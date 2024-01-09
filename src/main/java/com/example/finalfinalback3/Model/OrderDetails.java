package com.example.finalfinalback3.Model;

import com.example.finalfinalback3.Entity.TourEntity;
import com.example.finalfinalback3.Entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetails {
    private TourEntity tour;
    private UserEntity user;

    public OrderDetails(TourEntity tour, UserEntity user) {
        this.tour = tour;
        this.user = user;
    }
}
