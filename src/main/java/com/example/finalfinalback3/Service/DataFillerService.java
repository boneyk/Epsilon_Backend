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
import java.util.List;

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
        ImageAddDTO image1_1 = new ImageAddDTO("egtparam", "/images/", "anoubis", "1920x1080", ".png");
        ImageAddDTO image1_2 = new ImageAddDTO("egtphoto1", "/images/", "anoubis", "1920x1080", ".png");
        ImageAddDTO image1_3 = new ImageAddDTO("egtphoto2", "/images/", "anoubis", "1920x1080", ".png");
        ImageAddDTO image1_4 = new ImageAddDTO("egtphoto3", "/images/", "anoubis", "1920x1080", ".png");

        ImageAddDTO image2 = new ImageAddDTO("thailandbangkok", "/images/", "bangkok", "1920x1080", ".png");
        ImageAddDTO image2_1 = new ImageAddDTO("thaiparam", "/images/", "bangkok", "1920x1080", ".png");
        ImageAddDTO image2_2 = new ImageAddDTO("thaiphoto1", "/images/", "bangkok", "1920x1080", ".png");
        ImageAddDTO image2_3 = new ImageAddDTO("thaiphoto2", "/images/", "bangkok", "1920x1080", ".png");
        ImageAddDTO image2_4 = new ImageAddDTO("thaiphoto3", "/images/", "bangkok", "1920x1080", ".png");

        ImageAddDTO image3 = new ImageAddDTO("japantokyo", "/images/", "japantokyo", "1920x1080", ".png");
        ImageAddDTO image3_1 = new ImageAddDTO("japparam", "/images/", "japantokyo", "1920x1080", ".png");
        ImageAddDTO image3_2 = new ImageAddDTO("japphoto1", "/images/", "japantokyo", "1920x1080", ".png");
        ImageAddDTO image3_3 = new ImageAddDTO("japphoto2", "/images/", "japantokyo", "1920x1080", ".png");
        ImageAddDTO image3_4 = new ImageAddDTO("japphoto3", "/images/", "japantokyo", "1920x1080", ".png");

        ImageAddDTO image4 = new ImageAddDTO("koreaseoul", "/images/", "koreaseoul", "1920x1080", ".png");
        ImageAddDTO image4_1 = new ImageAddDTO("korparam", "/images/", "koreaseoul", "1920x1080", ".png");
        ImageAddDTO image4_2 = new ImageAddDTO("korphoto1", "/images/", "koreaseoul", "1920x1080", ".png");
        ImageAddDTO image4_3 = new ImageAddDTO("korphoto2", "/images/", "koreaseoul", "1920x1080", ".png");
        ImageAddDTO image4_4 = new ImageAddDTO("korphoto3", "/images/", "koreaseoul", "1920x1080", ".png");

        ImageAddDTO image5 = new ImageAddDTO("americamaiami", "/images/", "americamaiami", "1920x1080", ".png");
        ImageAddDTO image5_1 = new ImageAddDTO("maiparam", "/images/", "americamaiami", "1920x1080", ".png");
        ImageAddDTO image5_2 = new ImageAddDTO("maiphoto1", "/images/", "americamaiami", "1920x1080", ".png");
        ImageAddDTO image5_3 = new ImageAddDTO("maiphoto2", "/images/", "americamaiami", "1920x1080", ".png");
        ImageAddDTO image5_4 = new ImageAddDTO("maiphoto3", "/images/", "americamaiami", "1920x1080", ".png");

        ImageAddDTO image6 = new ImageAddDTO("russiabaikal", "/images/", "russiabaikal", "1920x1080", ".png");
        ImageAddDTO image6_1 = new ImageAddDTO("baiparam", "/images/", "russiabaikal", "1920x1080", ".png");
        ImageAddDTO image6_2 = new ImageAddDTO("baiphoto1", "/images/", "russiabaikal", "1920x1080", ".png");
        ImageAddDTO image6_3 = new ImageAddDTO("baiphoto2", "/images/", "russiabaikal", "1920x1080", ".png");
        ImageAddDTO image6_4 = new ImageAddDTO("baiphoto3", "/images/", "russiabaikal", "1920x1080", ".png");


        Integer image1_id = imageService.addImage(image1).getId();
        Integer image1_1_id = imageService.addImage(image1_1).getId();
        Integer image1_2_id = imageService.addImage(image1_2).getId();
        Integer image1_3_id = imageService.addImage(image1_3).getId();
        Integer image1_4_id = imageService.addImage(image1_4).getId();

        Integer image2_id = imageService.addImage(image2).getId();
        Integer image2_1_id = imageService.addImage(image2_1).getId();
        Integer image2_2_id = imageService.addImage(image2_2).getId();
        Integer image2_3_id = imageService.addImage(image2_3).getId();
        Integer image2_4_id = imageService.addImage(image2_4).getId();

        Integer image3_id = imageService.addImage(image3).getId();
        Integer image3_1_id = imageService.addImage(image3_1).getId();
        Integer image3_2_id = imageService.addImage(image3_2).getId();
        Integer image3_3_id = imageService.addImage(image3_3).getId();
        Integer image3_4_id = imageService.addImage(image3_4).getId();

        Integer image4_id = imageService.addImage(image4).getId();
        Integer image4_1_id = imageService.addImage(image4_1).getId();
        Integer image4_2_id = imageService.addImage(image4_2).getId();
        Integer image4_3_id = imageService.addImage(image4_3).getId();
        Integer image4_4_id = imageService.addImage(image4_4).getId();

        Integer image5_id = imageService.addImage(image5).getId();
        Integer image5_1_id = imageService.addImage(image5_1).getId();
        Integer image5_2_id = imageService.addImage(image5_2).getId();
        Integer image5_3_id = imageService.addImage(image5_3).getId();
        Integer image5_4_id = imageService.addImage(image5_4).getId();

        Integer image6_id = imageService.addImage(image6).getId();
        Integer image6_1_id = imageService.addImage(image6_1).getId();
        Integer image6_2_id = imageService.addImage(image6_2).getId();
        Integer image6_3_id = imageService.addImage(image6_3).getId();
        Integer image6_4_id = imageService.addImage(image6_4).getId();

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
        tourService.setImage(tour1_id, image1_1_id);
        tourService.setImage(tour1_id, image1_2_id);
        tourService.setImage(tour1_id, image1_3_id);
        tourService.setImage(tour1_id, image1_4_id);

        tourService.setImage(tour2_id, image2_id);
        tourService.setImage(tour2_id, image2_1_id);
        tourService.setImage(tour2_id, image2_2_id);
        tourService.setImage(tour2_id, image2_3_id);
        tourService.setImage(tour2_id, image2_4_id);

        tourService.setImage(tour3_id, image3_id);
        tourService.setImage(tour3_id, image3_1_id);
        tourService.setImage(tour3_id, image3_2_id);
        tourService.setImage(tour3_id, image3_3_id);
        tourService.setImage(tour3_id, image3_4_id);

        tourService.setImage(tour4_id, image4_id);
        tourService.setImage(tour4_id, image4_1_id);
        tourService.setImage(tour4_id, image4_2_id);
        tourService.setImage(tour4_id, image4_3_id);
        tourService.setImage(tour4_id, image4_4_id);

        tourService.setImage(tour5_id, image5_id);
        tourService.setImage(tour5_id, image5_1_id);
        tourService.setImage(tour5_id, image5_2_id);
        tourService.setImage(tour5_id, image5_3_id);
        tourService.setImage(tour5_id, image5_4_id);

        tourService.setImage(tour6_id, image6_id);
        tourService.setImage(tour6_id, image6_1_id);
        tourService.setImage(tour6_id, image6_2_id);
        tourService.setImage(tour6_id, image6_3_id);
        tourService.setImage(tour6_id, image6_4_id);

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
