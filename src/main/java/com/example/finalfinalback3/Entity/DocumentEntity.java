package com.example.finalfinalback3.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class DocumentEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String token;
    private String fullname;
    private String phone_number;
    private String sex;
    //@JsonProperty("date_of_birth")
    private LocalDate dob;


    @OneToOne(cascade = CascadeType.ALL)
    private PassportEntity passport;

    @ManyToOne
    @JsonIgnore
    private UserEntity user;

    @JsonIgnore
    @ManyToMany(mappedBy = "participants")
    private List<TripEntity> trip;

    public DocumentEntity(Integer id, PassportEntity passport) {
        this.id = id;
        this.passport = passport;
    }
}
