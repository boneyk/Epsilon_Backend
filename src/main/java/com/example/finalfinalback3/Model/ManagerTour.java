package com.example.finalfinalback3.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ManagerTour {
    private Integer id;
    private String name;
    private String tour_type;
    private Integer price_per_one;
}
