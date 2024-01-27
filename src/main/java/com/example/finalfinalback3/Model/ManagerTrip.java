package com.example.finalfinalback3.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ManagerTrip {
    private Integer id;
    private String login;
    private String country;
    private String city;
    private String price;

    public ManagerTrip(Integer id, String login, String country, String city, String price) {
        this.id = id;
        this.login = login;
        this.country = country;
        this.city = city;
        this.price = price;
    }
}
