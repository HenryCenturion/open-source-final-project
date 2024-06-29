package com.dtaquito_backend.dtaquito_backend.payments.interfaces.rest.transform;

import com.dtaquito_backend.dtaquito_backend.payments.domain.model.commands.CreatePaymentsCommand;
import com.dtaquito_backend.dtaquito_backend.payments.interfaces.rest.resources.CreatePaymentsResource;

public class CreatePaymentsCommandFromResourceAssembler {

    public static CreatePaymentsCommand toCommandFromResource(CreatePaymentsResource resource) {
        return new CreatePaymentsCommand(resource.cardNumber(), resource.expirationDate(), resource.cardHolder(), resource.cardIssuer(), resource.cvv(), resource.userId(), resource.balance());
    }
}
