package com.example.finalfinalback3.Controller;

import com.example.finalfinalback3.DTO.TourAddDTO;
import com.example.finalfinalback3.Exceptions.AccessException;
import com.example.finalfinalback3.Exceptions.DataAlreadyExistsException;
import com.example.finalfinalback3.Service.ManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    private final ManagerService managerService;
    private final ModelMapper modelMapper;

    public ManagerController(ManagerService managerService, ModelMapper modelMapper) {
        this.managerService = managerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity showAllFree(@RequestParam String token) throws AccessException {
        return new ResponseEntity(managerService.showAllFree(token), HttpStatus.OK);
    }
    @GetMapping("/{token}")
    public ResponseEntity showAllTracable(@PathVariable String token) throws AccessException {
        return new ResponseEntity(managerService.showAllTraceble(token), HttpStatus.OK);
    }
    @GetMapping("/{token}/trips")
    public ResponseEntity showAllTrips(@PathVariable String token) throws AccessException {
        return new ResponseEntity(managerService.showAllTrips(token), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity processOrder(@RequestParam String token,
                                       @RequestParam Boolean decision,
                                       @RequestParam Integer trip_id) throws AccessException {
        return new ResponseEntity(managerService.processOrder(token, decision, trip_id), HttpStatus.OK);
    }
    @PostMapping("/{token}/to/{tour_id}")
    public ResponseEntity setManagerToTour(@PathVariable String token,
                                           @PathVariable Integer tour_id) throws AccessException, DataAlreadyExistsException {
        return new ResponseEntity(managerService.setTourToManager(token, tour_id), HttpStatus.OK);
    }

    @DeleteMapping("/{token}/from/{tour_id}")
    public ResponseEntity removeManagerFromTour(@PathVariable String token,
                                                @PathVariable Integer tour_id) throws AccessException, DataAlreadyExistsException {
        return new ResponseEntity(managerService.removeTourFromManager(token, tour_id), HttpStatus.OK);
    }

    @PatchMapping("/edit/{tour_id}")
    public ResponseEntity editTour(@RequestParam String token,
                                   @PathVariable Integer tour_id,
                                   @RequestBody TourAddDTO tour) throws AccessException {
        return new ResponseEntity(managerService.editTour(tour, tour_id, token), HttpStatus.OK);
    }

    @GetMapping("/trip/{trip_id}")
    public ResponseEntity showTripDetails(@PathVariable Integer trip_id,
                                          @RequestParam String token) throws AccessException {
        return new ResponseEntity(managerService.showTripDetails(trip_id, token), HttpStatus.OK);
    }

}
