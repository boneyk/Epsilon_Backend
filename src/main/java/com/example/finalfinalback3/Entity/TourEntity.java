package com.example.finalfinalback3.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class TourEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String country;
    private String city;
    private String tour_type;
    private Integer capacity;
    private Integer price_per_one;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="tour_image",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<ImageEntity> images;

    @JsonIgnore
    @ManyToMany(mappedBy = "favorites")
    private List<UserEntity> favorites;


    @ManyToMany(mappedBy = "tour")
    private List<DateEntity> date;

    @JsonIgnore
    @OneToMany
    private List<BookingEntity> booking;

    @JsonIgnore
    @ManyToOne
    private UserEntity manager;
}
