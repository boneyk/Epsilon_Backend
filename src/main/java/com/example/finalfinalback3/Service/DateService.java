package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.DateAddDTO;
import com.example.finalfinalback3.Entity.DateEntity;
import com.example.finalfinalback3.Entity.TourEntity;
import com.example.finalfinalback3.Exceptions.DataAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.DataNotFoundException;
import com.example.finalfinalback3.Model.DateWindow;
import com.example.finalfinalback3.Repository.DateRepository;
import org.hibernate.internal.util.collections.SingletonIterator;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class DateService {
    private final DateRepository dateRepo;
    private final TourService tourService;
    private final ModelMapper modelMapper;

    public DateService(DateRepository dateRepo, TourService tourService, ModelMapper modelMapper) {
        this.dateRepo = dateRepo;
        this.tourService = tourService;
        this.modelMapper = modelMapper;
    }

    public DateEntity saveDate(DateEntity date, TourEntity tour) throws DataAlreadyExistsException {
        if (dateRepo.findByDateEndAndDateStartAndTourContains(date.getDateEnd(), date.getDateStart(), tour) != null){
            throw new DataAlreadyExistsException("Такой временной промежуток уже существует");
        }
        return dateRepo.save(date);
    }

    public List<DateWindow> showDateByTourId(Integer tour_id) throws DataNotFoundException{
        TourEntity tour = tourService.getTourById(tour_id);
        if (dateRepo.findAllByTourContains(tour).isEmpty()){
            throw new DataNotFoundException("Временных промежутков для данного тура пока нет");
        }
        return Streamable.of(dateRepo.findAllByTourContains(tour))
                .stream()
                .map(date -> modelMapper.map(date, DateWindow.class))
                .toList();
    }

    public DateEntity getDateById(Integer date_id) throws DataNotFoundException{
        Optional<DateEntity> date = dateRepo.findById(date_id);
        if (date.isEmpty()){
            throw new DataNotFoundException("Такого временного промежутка не найдено");
        }
        return date.get();
    }

    public DateWindow getDateWindowById(Integer date_id){
        DateEntity date = getDateById(date_id);
        return modelMapper.map(date, DateWindow.class);
    }

    public Integer addTourToDate(DateAddDTO date, Integer tour_id) throws DataAlreadyExistsException {
        DateEntity new_date = modelMapper.map(date, DateEntity.class);
        TourEntity tour = tourService.getTourById(tour_id);
        List<TourEntity> tours = new_date.getTour();
        if (tours == null){
            new_date.setTour(List.of(tour));
        }
        else {
            tours.add(tour);
            new_date.setTour(tours);
        }
        return saveDate(new_date, tour).getId();
    }

    public DateEntity deleteDateById(Integer date_id) {
        DateEntity date = getDateById(date_id);
        dateRepo.delete(date);
        return date;
    }
}
