package com.dtaquito_backend.dtaquito_backend.suscriptions.interfaces.rest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.dtaquito_backend.dtaquito_backend.payments.domain.model.aggregates.Payments;
import com.dtaquito_backend.dtaquito_backend.payments.domain.services.PaymentsCommandService;
import com.dtaquito_backend.dtaquito_backend.payments.domain.services.PaymentsQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.aggregates.Suscriptions;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.queries.GetAllSuscriptionsByPlanQuery;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.queries.GetSuscriptionsByIdQuery;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.services.SuscriptionsCommandService;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.services.SuscriptionsQueryService;
import com.dtaquito_backend.dtaquito_backend.suscriptions.interfaces.rest.resources.CreateSuscriptionsResource;
import com.dtaquito_backend.dtaquito_backend.suscriptions.interfaces.rest.resources.SuscriptionsResource;
import com.dtaquito_backend.dtaquito_backend.suscriptions.interfaces.rest.transform.CreateSuscriptionsCommandFromResourceAssembler;
import com.dtaquito_backend.dtaquito_backend.suscriptions.interfaces.rest.transform.SuscriptionsResourceFromEntityAssembler;

@RestController
@RequestMapping("/api/v1/suscriptions")
public class SuscriptionsController {

    private final SuscriptionsQueryService suscriptionsQueryService;
    private final SuscriptionsCommandService suscriptionsCommandService;
    private final PaymentsCommandService paymentsCommandService;
    private final PaymentsQueryService paymentsQueryService;

    public SuscriptionsController(SuscriptionsQueryService suscriptionsQueryService, SuscriptionsCommandService suscriptionsCommandService, PaymentsCommandService paymentsCommandService, PaymentsQueryService paymentsQueryService) {
        this.suscriptionsQueryService = suscriptionsQueryService;
        this.suscriptionsCommandService = suscriptionsCommandService;
        this.paymentsCommandService = paymentsCommandService;
        this.paymentsQueryService = paymentsQueryService;
    }

    @GetMapping("{id}")
    public ResponseEntity<SuscriptionsResource> getSuscripcionById(@PathVariable Long id) {
        Optional<Suscriptions> suscriptions = suscriptionsQueryService.handle(new GetSuscriptionsByIdQuery(id));
        return suscriptions.map(source -> ResponseEntity.ok(SuscriptionsResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<List<SuscriptionsResource>> getAllUsersByPlan(String plan){

        var getAllSuscriptionsByPlanQuery = new GetAllSuscriptionsByPlanQuery(plan);
        var suscriptions = suscriptionsQueryService.handle(getAllSuscriptionsByPlanQuery);
        if (suscriptions.isEmpty()) return ResponseEntity.notFound().build();
        var suscriptionsResources = suscriptions.stream().map(SuscriptionsResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(suscriptionsResources);
    }

    @GetMapping(params = {"!id", "!userId"})
    public ResponseEntity<List<SuscriptionsResource>> getAllSuscriptions(){
        var suscriptions = suscriptionsQueryService.getAllSuscriptions();
        var suscriptionsResources = suscriptions.stream().map(SuscriptionsResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(suscriptionsResources);
    }

    @GetMapping(params = {"plan"})
    public ResponseEntity<?> getUsersWithParameters(@RequestParam Map<String, String> params) {

        if (params.containsKey("plan")) {
            return getAllUsersByPlan(params.get("plan"));
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<SuscriptionsResource> updateSuscription(@PathVariable Long id, @RequestBody CreateSuscriptionsResource resource) {

        Optional<Suscriptions> existingSuscription = suscriptionsQueryService.getSuscriptionById(id);
        if (!existingSuscription.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Suscriptions suscription = existingSuscription.get();
        if (suscription.getPlan().equals("free") && "premium".equals(resource.plan())) {
            Long userId = suscription.getUser().getId();
            Optional<Payments> payments = paymentsQueryService.getPaymentByUserId(userId);
            if (!payments.isPresent()) {
                SuscriptionsResource errorResource = new SuscriptionsResource(null, null, null);
                return new ResponseEntity<>(errorResource, HttpStatus.BAD_REQUEST);
            }
            Payments payment = payments.get();
            if (payment.getBalance() < 70) {
                SuscriptionsResource errorResource = new SuscriptionsResource(null, null, null);
                return new ResponseEntity<>(errorResource, HttpStatus.BAD_REQUEST);
            }
            payment.setBalance(payment.getBalance() - 70);
            paymentsCommandService.updatePayments(payment);
        }

        suscription.update(CreateSuscriptionsCommandFromResourceAssembler.toCommandFromResource(resource));
        Suscriptions updatedSuscription = suscriptionsCommandService.updateSuscription(suscription).orElseThrow(() -> new IllegalArgumentException("Error updating suscription"));
        return ResponseEntity.ok(SuscriptionsResourceFromEntityAssembler.toResourceFromEntity(updatedSuscription));
    }

    @GetMapping(params = {"userId"})
    public ResponseEntity<?> getSubscriptionByUserId(@RequestParam("userId") Long userId) {
        Optional<Suscriptions> subscription = suscriptionsQueryService.getSubscriptionByUserId(userId);
        if (subscription.isPresent()) {
            return ResponseEntity.ok(SuscriptionsResourceFromEntityAssembler.toResourceFromEntity(subscription.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
