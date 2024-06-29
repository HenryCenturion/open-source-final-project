package com.dtaquito_backend.dtaquito_backend.payments.interfaces.rest.transform;

import com.dtaquito_backend.dtaquito_backend.payments.domain.model.aggregates.Payments;
import com.dtaquito_backend.dtaquito_backend.payments.interfaces.rest.resources.PaymentsResource;

public class PaymentsResourceFromEntityAssembler {

    public static PaymentsResource toResourceFromEntity(Payments entity) {
        return new PaymentsResource(entity.getId(), entity.getCardNumber(), entity.getExpirationDate().expirationDate(), entity.getCardHolder(), entity.getCardIssuer(), entity.getCvv().cvv(), entity.getUser(), entity.getBalance());
    } 
}
