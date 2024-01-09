package com.example.finalfinalback3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TourAddDTO {
    private String name;
    private String country;
    private String city;
    private String tour_type;
    private Integer capacity;
    private Integer price_per_one;
    private String description;

    public TourAddDTO(String name, String country, String city, String tour_type, Integer capacity, Integer price_per_one, String description) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.tour_type = tour_type;
        this.capacity = capacity;
        this.price_per_one = price_per_one;
        this.description = description;
    }
}
