package com.example.finalfinalback3.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class DateEntity{
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDate dateStart;
    private LocalDate dateEnd;

    @ManyToOne
    @JsonIgnore
    private TourEntity tour;

    @JsonIgnore
    @OneToMany
    private List<BookingEntity> booking;
}
