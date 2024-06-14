package com.dtaquito_backend.dtaquito_backend.payments.domain.model.aggregates;

import java.util.Date;

import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnCloudPlatform;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.dtaquito_backend.dtaquito_backend.payments.domain.model.commands.CreatePaymentsCommand;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Payments extends AbstractAggregateRoot<Payments>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String cardNumber;

    @Column(nullable = false)
    @Getter
    private Date expirationDate;

    @Column(nullable = false)
    @Getter
    private String cardHolder;

    @Column(nullable = false)
    @Getter
    private String cardIssuer;

    @Column(nullable = false)
    @Getter
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_user_Id2"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
        this.expirationDate = command.expirationDate();
        this.cardIssuer = command.cardIssuer();
        this.cvv = command.cvv();
        this.user = user;
        this.balance = command.balance() != null ? command.balance() : 1000L;
    }
}
