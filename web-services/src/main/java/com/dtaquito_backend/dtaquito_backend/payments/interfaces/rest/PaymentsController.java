package com.dtaquito_backend.dtaquito_backend.payments.interfaces.rest;


import com.dtaquito_backend.dtaquito_backend.payments.domain.model.aggregates.Payments;
import com.dtaquito_backend.dtaquito_backend.payments.domain.model.queries.GetAllPaymentsByCardHolderQuery;
import com.dtaquito_backend.dtaquito_backend.payments.domain.model.queries.GetPaymentsByIdQuery;
import com.dtaquito_backend.dtaquito_backend.payments.domain.services.PaymentsCommandService;
import com.dtaquito_backend.dtaquito_backend.payments.domain.services.PaymentsQueryService;
import com.dtaquito_backend.dtaquito_backend.payments.interfaces.rest.resources.CreatePaymentsResource;
import com.dtaquito_backend.dtaquito_backend.payments.interfaces.rest.resources.PaymentsResource;
import com.dtaquito_backend.dtaquito_backend.payments.interfaces.rest.transform.CreatePaymentsCommandFromResourceAssembler;
import com.dtaquito_backend.dtaquito_backend.payments.interfaces.rest.transform.PaymentsResourceFromEntityAssembler;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.dtaquito_backend.dtaquito_backend.users.domain.services.UserQueryService;
import com.dtaquito_backend.dtaquito_backend.users.interfaces.rest.resources.UserResource;
import com.dtaquito_backend.dtaquito_backend.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentsController {

    private final PaymentsCommandService paymentsCommandService;
    private final PaymentsQueryService paymentsQueryService;
    private final UserQueryService userQueryService;

    public PaymentsController(PaymentsCommandService paymentsCommandService, PaymentsQueryService paymentsQueryService, UserQueryService userQueryService) {
        this.paymentsCommandService = paymentsCommandService;
        this.paymentsQueryService = paymentsQueryService;
        this.userQueryService = userQueryService;
    }

    @PostMapping
    public ResponseEntity<?> createPayments(@RequestBody CreatePaymentsResource resource) {
        Long userId = resource.userId();

        Optional<Payments> existingPayment = paymentsQueryService.getPaymentByUserId(userId);
        if (existingPayment.isPresent()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "User already has a payment card. Please delete the existing one before adding a new one.");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Long balance = (resource.balance() == null) ? 1L : resource.balance();

        CreatePaymentsResource newResource = new CreatePaymentsResource(resource.cardNumber(), resource.expirationDate(), resource.cardHolder(), resource.cardIssuer(), resource.cvv(), userId, balance);

        Optional<Payments> payments = paymentsCommandService.handle(userId, CreatePaymentsCommandFromResourceAssembler.toCommandFromResource((newResource)));

        if (payments.isPresent()) {
            Payments payment = payments.get();
            payment.setBalance(1000L);
            paymentsCommandService.updatePayments(payment);
        }

        return payments.map(source -> new ResponseEntity<>(PaymentsResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<PaymentsResource> getPaymentById(@PathVariable Long id) {
        Optional<Payments> payments = paymentsQueryService.handle(new GetPaymentsByIdQuery(id));
        return payments.map(source -> ResponseEntity.ok(PaymentsResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<List<PaymentsResource>> getAllPaymentsByCardHolder(String name){

        var getAllPaymentsByCardHolder = new GetAllPaymentsByCardHolderQuery(name);
        var payments = paymentsQueryService.handle(getAllPaymentsByCardHolder);
        if (payments.isEmpty()) return ResponseEntity.notFound().build();
        var paymentsResources = payments.stream().map(PaymentsResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(paymentsResources);
    }

    @GetMapping
    public ResponseEntity<List<PaymentsResource>> getAllPayments(){
        var payments = paymentsQueryService.getAllPayments();
        var paymentsResources = payments.stream().map(PaymentsResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(paymentsResources);
    }

    @GetMapping(params = {"cardHolder"})
    public ResponseEntity<?> getPaymentsWithParameters(@RequestParam Map<String, String> params) {

        if (params.containsKey("cardHolder")) {
            return getAllPaymentsByCardHolder(params.get("cardHolder"));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id) {
        Optional<Payments> payment = paymentsQueryService.handle(new GetPaymentsByIdQuery(id));
        if (payment.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        paymentsCommandService.handleDelete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(params = {"userId"})
    public ResponseEntity<?> getPaymentByUserId(@RequestParam("userId") Long userId) {
        Optional<Payments> payment = paymentsQueryService.getPaymentByUserId(userId);
        if (payment.isPresent()) {
            return ResponseEntity.ok(PaymentsResourceFromEntityAssembler.toResourceFromEntity(payment.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
