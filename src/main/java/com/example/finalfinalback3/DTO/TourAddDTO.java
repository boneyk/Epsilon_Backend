package com.example.finalfinalback3.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TourAddDTO {
    private String name;
    private String country;
    private String city;
    private Integer capacity;
    private String description;

    public TourAddDTO(String name, String country, String city, Integer capacity, String description) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.capacity = capacity;
        this.description = description;
    }
}
