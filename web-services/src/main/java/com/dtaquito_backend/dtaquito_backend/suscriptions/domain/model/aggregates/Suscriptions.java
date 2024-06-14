package com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.aggregates;

import com.dtaquito_backend.dtaquito_backend.users.domain.model.commands.CreateSuscriptionsCommand;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Suscriptions extends AbstractAggregateRoot<Suscriptions> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter // Setter for plan
    private String plan = "free"; // Set plan to "free" by default

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_user_Id_unique"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter
    private User user;

    protected Suscriptions() {}

    public Suscriptions(CreateSuscriptionsCommand command, User user) {
        this.plan = command.plan();
        this.user = user;
    }

    public void update(CreateSuscriptionsCommand command) {
        this.plan = command.plan();
    }
}