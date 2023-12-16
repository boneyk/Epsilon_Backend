package com.example.finalfinalback3.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonalInfoAddDTO {
    private Integer user_id;
    private String fullname;
    private String phone_num;

    public PersonalInfoAddDTO(Integer id, String fullname, String phone_num) {
        this.user_id = id;
        this.fullname = fullname;
        this.phone_num = phone_num;
    }
}
