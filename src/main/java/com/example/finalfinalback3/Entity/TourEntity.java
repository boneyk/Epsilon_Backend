package com.example.finalfinalback3.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TourEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String country;
    private String city;
    private Integer capacity;
    private String description;


}
