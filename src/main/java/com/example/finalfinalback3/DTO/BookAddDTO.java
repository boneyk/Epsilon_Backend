package com.example.finalfinalback3.DTO;

import com.example.finalfinalback3.Entity.DateEntity;
import com.example.finalfinalback3.Entity.TourEntity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookAddDTO {
    private TourEntity tour;
    private DateEntity date;

    public BookAddDTO(TourEntity tour, DateEntity date) {
        this.tour = tour;
        this.date = date;
    }
}
