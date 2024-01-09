package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.DateAddDTO;
import com.example.finalfinalback3.DTO.ImageAddDTO;
import com.example.finalfinalback3.DTO.TourAddDTO;
import com.example.finalfinalback3.DTO.UserRegisterDTO;
import com.example.finalfinalback3.Entity.TourEntity;
import com.example.finalfinalback3.Model.Token;
import com.example.finalfinalback3.Security.AuthService;
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
    private final UserService userService;
    private final DateService dateService;
    public DataFillerService(AuthService authService, TourService tourService, ImageService imageService, UserService userService, DateService dateService) {
        this.authService = authService;
        this.tourService = tourService;
        this.imageService = imageService;
        this.userService = userService;
        this.dateService = dateService;
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
        Token user2_token = authService.registration(user2);
        authService.registration(user3);
        authService.registration(user4);

        //userService.setUserRoleAdmin(user2_id);

        ImageAddDTO image1 = new ImageAddDTO("egyptanoubis", "/images/", "anoubis", "1920x1080", ".png");
        ImageAddDTO image2 = new ImageAddDTO("thailandbangkok", "/images/", "bangkok", "1920x1080", ".png");
        ImageAddDTO image3 = new ImageAddDTO("japantokyo", "/images/", "japantokyo", "1920x1080", ".png");
        ImageAddDTO image4 = new ImageAddDTO("koreaseoul", "/images/", "koreaseoul", "1920x1080", ".png");
        ImageAddDTO image5 = new ImageAddDTO("americamaiami", "/images/", "americamaiami", "1920x1080", ".png");
        ImageAddDTO image6 = new ImageAddDTO("russiabaikal", "/images/", "russiabaikal", "1920x1080", ".png");

        Integer image1_id = imageService.addImage(image1).getId();
        Integer image2_id = imageService.addImage(image2).getId();
        Integer image3_id = imageService.addImage(image3).getId();
        Integer image4_id = imageService.addImage(image4).getId();
        Integer image5_id = imageService.addImage(image5).getId();
        Integer image6_id = imageService.addImage(image6).getId();

        TourAddDTO tour1 = new TourAddDTO("Горячая путёвка в Египет!", "Египет", "Анубис", "Международный", 30, 99999, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");
        TourAddDTO tour2 = new TourAddDTO("Гостеприимный Тайланд", "Тайланд", "Бангкок", "Международный", 64, 150156, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");
        TourAddDTO tour3 = new TourAddDTO("Страна восходящего Солнца!", "Япония", "Токио", "Международный", 15, 90000, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");
        TourAddDTO tour4 = new TourAddDTO("Страна будущего!", "Корея", "Сеул",  "Международный", 100, 200000, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");
        TourAddDTO tour5 = new TourAddDTO("Бархатистые пески Майями!", "Америка", "Майями",  "Международный", 10, 502000, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");
        TourAddDTO tour6 = new TourAddDTO("Зимний Байкал", "Россия", "Байкал", "По России", 120, 50000, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf");

        Integer tour1_id = tourService.addTour(tour1).getId();
        Integer tour2_id = tourService.addTour(tour2).getId();
        Integer tour3_id = tourService.addTour(tour3).getId();
        Integer tour4_id = tourService.addTour(tour4).getId();
        Integer tour5_id = tourService.addTour(tour5).getId();
        Integer tour6_id = tourService.addTour(tour6).getId();

        tourService.setImage(tour1_id, image1_id);
        tourService.setImage(tour2_id, image2_id);
        tourService.setImage(tour3_id, image3_id);
        tourService.setImage(tour4_id, image4_id);
        tourService.setImage(tour5_id, image5_id);
        tourService.setImage(tour6_id, image6_id);

        DateAddDTO date1 = new DateAddDTO(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 2, 1));
        DateAddDTO date2 = new DateAddDTO(LocalDate.of(2024, 7, 15), LocalDate.of(2024, 8, 1));
        DateAddDTO date3 = new DateAddDTO(LocalDate.of(2024, 8, 1), LocalDate.of(2024, 8, 16));
        DateAddDTO date4 = new DateAddDTO(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 7));
        DateAddDTO date5 = new DateAddDTO(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 7));
        DateAddDTO date6 = new DateAddDTO(LocalDate.of(2024, 4, 7), LocalDate.of(2024, 4, 21));
        DateAddDTO date7 = new DateAddDTO(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 5, 6));
        DateAddDTO date8 = new DateAddDTO(LocalDate.of(2024, 12, 27), LocalDate.of(2024, 12, 30));
        DateAddDTO date9 = new DateAddDTO(LocalDate.of(2024, 12, 15), LocalDate.of(2025, 1, 15));
        DateAddDTO date10 = new DateAddDTO(LocalDate.of(2024, 10, 10), LocalDate.of(2024, 12, 1));
        DateAddDTO date11 = new DateAddDTO(LocalDate.of(2024, 3, 15), LocalDate.of(2024, 3, 21));

        dateService.addTourToDate(date1, 1);
        dateService.addTourToDate(date2, 1);
        dateService.addTourToDate(date3, 2);
        dateService.addTourToDate(date4, 2);
        dateService.addTourToDate(date5, 3);
        dateService.addTourToDate(date6, 4);
        dateService.addTourToDate(date7, 4);
        dateService.addTourToDate(date8, 5);
        dateService.addTourToDate(date9, 5);
        dateService.addTourToDate(date10, 6);
        dateService.addTourToDate(date11, 6);

        //PersonalInfoAddDTO person1 = new PersonalInfoAddDTO("Epsilon Developer team", "+79991115050");

        //userService.addPersonalInfo(person1, user2_token.getToken());
    }

}
