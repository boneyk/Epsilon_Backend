package com.example.finalfinalback3.Model;

import com.example.finalfinalback3.DTO.TourCutDTO;
import com.example.finalfinalback3.Entity.DateEntity;
import com.example.finalfinalback3.Entity.TourEntity;
import com.example.finalfinalback3.Entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDetails {
    private TourCutDTO tour;
    private DateEntity date;
    private String token;
    private List<DocPersonalInfo> person_list;

    public OrderDetails(TourCutDTO tour, DateEntity date, String token, List<DocPersonalInfo> person_list) {
        this.tour = tour;
        this.date = date;
        this.token = token;
        this.person_list = person_list;
    }
}
