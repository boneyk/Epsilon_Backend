package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.TourAddDTO;
import com.example.finalfinalback3.DTO.TourMainDTO;
import com.example.finalfinalback3.Entity.TourEntity;
import com.example.finalfinalback3.Exceptions.DataAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.DataNotFoundException;
import com.example.finalfinalback3.Repository.TourRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {

    private final TourRepository tourRepo;
    private final ModelMapper modelMapper;


    public TourService(TourRepository tourRepository, ModelMapper modelMapper) {
        this.tourRepo = tourRepository;
        this.modelMapper = modelMapper;
    }

    public TourEntity addTour(TourAddDTO tour) throws DataAlreadyExistsException {
        if (tourRepo.findByName(tour.getName()) != null){
            throw new DataAlreadyExistsException("Такой тур уже существует!");
        }
        return tourRepo.save(modelMapper.map(tour, TourEntity.class));
    }

    public List<TourMainDTO> showAll() throws DataNotFoundException {
        if (!tourRepo.findAll().iterator().hasNext()){
            throw new DataNotFoundException("Упс, похоже, сейчас туров нету. Все забрали!");
        }
        return Streamable.of(tourRepo.findAll())
                .stream()
                .map(tour -> modelMapper.map(tour, TourMainDTO.class))
                .toList();
    }

    public TourEntity getTourById(Integer id) throws DataNotFoundException{
        Optional<TourEntity> tour = tourRepo.findById(id);
        if (tour.isEmpty()){
            throw new DataNotFoundException("Тура с таким id не найдено");
        }
        return tour.get();
    }

}
