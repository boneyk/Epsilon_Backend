package com.example.finalfinalback3.Controller;

import com.example.finalfinalback3.DTO.DateAddDTO;
import com.example.finalfinalback3.Entity.DateEntity;
import com.example.finalfinalback3.Entity.UserEntity;
import com.example.finalfinalback3.Exceptions.DataAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.DataNotFoundException;
import com.example.finalfinalback3.Model.DateWindow;
import com.example.finalfinalback3.Service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/date")
public class DateController{

    private final DateService dateService;

    @Autowired
    public DateController(DateService dateService) {
        this.dateService = dateService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getDateWindowById(@PathVariable Integer id){
        try {
            DateWindow date = dateService.getDateWindowById(id);
            return new ResponseEntity(date, HttpStatus.OK);
        }
        catch (DataNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/add")
    public ResponseEntity addTourToDate(@RequestParam Integer tour_id,
                                        @RequestBody DateAddDTO date){
        try {
            dateService.addTourToDate(date, tour_id);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (DataAlreadyExistsException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity showDateByTourId(@RequestParam Integer tour_id){
        try {
            List<DateWindow> date = dateService.showDateByTourId(tour_id);
            return new ResponseEntity(date, HttpStatus.OK);
        }
        catch (DataNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteDateWindowById(@PathVariable Integer id){
        try {
            DateEntity date = dateService.deleteDateById(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (DataNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
