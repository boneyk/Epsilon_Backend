package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.TourAddDTO;
import com.example.finalfinalback3.DTO.UserRegisterDTO;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class DataFillerService {

    private final AuthService authService;
    private final TourService tourService;
    public DataFillerService(AuthService authService, TourService tourService) {
        this.authService = authService;
        this.tourService = tourService;
    }

    @PostConstruct
    @Transactional
    @SneakyThrows
    public void fillData() {
        UserRegisterDTO user1 = new UserRegisterDTO("Dum@gmail.com", "Hr0mE", "smth", "smth");
        UserRegisterDTO user2 = new UserRegisterDTO("Numbers@gmail.com", "123", "123", "123");
        UserRegisterDTO user3 = new UserRegisterDTO("Not_admin@gmail.com", "Not_admin", "true", "true");
        UserRegisterDTO user4 = new UserRegisterDTO("Petrovich@gmail.com", "Petrovich", "123", "123");

        authService.registration(user1);
        authService.registration(user2);
        authService.registration(user3);
        authService.registration(user4);

        TourAddDTO tour1 = new TourAddDTO("Hot Egypt tour!", "Egypt", "Anoubis", 30, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");
        TourAddDTO tour2 = new TourAddDTO("Populat Thailand tour!", "Thailand", "Bangkok", 16, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");
        TourAddDTO tour3 = new TourAddDTO("Visit Japan!", "Japan", "Tokyo", 80, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");
        TourAddDTO tour4 = new TourAddDTO("Fall into Korea!", "Korea", "Seoul", 100, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");
        TourAddDTO tour5 = new TourAddDTO("Hot Mayami beach!", "America", "Maiami", 10, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");
        TourAddDTO tour6 = new TourAddDTO("Special Baikal offer!", "Russia", "Baikal", 25, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");

        tourService.addTour(tour1);
        tourService.addTour(tour2);
        tourService.addTour(tour3);
        tourService.addTour(tour4);
        tourService.addTour(tour5);
        tourService.addTour(tour6);

    }
}
