package com.example.finalfinalback3.DTO;

import com.example.finalfinalback3.Model.DocPersonalInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Integer date_id;
    private Integer tour_id;
    private String token;
    private List<DocPersonalInfo> person_list;
    private Integer price;
}
