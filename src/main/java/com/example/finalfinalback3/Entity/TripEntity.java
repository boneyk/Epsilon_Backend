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
    //Добавить цену

    @OneToOne
    private BookingEntity bookingEntity;

    @ManyToMany
    @JoinTable(name = "participant_trip",
        joinColumns = @JoinColumn(name = "trip_id"),
        inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private List<DocumentEntity> participants;

    @JsonIgnore
    @ManyToMany(mappedBy = "history")
    private List<UserEntity> history;

    public TripEntity(Integer people_amount, BookingEntity bookingEntity, List<DocumentEntity> participants, String manager) {
        this.people_amount = people_amount;
        this.bookingEntity = bookingEntity;
        this.participants = participants;
        this.manager = manager;
    }
}
