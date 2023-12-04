package com.example.finalfinalback3.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
public class UserAuthDTO {
    public String login;
    public String password;
}


