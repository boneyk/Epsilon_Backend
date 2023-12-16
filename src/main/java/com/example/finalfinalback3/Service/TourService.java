package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.TourAddDTO;
import com.example.finalfinalback3.DTO.TourMainDTO;
import com.example.finalfinalback3.Entity.ImageEntity;
import com.example.finalfinalback3.Entity.TourEntity;
import com.example.finalfinalback3.Exceptions.DataAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.DataNotFoundException;
import com.example.finalfinalback3.Repository.TourRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Streamable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TourService {

    private final TourRepository tourRepo;
    private final ImageService imageService;
    private final ModelMapper modelMapper;


    public TourService(TourRepository tourRepository, ImageService imageService, ModelMapper modelMapper) {
        this.tourRepo = tourRepository;
        this.imageService = imageService;
        this.modelMapper = modelMapper;
    }

    public TourEntity addTour(TourAddDTO tour) throws DataAlreadyExistsException {
        if (tourRepo.findByName(tour.getName()) != null){
            throw new DataAlreadyExistsException("Такой тур уже существует!");
        }
        return tourRepo.save(modelMapper.map(tour, TourEntity.class));
    }

    public List<TourEntity> adminShowAll() throws DataNotFoundException{
        if (!tourRepo.findAll().iterator().hasNext()){
            throw new DataNotFoundException("Упс, похоже, сейчас туров нету. Все забрали!");
        }
        return Streamable.of(tourRepo.findAll()).stream().toList();
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
            throw new DataNotFoundException("Тур с таким id не найден");
        }
        return tour.get();
    }

    public ImageEntity setImage(Integer tour_id, Integer image_id) throws DataNotFoundException{

        TourEntity tour = getTourById(tour_id);
        ImageEntity image = imageService.getImageById(image_id);
        List<ImageEntity> new_images = tour.getImages();
        new_images.add(image);
        tour.setImages(new_images);
        tourRepo.save(tour);
        return image;
    }

    public List<TourMainDTO> showFilteredTours(@NonNull String country_to,
                                               LocalDate date_start,
                                               Integer nights,
                                               @NonNull Integer amount) throws DataNotFoundException{
        Iterable<TourEntity> tours = tourRepo.findAllByCountryAndCapacityIsGreaterThanEqual(country_to, amount);
        if (!tours.iterator().hasNext()){
            throw new DataNotFoundException("Туров с такими параметрами не найдено");
        }

        return Streamable.of(tours)
                .stream()
                .map(tour -> modelMapper.map(tour, TourMainDTO.class))
                .toList();
    }

}
