package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.DateAddDTO;
import com.example.finalfinalback3.DTO.ImageAddDTO;
import com.example.finalfinalback3.DTO.TourAddDTO;
import com.example.finalfinalback3.DTO.UserRegisterDTO;
import com.example.finalfinalback3.Security.AuthService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Iterator;
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
        List<UserRegisterDTO> user_list = List.of(
            new UserRegisterDTO("Dum@gmail.com", "Hr0mE", "smth", "smth"),
            new UserRegisterDTO("Numbers@gmail.com", "123", "123", "123"),
            new UserRegisterDTO("Not_admin@gmail.com", "Not_admin", "true", "true"),
            new UserRegisterDTO("Petrovich@gmail.com", "Petrovich", "123", "123")
        );
        //user_list.forEach(authService::registration); Почему-то не работает...
        for (UserRegisterDTO user :
                user_list) {
            authService.registration(user);
        }

        //userService.setUserRoleAdmin(user2_id);

        List<List<ImageAddDTO>> images_list = List.of(
                List.of(
                    new ImageAddDTO("egyptanoubis", "/images/", "anoubis", "1920x1080", ".png"),
                    new ImageAddDTO("egtparam", "/images/", "anoubis", "1920x1080", ".png"),
                    new ImageAddDTO("egtphoto1", "/images/", "anoubis", "1920x1080", ".png"),
                    new ImageAddDTO("egtphoto2", "/images/", "anoubis", "1920x1080", ".png"),
                    new ImageAddDTO("egtphoto3", "/images/", "anoubis", "1920x1080", ".png")
                ),
                List.of(
                    new ImageAddDTO("thailandbangkok", "/images/", "bangkok", "1920x1080", ".png"),
                    new ImageAddDTO("thaiparam", "/images/", "bangkok", "1920x1080", ".png"),
                    new ImageAddDTO("thaiphoto1", "/images/", "bangkok", "1920x1080", ".png"),
                    new ImageAddDTO("thaiphoto2", "/images/", "bangkok", "1920x1080", ".png"),
                    new ImageAddDTO("thaiphoto3", "/images/", "bangkok", "1920x1080", ".png")
                ),
                List.of(
                    new ImageAddDTO("japantokyo", "/images/", "japantokyo", "1920x1080", ".png"),
                    new ImageAddDTO("japparam", "/images/", "japantokyo", "1920x1080", ".png"),
                    new ImageAddDTO("japphoto1", "/images/", "japantokyo", "1920x1080", ".png"),
                    new ImageAddDTO("japphoto2", "/images/", "japantokyo", "1920x1080", ".png"),
                    new ImageAddDTO("japphoto3", "/images/", "japantokyo", "1920x1080", ".png")
                ),
                List.of(
                    new ImageAddDTO("koreaseoul", "/images/", "koreaseoul", "1920x1080", ".png"),
                    new ImageAddDTO("korparam", "/images/", "koreaseoul", "1920x1080", ".png"),
                    new ImageAddDTO("korphoto1", "/images/", "koreaseoul", "1920x1080", ".png"),
                    new ImageAddDTO("korphoto2", "/images/", "koreaseoul", "1920x1080", ".png"),
                    new ImageAddDTO("korphoto3", "/images/", "koreaseoul", "1920x1080", ".png")
                ),
                List.of(
                    new ImageAddDTO("americamaiami", "/images/", "americamaiami", "1920x1080", ".png"),
                    new ImageAddDTO("maiparam", "/images/", "americamaiami", "1920x1080", ".png"),
                    new ImageAddDTO("maiphoto1", "/images/", "americamaiami", "1920x1080", ".png"),
                    new ImageAddDTO("maiphoto2", "/images/", "americamaiami", "1920x1080", ".png"),
                    new ImageAddDTO("maiphoto3", "/images/", "americamaiami", "1920x1080", ".png")
                ),
                List.of(
                    new ImageAddDTO("russiabaikal", "/images/", "russiabaikal", "1920x1080", ".png"),
                    new ImageAddDTO("baiparam", "/images/", "russiabaikal", "1920x1080", ".png"),
                    new ImageAddDTO("baiphoto1", "/images/", "russiabaikal", "1920x1080", ".png"),
                    new ImageAddDTO("baiphoto2", "/images/", "russiabaikal", "1920x1080", ".png"),
                    new ImageAddDTO("baiphoto3", "/images/", "russiabaikal", "1920x1080", ".png")
                )
        );

        for (List<ImageAddDTO> list: images_list){
            for (ImageAddDTO image : list) {
                imageService.addImage(image);
            }
        }

        List<TourAddDTO> tour_list = List.of(
            new TourAddDTO("Горячая путёвка в Египет!", "Египет", "Анубис", "Международный", 30, 99999, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf"),
            new TourAddDTO("Гостеприимный Тайланд", "Тайланд", "Бангкок", "Международный", 64, 150156, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf"),
            new TourAddDTO("Страна восходящего Солнца!", "Япония", "Токио", "Международный", 15, 90000, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf"),
            new TourAddDTO("Страна будущего!", "Корея", "Сеул",  "Международный", 100, 200000, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf"),
            new TourAddDTO("Бархатистые пески Майями!", "Америка", "Майями",  "Международный", 10, 502000, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf"),
            new TourAddDTO("Зимний Байкал", "Россия", "Байкал", "По России", 120, 50000, "some description here. Lorem ipsum sol amer grou fat yvur sop gtariqus divun gopraf")
        );

        for (TourAddDTO tour: tour_list) {
            tourService.addTour(tour);
        }


        // Дай угадаю, поменял способ индексирования сущностей и вылезла ошибка? ;)
        // TODO ПРИДУМАТЬ СПОСОБ СДЕЛАТЬ ЭТО НАДЁЖНЕЕ
        // Тогда теперь это твоя проблема :D
        Integer image_counter = 1, tour_counter = 1;
        for (List<ImageAddDTO> image_list: images_list){
            for (ImageAddDTO image: image_list){
                tourService.setImage(tour_counter, image_counter++);
            }
            tour_counter++;
        }

        List<DateAddDTO> date_list = List.of(
                new DateAddDTO(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 2, 1)),
                new DateAddDTO(LocalDate.of(2024, 7, 15), LocalDate.of(2024, 8, 1)),
                new DateAddDTO(LocalDate.of(2024, 8, 1), LocalDate.of(2024, 8, 16)),
                new DateAddDTO(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 7)),
                new DateAddDTO(LocalDate.of(2024, 4, 7), LocalDate.of(2024, 4, 21)),
                new DateAddDTO(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 5, 6)),
                new DateAddDTO(LocalDate.of(2024, 12, 27), LocalDate.of(2024, 12, 30)),
                new DateAddDTO(LocalDate.of(2024, 12, 15), LocalDate.of(2025, 1, 15)),
                new DateAddDTO(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 8)),
                new DateAddDTO(LocalDate.of(2024, 10, 10), LocalDate.of(2024, 12, 1)),
                new DateAddDTO(LocalDate.of(2024, 3, 15), LocalDate.of(2024, 3, 21))
        );

        for (DateAddDTO date : date_list) {
            dateService.addTourToDate(date, 1 + date_list.indexOf(date)/2);
        }
        dateService.addTourToDate(
                new DateAddDTO(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 8)),
                6);

        //PersonalInfoAddDTO person1 = new PersonalInfoAddDTO("Epsilon Developer team", "+79991115050");

        //userService.addPersonalInfo(person1, user2_token.getToken());
    }

}
