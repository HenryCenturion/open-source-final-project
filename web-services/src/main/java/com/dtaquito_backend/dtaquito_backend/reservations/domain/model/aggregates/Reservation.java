package com.dtaquito_backend.dtaquito_backend.reservations.domain.model.aggregates;

import com.dtaquito_backend.dtaquito_backend.reservations.domain.model.commands.CreateReservationsCommand;
import com.dtaquito_backend.dtaquito_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.dtaquito_backend.dtaquito_backend.sportspaces.domain.model.aggregates.SportSpaces;
import com.dtaquito_backend.dtaquito_backend.users.domain.model.aggregates.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private Date time;

    @Column(nullable = false)
    @Getter
    private Long hours;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_user_Id3"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter
    private User user;

    @ManyToOne
    @JoinColumn(name = "sport_spaces_id", nullable = false, foreignKey = @ForeignKey(name = "FK_space_Id"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter
    private SportSpaces sportSpaces;

    protected Reservation() {}

    public Reservation(CreateReservationsCommand command, User user, SportSpaces sportSpaces) {

        this.time = command.time();
        this.hours = command.hours();
        this.user = user;
        this.sportSpaces = sportSpaces;
    }

}
