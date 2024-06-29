package com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.aggregates;

import com.dtaquito_backend.dtaquito_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.entities.Sport;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.commands.CreateSportSpacesCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class SportSpaces extends AuditableAbstractAggregateRoot<SportSpaces> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String name;

    @ManyToOne
    @JoinColumn(name = "sport_id", nullable = false)
    private Sport sport;

    @Column(nullable = false)
    @Getter
    private String imageUrl;

    @Column(nullable = false)
    @Getter
    private Long price;

    @Column(nullable = false)
    @Getter
    private String district;

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

    @Column(nullable = false)
    @Getter
    private Long rating;


    protected SportSpaces() {}

    public SportSpaces(CreateSportSpacesCommand command, User user, Sport sport) {
        this.name = command.name();
        this.sport = sport;
        this.imageUrl = command.imageUrl();
        this.price = command.price();
        this.district = command.district();
        this.description = command.description();
        this.user = user;
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        this.rating = 0L;
    }

    public void update(CreateSportSpacesCommand command) {
        this.name = command.name();
        this.imageUrl = command.imageUrl();
        this.price = command.price();
        this.district = command.district();
        this.description = command.description();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        this.rating= command.rating();
    }
}