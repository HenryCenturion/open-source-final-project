package com.dtaquito_backend.dtaquito_backend.payments.domain.model.aggregates;

import com.dtaquito_backend.dtaquito_backend.payments.domain.model.valueObjects.Cvv;
import com.dtaquito_backend.dtaquito_backend.payments.domain.model.valueObjects.ExpirationDate;
import com.dtaquito_backend.dtaquito_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.dtaquito_backend.dtaquito_backend.payments.domain.model.commands.CreatePaymentsCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Payments extends AuditableAbstractAggregateRoot<Payments> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cardNumber;

    @Embedded
    private ExpirationDate expirationDate;

    @Column(nullable = false)
    private String cardHolder;

    @Column(nullable = false)
    private String cardIssuer;

    @Embedded
    private Cvv cvv;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_user_Id2"))
    @Getter
    private User user;

    @Column(nullable = false)
    @Getter
    @Setter
    private Long balance = 1000L;

    protected Payments() {}

    public Payments(CreatePaymentsCommand command, User user) {
        this.cardNumber = command.cardNumber();
        this.cardHolder = command.cardHolder();
        this.expirationDate = new ExpirationDate(command.expirationDate());
        this.cardIssuer = command.cardIssuer();
        this.cvv = new Cvv(command.cvv());
        this.user = user;
        this.balance = command.balance() != null ? command.balance() : 1000L;
    }
}