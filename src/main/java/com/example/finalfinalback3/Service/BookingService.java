package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.BookAddDTO;
import com.example.finalfinalback3.Entity.BookingEntity;
import com.example.finalfinalback3.Entity.DateEntity;
import com.example.finalfinalback3.Entity.TourEntity;
import com.example.finalfinalback3.Exceptions.DataNotFoundException;
import com.example.finalfinalback3.Repository.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookRepo;
    private final ModelMapper modelMapper;
    private final TourService tourService;
    private final DateService dateService;

    public BookingService(BookingRepository bookRepo, ModelMapper modelMapper, TourService tourService, DateService dateService) {
        this.bookRepo = bookRepo;
        this.modelMapper = modelMapper;
        this.tourService = tourService;
        this.dateService = dateService;
    }

    public BookingEntity saveBook(Integer tour_id, Integer date_id) throws DataNotFoundException{
        TourEntity tour = tourService.getTourById(tour_id);
        DateEntity date = dateService.getDateById(date_id);
        BookAddDTO book = new BookAddDTO(tour, date);
        return bookRepo.save(modelMapper.map(book, BookingEntity.class));
    }

    public BookingEntity getBookById(Integer id) throws DataNotFoundException{
        Optional<BookingEntity> book = bookRepo.findById(id);
        if (book.isEmpty()){
            throw new DataNotFoundException(new StringBuilder().append("Бронирования с id ").append(id).append(" не найдено").toString());
        }
        return book.get();
    }


}
