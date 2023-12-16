package com.example.finalfinalback3.Entity;

import jakarta.persistence.*;

@Entity
public class PersonalInfoEntity {
    @Id
    private Integer id;
    private String fullname;
    private String phone_num;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private UserEntity user_id;
}
