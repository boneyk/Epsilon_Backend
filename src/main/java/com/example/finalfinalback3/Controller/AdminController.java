package com.example.finalfinalback3.Controller;

import com.example.finalfinalback3.DTO.TourAddDTO;
import com.example.finalfinalback3.DTO.TourMainDTO;
import com.example.finalfinalback3.Entity.TourEntity;
import com.example.finalfinalback3.Entity.UserEntity;
import com.example.finalfinalback3.Exceptions.DataAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.DataNotFoundException;
import com.example.finalfinalback3.Service.TourService;
import com.example.finalfinalback3.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final TourService tourService;

    public AdminController(UserService userService, TourService tourService) {
        this.userService = userService;
        this.tourService = tourService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id){
        try {
            UserEntity user = userService.getSingleUser(id);
            return new ResponseEntity(user, HttpStatus.OK);
        }
        catch (DataNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public ResponseEntity showAllUsers() {
        try {
            List<UserEntity> user_list = userService.showAll();
            return new ResponseEntity(user_list, HttpStatus.OK);
        } catch (DataNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tours/{id}")
    public ResponseEntity getTourById(@PathVariable Integer id){
        try{
            TourEntity tour = tourService.getTourById(id);
            return new ResponseEntity(tour, HttpStatus.OK);
        } catch (DataNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tours")
    public ResponseEntity showAllTours(){
        try {
            List<TourMainDTO> tour_list = tourService.showAll();
            return new ResponseEntity(tour_list, HttpStatus.OK);
    } catch (DataNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tours")
    public ResponseEntity addTour(@RequestBody TourAddDTO tour){
        try {
            tourService.addTour(tour);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch (DataAlreadyExistsException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public String post(){
        return "POST:: admin controller";
    }

    @PutMapping
    public String Put(){
        return "PUT:: admin controller";
    }

    @DeleteMapping
    public String delete(){
        return "DELETE:: admin controller";
    }

}
