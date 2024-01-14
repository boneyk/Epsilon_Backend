package com.example.finalfinalback3.DTO;

import com.example.finalfinalback3.Entity.ImageEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//Нормального названия не придумал.
//Это моделька, отображающая выбранный тур. Используется на этапе подтверждения заказа
//Почему не раньше? Раньше нужен доступ ко всей информации, а на этапе подтверждения - нет
@Data
@NoArgsConstructor
public class TourCutDTO {
    private Integer id;
    private String name;
    private String country;
    private String city;
    private Integer price_per_one;
    private String description;
    private List<ImageEntity> images;

    public TourCutDTO(Integer id, String name, String country, String city, Integer price_per_one, String description, List<ImageEntity> images) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.price_per_one = price_per_one;
        this.description = description;
        this.images = images;
    }
}
