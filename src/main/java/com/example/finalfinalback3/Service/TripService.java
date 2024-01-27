package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.OrderDTO;
import com.example.finalfinalback3.DTO.TourCutDTO;
import com.example.finalfinalback3.Entity.*;
import com.example.finalfinalback3.Exceptions.DataNotFoundException;
import com.example.finalfinalback3.Model.OrderDetails;
import com.example.finalfinalback3.Repository.TripRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.util.function.SingletonSupplier;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    private final TripRepository tripRepo;
    private final BookingService bookService;
    private final UserService userService;
    private final DateService dateService;
    private final DocumentService docService;
    private final TourService tourService;
    private final ModelMapper modelMapper;

    public TripService(TripRepository tripRepo, BookingService bookService, UserService userService, DateService dateService, DocumentService docService, TourService tourService, ModelMapper modelMapper) {
        this.tripRepo = tripRepo;
        this.bookService = bookService;
        this.userService = userService;
        this.dateService = dateService;
        this.docService = docService;
        this.tourService = tourService;
        this.modelMapper = modelMapper;
    }

    public TripEntity getTripById(Integer id){
        return tripRepo.findById(id).get();
    }

    public TripEntity saveTrip(TripEntity trip){
        return tripRepo.save(trip);
    }

    public OrderDetails showOrderDetails(OrderDTO order) {
        TourCutDTO tour = modelMapper.map(tourService.getTourById(order.getTour_id()), TourCutDTO.class);
        DateEntity date = dateService.getDateById(order.getDate_id());
        return new OrderDetails(tour, date, order.getToken(), order.getPerson_list(), order.getPrice());
    }

    public TripEntity addTrip(OrderDTO order) {
        BookingEntity booking = bookService.saveBook(order.getTour_id(), order.getDate_id());
        List<DocumentEntity> participants = Streamable.of(order.getPerson_list())
                .stream()
                .map(person -> docService.getPersonByToken(person.getToken()))
                .toList();
        Integer people_amount = participants.size();
        if (booking.getTour().getManager() == null){
            throw new DataNotFoundException("У данного тура пока нет менеджера :(");
        }
        String manager_token = booking.getTour().getManager().getToken();
        TripEntity trip = tripRepo.save(new TripEntity(people_amount, booking, participants, manager_token));
        UserEntity user = userService.getUserByToken(order.getToken());
        List<TripEntity> history = user.getHistory();
        history.add(trip);
        user.setHistory(history);
        userService.saveUser(user);
        return trip;
    }

    public List<TripEntity> showHistory(String token){
        UserEntity user = userService.getUserByToken(token);
        List<TripEntity> history = user.getHistory();
        if (history.isEmpty()){
            throw new DataNotFoundException("Путешествий не найдено! Надо исправлять!");
        }
        return history;
    }

    public List<TripEntity> getAllByManager(String manager_token){
        List<TripEntity> trips = tripRepo.findAllByManager(manager_token);
        if (trips.isEmpty()){
            throw new DataNotFoundException("У этого менеджера пока никто не купил туров");
        }
        return trips;
    }

}