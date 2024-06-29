package com.dtaquito_backend.dtaquito_backend.payments.application.internal.eventhandlers;


import com.dtaquito_backend.dtaquito_backend.payments.domain.model.events.PaymentsCreatedEvent;
import com.dtaquito_backend.dtaquito_backend.payments.domain.model.queries.GetPaymentsByIdQuery;
import com.dtaquito_backend.dtaquito_backend.payments.domain.services.PaymentsCommandService;
import com.dtaquito_backend.dtaquito_backend.payments.domain.services.PaymentsQueryService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentsCreatedEventHandler {

    private final PaymentsQueryService paymentsQueryService;
    private final PaymentsCommandService paymentsCommandService;

    public PaymentsCreatedEventHandler(PaymentsQueryService paymentsQueryService, PaymentsCommandService paymentsCommandService) {
        this.paymentsQueryService = paymentsQueryService;
        this.paymentsCommandService = paymentsCommandService;
    }

    @EventListener(PaymentsCreatedEvent.class)
    public void on(PaymentsCreatedEvent event){
        System.out.println("PaymentsCreatedEvent received for payment ID: " + event.getId());

        paymentsCommandService.handlePaymentsCreatedEvent(event);

        var getPaymentsByIdQuery = new GetPaymentsByIdQuery(event.getId());

        var payment = paymentsQueryService.handle(getPaymentsByIdQuery);

        if(payment.isPresent()){
            System.out.println("Payment with ID " + event.getId() + " has been created.");
        } else {
            System.out.println("No payment found with ID " + event.getId());
        }
    }
}
