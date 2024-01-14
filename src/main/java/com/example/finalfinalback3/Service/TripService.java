package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.OrderDTO;
import com.example.finalfinalback3.DTO.TourCutDTO;
import com.example.finalfinalback3.Entity.BookingEntity;
import com.example.finalfinalback3.Entity.DateEntity;
import com.example.finalfinalback3.Entity.DocumentEntity;
import com.example.finalfinalback3.Entity.TripEntity;
import com.example.finalfinalback3.Model.OrderDetails;
import com.example.finalfinalback3.Repository.TripRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    private final TripRepository tripRepo;
    private final BookingService bookService;
    private final DateService dateService;
    private final DocumentService docService;
    private final TourService tourService;
    private final ModelMapper modelMapper;

    public TripService(TripRepository tripRepo, BookingService bookService, DateService dateService, DocumentService docService, TourService tourService, ModelMapper modelMapper) {
        this.tripRepo = tripRepo;
        this.bookService = bookService;
        this.dateService = dateService;
        this.docService = docService;
        this.tourService = tourService;
        this.modelMapper = modelMapper;
    }

    public OrderDetails showOrderDetails(OrderDTO order) {
        TourCutDTO tour = modelMapper.map(tourService.getTourById(order.getTour_id()), TourCutDTO.class);
        DateEntity date = dateService.getDateById(order.getDate_id());
        return new OrderDetails(tour, date, order.getToken(), order.getPerson_list());
    }

    public TripEntity addTrip(OrderDTO order) {
        BookingEntity booking = bookService.saveBook(order.getTour_id(), order.getDate_id());
        List<DocumentEntity> participants = Streamable.of(order.getPerson_list())
                .stream()
                .map(person -> docService.getPersonByToken(person.getToken()))
                .toList();
        Integer people_amount = participants.size();
        TripEntity trip = new TripEntity(people_amount, booking, participants);
        return tripRepo.save(trip);
    }

}