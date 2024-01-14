package com.example.finalfinalback3.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class DateEntity{
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDate dateStart;
    private LocalDate dateEnd;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "tour_date",
            joinColumns = @JoinColumn(name = "date_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id")
    )
    private List<TourEntity> tour;

    @JsonIgnore
    @OneToMany
    private List<BookingEntity> booking;
}
