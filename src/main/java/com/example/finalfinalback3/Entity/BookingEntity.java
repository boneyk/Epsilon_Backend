package com.example.finalfinalback3.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BookingEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private TourEntity tour;
    @ManyToOne
    private DateEntity date;
}
