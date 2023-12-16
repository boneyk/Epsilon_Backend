package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.ImageAddDTO;
import com.example.finalfinalback3.DTO.PersonalInfoAddDTO;
import com.example.finalfinalback3.DTO.TourAddDTO;
import com.example.finalfinalback3.DTO.UserRegisterDTO;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DataFillerService {

    private final AuthService authService;
    private final TourService tourService;
    private final ImageService imageService;
    private final  UserService userService;
    public DataFillerService(AuthService authService, TourService tourService, ImageService imageService, UserService userService) {
        this.authService = authService;
        this.tourService = tourService;
        this.imageService = imageService;
        this.userService = userService;
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


        ImageAddDTO image1 = new ImageAddDTO("example_image", "/images/", "smth_important", "1920x1080", ".jpg");
        ImageAddDTO image2 = new ImageAddDTO("epsilon", "/images/", "logo", "50x50", ".png");
        //ImageAddDTO image3 = new ImageAddDTO("example_image", "/images/", "smth_important", "1920x1080", ".png");
        //ImageAddDTO image4 = new ImageAddDTO("example_image", "/images/", "smth_important", "1920x1080", ".png");
        //ImageAddDTO image5 = new ImageAddDTO("example_image", "/images/", "smth_important", "1920x1080", ".png");
        //ImageAddDTO image6 = new ImageAddDTO("example_image", "/images/", "smth_important", "1920x1080", ".png");

        imageService.addImage(image1);
        imageService.addImage(image2);

        TourAddDTO tour1 = new TourAddDTO("Hot Egypt tour!", "Egypt", "Anoubis", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 16), "Foreign", 30, 99999, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");
        TourAddDTO tour2 = new TourAddDTO("Popular Thailand tour!", "Thailand", "Bangkok", LocalDate.of(2024, 9, 15), LocalDate.of(2024,10, 15), "Foreign", 64, 150156, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");
        TourAddDTO tour3 = new TourAddDTO("Visit Japan!", "Japan", "Tokyo", LocalDate.of(2024, 6, 8), LocalDate.of(2024,6, 1), "Foreign", 15, 90000, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");
        TourAddDTO tour4 = new TourAddDTO("Fall into Korea!", "Korea", "Seoul", LocalDate.of(2024, 10, 18), LocalDate.of(2024,8, 18), "Foreign", 100, 200000, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");
        TourAddDTO tour5 = new TourAddDTO("Hot Mayami beach!", "America", "Maiami", LocalDate.of(2024, 7, 31), LocalDate.of(2024,6, 1), "Foreign", 10, 502000, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");
        TourAddDTO tour6 = new TourAddDTO("Special Baikal offer!", "Russia", "Baikal", LocalDate.of(2024, 1, 8), LocalDate.of(2023,12, 29), "Russia", 120, 50000, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");

        tourService.addTour(tour1);
        tourService.addTour(tour2);
        tourService.addTour(tour3);
        tourService.addTour(tour4);
        tourService.addTour(tour5);
        tourService.addTour(tour6);

        PersonalInfoAddDTO person1 = new PersonalInfoAddDTO(2, "Epsilon Developer team", "+79991115050");

        //userService.addPersonalInfo(person1);
    }

}
