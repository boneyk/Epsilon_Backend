package com.example.finalfinalback3.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TripEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer people_amount;
    //СТАТУС ПО УМОЛЧАНИЮ СТАВИТСЯ В TripService.addTrip()
    private String status = "MODERATING";
    private String manager;
    private Integer price;

    @OneToOne
    private BookingEntity bookingEntity;

    @ManyToMany
    @JoinTable(name = "participant_trip",
        joinColumns = @JoinColumn(name = "trip_id"),
        inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private List<DocumentEntity> participants;

    @JsonIgnore
    @ManyToOne
    private UserEntity user;

    public TripEntity(Integer people_amount, String manager, Integer price, BookingEntity bookingEntity, List<DocumentEntity> participants, UserEntity user) {
        this.people_amount = people_amount;
        this.manager = manager;
        this.price = price;
        this.bookingEntity = bookingEntity;
        this.participants = participants;
        this.user = user;
    }
}
