package com.example.finalfinalback3.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
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
