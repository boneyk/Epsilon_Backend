package com.example.finalfinalback3.Controller;

import com.example.finalfinalback3.DTO.ImageAddDTO;
import com.example.finalfinalback3.DTO.TourAddDTO;
import com.example.finalfinalback3.Entity.TourEntity;
import com.example.finalfinalback3.Entity.UserEntity;
import com.example.finalfinalback3.Exceptions.AccessException;
import com.example.finalfinalback3.Exceptions.DataAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.DataNotFoundException;
import com.example.finalfinalback3.Model.AdminManager;
import com.example.finalfinalback3.Service.ImageService;
import com.example.finalfinalback3.Service.ManagerService;
import com.example.finalfinalback3.Service.TourService;
import com.example.finalfinalback3.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final TourService tourService;
    private final ImageService imageService;
    private final ManagerService managerService;
    private final ModelMapper modelMapper;

    public AdminController(UserService userService, TourService tourService, ImageService imageService, ManagerService managerService, ModelMapper modelMapper) {
        this.userService = userService;
        this.tourService = tourService;
        this.imageService = imageService;
        this.managerService = managerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity getUserById(@PathVariable Integer user_id){
            UserEntity user = userService.getUserById(user_id);
            return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity showAllUsers() {
        List<UserEntity> user_list = userService.showAll();
        return new ResponseEntity(user_list, HttpStatus.OK);
    }

    @GetMapping("/users/manager")
    public ResponseEntity showAllManagers(@RequestParam String admin_token){
        String role = "MANAGER";
        List<AdminManager> manager_list = Streamable.of(userService.getAllByRole(role))
                .stream()
                .map(manager -> modelMapper.map(manager, AdminManager.class))
                .toList();
        return new ResponseEntity(manager_list, HttpStatus.OK);
    }

    @PostMapping("/users/manager/{manager_token}")
    public ResponseEntity setUserRoleManager(@RequestParam String admin_token,
                                             @PathVariable String manager_token) throws AccessException {
        userService.setUserRoleManager(admin_token, manager_token);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/users/manager/{ex_manager_token}/fire")
    public ResponseEntity setUserRoleUser(@RequestParam String admin_token,
                                          @PathVariable String ex_manager_token) throws AccessException {
        userService.setUserRoleUser(admin_token, ex_manager_token);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/users/manager/{manager_token}/tours/{tour_id}")
    public ResponseEntity setTourToManager(@RequestParam String admin_token,
                                           @PathVariable String manager_token,
                                           @PathVariable Integer tour_id) throws AccessException, DataAlreadyExistsException {

        managerService.AdminSetTourToManager(admin_token, manager_token, tour_id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/users/manager/{manager_token}/tours/{tour_id}")
    public ResponseEntity removeTourFromManager(@RequestParam String admin_token,
                                                @PathVariable String manager_token,
                                                @PathVariable Integer tour_id) throws AccessException, DataAlreadyExistsException {

        managerService.AdminRemoveTourFromManager(admin_token, manager_token, tour_id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/tours/{id}")
    public ResponseEntity getTourById(@PathVariable Integer id){
        TourEntity tour = tourService.getTourById(id);
        return new ResponseEntity(tour, HttpStatus.OK);
    }

    @GetMapping("/tours")
    public ResponseEntity showAllTours(){
        List<TourEntity> tour_list = tourService.adminShowAll();
        return new ResponseEntity(tour_list, HttpStatus.OK);
    }

    @PostMapping("/tours")
    public ResponseEntity addTour(@RequestBody TourAddDTO tour) throws DataAlreadyExistsException {
        tourService.addTour(tour);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/images")
    public ResponseEntity addImage(@RequestBody ImageAddDTO image) throws DataAlreadyExistsException {
        imageService.addImage(image);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping
    public String post(){
        return "POST:: admin controller";
    }

    @PutMapping("/tours/{tour_id}/images/{image_id}")
    public ResponseEntity addImageToTour(@PathVariable Integer tour_id,
                      @PathVariable Integer image_id) throws DataNotFoundException{
        tourService.setImage(tour_id, image_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public String delete(){
        return "DELETE:: admin controller";
    }
}
