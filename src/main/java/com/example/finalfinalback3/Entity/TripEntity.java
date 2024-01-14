package com.example.finalfinalback3.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class TripEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer people_amount;
    //СТАТУС ПО УМОЛЧАНИЮ СТАВИТСЯ В TripService.addTrip()
    private String status = "MODERATING";

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

    public TripEntity(Integer people_amount, BookingEntity bookingEntity, List<DocumentEntity> participants) {
        this.people_amount = people_amount;
        this.bookingEntity = bookingEntity;
        this.participants = participants;
    }
}
