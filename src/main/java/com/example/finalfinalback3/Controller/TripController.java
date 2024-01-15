package com.example.finalfinalback3.Controller;

import com.example.finalfinalback3.DTO.OrderDTO;
import com.example.finalfinalback3.Service.BookingService;
import com.example.finalfinalback3.Service.TripService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Контроллер, который отвечает за всё, что происходит после нажатия пользователем кнопки "купить тур"
// Включает в себя создание брони отеля и даты, создание бронирования комнат, связи их в поездку
// И создание поездки.
@CrossOrigin
@RestController
@RequestMapping("/api/trip")
public class TripController {

    private final TripService tripService;
    private final ModelMapper modelMapper;

    public TripController(TripService tripService, ModelMapper modelMapper) {
        this.tripService = tripService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity showOrderDetails(@RequestBody OrderDTO order){
        return new ResponseEntity(tripService.showOrderDetails(order), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addTrip(@RequestBody OrderDTO order){
        return new ResponseEntity(tripService.addTrip(order), HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity showHistory(@RequestParam String token){
        return new ResponseEntity(tripService.showHistory(token), HttpStatus.OK);
    }
}
