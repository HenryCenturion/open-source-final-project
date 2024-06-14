package com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.aggregates;

import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.commands.CreateSportSpacesCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class SportSpaces extends AbstractAggregateRoot<SportSpaces> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String name;

    @Column(nullable = false)
    @Getter
    private String imageUrl;

    @Column(nullable = false)
    @Getter
    private Long price;

    @Column(nullable = false)
    @Getter
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_user_Id"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter
    private User user;

    @Column(nullable = false)
    @Getter
    private String startTime;

    @Column(nullable = false)
    @Getter
    private String endTime;

    protected SportSpaces() {}

    public SportSpaces(CreateSportSpacesCommand command, User user) {
        this.name = command.name();
        this.imageUrl = command.imageUrl();
        this.price = command.price();
        this.description = command.description();
        this.user = user;
        this.startTime = command.startTime();
        this.endTime = command.endTime();
    }

    public void update(CreateSportSpacesCommand command) {
        this.name = command.name();
        this.imageUrl = command.imageUrl();
        this.price = command.price();
        this.description = command.description();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
    }
}
