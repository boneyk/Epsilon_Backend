package com.example.finalfinalback3.Model;

import com.example.finalfinalback3.Entity.DocumentEntity;
import com.example.finalfinalback3.Entity.TourEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TourDetails {
    private TourEntity tour;
    private String user_token;
    private List<DocPersonalInfo> persons; //Создать модельку для вывода в детали todo

    public TourDetails(TourEntity tour, String user_token, List<DocPersonalInfo> persons) {
        this.tour = tour;
        this.user_token = user_token;
        this.persons = persons;
    }
}
