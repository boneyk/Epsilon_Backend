package com.example.finalfinalback3.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenRole {
    private String token;
    private String role;

    public TokenRole(String token, String role) {
        this.token = token;
        this.role = role;
    }
}
