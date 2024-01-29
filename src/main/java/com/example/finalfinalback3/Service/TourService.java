package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.TourAddDTO;
import com.example.finalfinalback3.DTO.TourFavoriteDTO;
import com.example.finalfinalback3.DTO.TourMainDTO;
import com.example.finalfinalback3.Entity.ImageEntity;
import com.example.finalfinalback3.Entity.TourEntity;
import com.example.finalfinalback3.Entity.UserEntity;
import com.example.finalfinalback3.Exceptions.DataAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.DataNotFoundException;
import com.example.finalfinalback3.Model.DocPersonalInfo;
import com.example.finalfinalback3.Model.TourDetails;
import com.example.finalfinalback3.Repository.TourRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TourService {

    private final TourRepository tourRepo;
    private final ImageService imageService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public TourService(TourRepository tourRepository, ImageService imageService, UserService userService, ModelMapper modelMapper) {
        this.tourRepo = tourRepository;
        this.imageService = imageService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public TourEntity addTour(TourAddDTO tour) throws DataAlreadyExistsException {
        if (tourRepo.findByName(tour.getName()) != null){
            throw new DataAlreadyExistsException("Такой тур уже существует!");
        }
        return tourRepo.save(modelMapper.map(tour, TourEntity.class));
    }

    public TourEntity saveTour(TourEntity tour){
        return tourRepo.save(tour);
    }

    public List<TourEntity> getAllByManagerIsNull(){
        return tourRepo.findAllByManagerIsNull();
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

    public TourEntity getTourById(Integer tour_id) throws DataNotFoundException{
        Optional<TourEntity> tour = tourRepo.findById(tour_id);
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
        //починить фильтрацию todo
        Iterable<TourEntity> tours = tourRepo.findAllByCountryAndCapacityIsGreaterThanEqual(country_to, amount);
        if (!tours.iterator().hasNext()){
            throw new DataNotFoundException("Туров с такими параметрами не найдено");
        }

        return Streamable.of(tours)
                .stream()
                .map(tour -> modelMapper.map(tour, TourMainDTO.class))
                .toList();
    }

    public List<TourFavoriteDTO> showFavoriteTours(@NonNull String token) throws DataNotFoundException{
        Iterable<TourEntity> tours = tourRepo.findAllByFavorites(userService.getUserByToken(token));
        if (!tours.iterator().hasNext()){
            throw new DataNotFoundException("Избранных туров пока нет. Надо исправлять!");
        }

        return Streamable.of(tours)
                .stream()
                .map(tour -> modelMapper.map(tour, TourFavoriteDTO.class))
                .toList();
    }

    public TourEntity addFavorite(Integer tour_id, String token) throws DataNotFoundException{
        TourEntity tour = getTourById(tour_id);
        UserEntity user = userService.getUserByToken(token);
        List<TourEntity> favorites_list = user.getFavorites();
        favorites_list.add(tour);
        user.setFavorites(favorites_list);
        userService.saveUser(user);
        return tour;
    }

    public UserEntity removeFromFavorite(Integer tour_id, String token) throws DataNotFoundException{
        if (tourRepo.findById(tour_id).isEmpty()){
            throw new DataNotFoundException("Такого тура не cуществует");
        }

        UserEntity user = userService.getUserByToken(token);
        TourEntity tour = tourRepo.findById(tour_id).get();

        if (!user.getFavorites().contains(tour)){
            throw new DataNotFoundException("Такого тура нет в списке избранного у данного пользователя");
        }

        user.getFavorites().remove(tour);
        userService.saveUser(user);
        return user;
    }

    public TourDetails getTourDetails(Integer tourId, String token) {
        TourEntity tour = getTourById(tourId);
        List<DocPersonalInfo> persons = Streamable.of(userService.getUserByToken(token).getDoc())
                .stream()
                .map(doc -> modelMapper.map(doc, DocPersonalInfo.class))
                .toList();
        return new TourDetails(tour, token, persons);
    }

    //Возвращает true, когда есть менеджер
    public boolean isHavingManager(Integer tour_id){
        return getTourById(tour_id).getManager() != null;
    }

}
