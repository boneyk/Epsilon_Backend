package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.DateAddDTO;
import com.example.finalfinalback3.DTO.ImageAddDTO;
import com.example.finalfinalback3.DTO.TourAddDTO;
import com.example.finalfinalback3.DTO.TourCutDTO;
import com.example.finalfinalback3.Entity.*;
import com.example.finalfinalback3.Exceptions.AccessException;
import com.example.finalfinalback3.Exceptions.DataAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.DataNotFoundException;
import com.example.finalfinalback3.Model.DocPersonalInfo;
import com.example.finalfinalback3.Model.ManagerTour;
import com.example.finalfinalback3.Model.ManagerTrip;
import com.example.finalfinalback3.Model.OrderDetails;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ManagerService {

    private final UserService userService;
    private final TourService tourService;
    private final TripService tripService;
    private final ModelMapper modelMapper;
    private final ImageService imageService;
    private final DateService dateService;

    public ManagerService(UserService userService, TourService tourService, TripService tripService, ModelMapper modelMapper, ImageService imageService, DateService dateService) {
        this.userService = userService;
        this.tourService = tourService;
        this.tripService = tripService;
        this.modelMapper = modelMapper;
        this.imageService = imageService;
        this.dateService = dateService;
    }

    public TourEntity setTourToManager(String token, Integer tour_id) throws AccessException, DataAlreadyExistsException {
        if (!userService.isManager(token)){
            throw new AccessException("Нельзя наначить на тур кого-то, кто не является менеджером");
        }
        TourEntity tour = tourService.getTourById(tour_id);
        UserEntity manager = userService.getUserByToken(token);

        if (tourService.isHavingManager(tour_id)){
            throw new DataAlreadyExistsException("Данному туру уже назначен свой менеджер");
        }

        tour.setManager(manager);
        List<TourEntity> traceable = manager.getTraceble();
        traceable.add(tour);
        manager.setTraceble(traceable);
        userService.saveUser(manager);
        return tourService.saveTour(tour);
    }

    public TourEntity AdminSetTourToManager(String admin_token, String token, Integer tour_id) throws AccessException, DataAlreadyExistsException {
        if (!userService.isAdmin(admin_token)){
            throw new AccessException("Вы - не администратор!");
        }
        if (!userService.isManager(token)){
            throw new AccessException("Нельзя наначить на тур кого-то, кто не является менеджером");
        }
        if (tourService.isHavingManager(tour_id)){
            throw new DataAlreadyExistsException("Данному туру уже назначен свой менеджер");
        }
        TourEntity tour = tourService.getTourById(tour_id);
        UserEntity manager = userService.getUserByToken(token);



        tour.setManager(manager);
        List<TourEntity> traceable = manager.getTraceble();
        traceable.add(tour);
        manager.setTraceble(traceable);
        userService.saveUser(manager);
        return tourService.saveTour(tour);
    }

    public TourEntity removeTourFromManager(String token, Integer tour_id) throws AccessException, DataAlreadyExistsException {
        if (!userService.isManager(token)){
            throw new AccessException("Нельзя снять с тура того, кто не является менеджером!");
        }
        if (!tourService.isHavingManager(tour_id)){
            throw new DataNotFoundException("Нельзя удалить менеджера, потому что он не назначен");
        }
        TourEntity tour = tourService.getTourById(tour_id);
        UserEntity manager = userService.getUserByToken(token);

        if (!tour.getManager().getToken().equals(token)){
            throw new AccessException("Нельзя удалить не свой тур");
        }

        tour.setManager(null);
        List<TourEntity> traceable = manager.getTraceble();
        traceable.remove(tour);
        manager.setTraceble(traceable);
        userService.saveUser(manager);
        return tourService.saveTour(tour);
    }
    public TourEntity AdminRemoveTourFromManager(String admin_token,String token, Integer tour_id) throws AccessException, DataAlreadyExistsException {
        if (!userService.isAdmin(admin_token)){
            throw new AccessException("Вы - не администратор!");
        }
        if (!userService.isManager(token)){
            throw new AccessException("Нельзя снять с тура того, кто не является менеджером!");
        }
        if (!tourService.isHavingManager(tour_id)){
            throw new DataNotFoundException("Нельзя удалить менеджера, потому что он не назначен");
        }
        TourEntity tour = tourService.getTourById(tour_id);
        UserEntity manager = userService.getUserByToken(token);

        if (tour.getManager() != manager){
            throw new AccessException("Нельзя удалить не свой тур");
        }

        tour.setManager(null);
        List<TourEntity> traceable = manager.getTraceble();
        traceable.remove(tour);
        manager.setTraceble(traceable);
        userService.saveUser(manager);
        return tourService.saveTour(tour);
    }

    public List<ManagerTour> showAllTraceble(String token) throws AccessException {
        if (!userService.isManager(token)){
            throw new AccessException("Данный пользователь - не менеджер");
        }
        UserEntity manager = userService.getUserByToken(token);
        if (manager.getTraceble().isEmpty()){
            throw new DataNotFoundException("Пока нет курируемых туров!");
        }
        return Streamable.of(manager.getTraceble())
                .stream()
                .map(tour -> modelMapper.map(tour, ManagerTour.class))
                .toList();
    }

    public List<ManagerTour> showAllFree(String token) throws AccessException {
        if (!userService.isManager(token)){
            throw new AccessException("Данный пользователь - не менеджер");
        }
        List<TourEntity> tours = tourService.getAllByManagerIsNull();
        if (tours.isEmpty()){
            throw new DataNotFoundException("Похоже, что пока свободных туров нету!");
        }
        return Streamable.of(tours)
                .stream()
                .map(tour -> modelMapper.map(tour, ManagerTour.class))
                .toList();
    }

    public List<ManagerTrip> showAllTrips(String token) throws AccessException {
        if (!userService.isManager(token)){
            throw new AccessException("Данный пользователь - не менеджер");
        }
        List<TripEntity> trip_list = tripService.getAllByManager(token);
        return Streamable.of(trip_list)
                .stream()
                .map(trip -> new ManagerTrip(
                        trip.getId(),
                        trip.getUser().getLogin(),
                        trip.getBookingEntity().getTour().getCountry(),
                        trip.getBookingEntity().getTour().getCity(),
                        trip.getPrice(),
                        trip.getStatus()))
                .toList();
    }

    public TripEntity processOrder(String token, Boolean decision, Integer trip_id) throws AccessException {
        String positive = "ACCEPTED";
        String negative = "DENIED";

        if (!userService.isManager(token)){
            throw new AccessException("Данный пользователь - не менеджер");
        }
        TripEntity trip = tripService.getTripById(trip_id);

        if (decision) { trip.setStatus(positive); }
        else { trip.setStatus(negative); }

        return tripService.saveTrip(trip);
    }

    public void deleteTour(Integer tour_id, String token) throws AccessException {
        if (!userService.isManager(token)){
            throw new AccessException("Данный пользователь - не менеджер");
        }
        if (!tourService.isHavingManager(tour_id)){
            throw new DataNotFoundException("Нельзя удалить тур, у которого нет менеджера");
        }
        TourEntity tour = tourService.getTourById(tour_id);
        UserEntity manager = userService.getUserByToken(token);

        if (tour.getManager() != manager){
            throw new AccessException("Нельзя удалить не свой тур");
        }

        dateService.deleteTourAndDate(tour);
    }

    public TourEntity editTourInfo(TourAddDTO new_tour_info, Integer tour_id, String token) throws AccessException {
        if (!userService.isManager(token)){
            throw new AccessException("Данный пользователь - не менеджер");
        }
        if (!tourService.isHavingManager(tour_id)){
            throw new DataNotFoundException("Нельзя изменить тур, у которого нет менеджера");
        }
        TourEntity tour = tourService.getTourById(tour_id);
        UserEntity manager = userService.getUserByToken(token);

        if (tour.getManager() != manager){
            throw new AccessException("Нельзя изменить не свой тур");
        }

        if (new_tour_info.getCity() != null) tour.setCity(new_tour_info.getCity());
        if (new_tour_info.getCountry() != null) tour.setCountry(new_tour_info.getCountry());
        if (new_tour_info.getTour_type() != null) tour.setTour_type(new_tour_info.getTour_type());
        if (new_tour_info.getName() != null) tour.setName(new_tour_info.getName());
        if (new_tour_info.getDescription() != null) tour.setDescription(new_tour_info.getDescription());
        if (new_tour_info.getCapacity() != null) tour.setCapacity(new_tour_info.getCapacity());

        return tourService.saveTour(tour);
    }
    public DateEntity editDate(DateAddDTO newDate, Integer dateId, Integer tour_id, String token) throws AccessException, DataAlreadyExistsException {
        if (!userService.isManager(token)){
            throw new AccessException("Данный пользователь - не менеджер");
        }
        if (!tourService.isHavingManager(tour_id)){
            throw new DataNotFoundException("Нельзя изменить тур, у которого нет менеджера");
        }

        DateEntity date = dateService.getDateById(dateId);
        TourEntity tour = tourService.getTourById(tour_id);
        UserEntity manager = userService.getUserByToken(token);

        if (tour.getManager() != manager){
            throw new AccessException("Нельзя изменить не свой тур");
        }
        if (newDate.getDateStart() != null) date.setDateStart(newDate.getDateStart());
        if (newDate.getDateEnd() != null) date.setDateEnd(newDate.getDateEnd());

        return dateService.saveDate(date, tour);
    }

    public ImageEntity editImage(ImageAddDTO newImage, Integer imageId, Integer tourId, String token) throws AccessException {
        if (!userService.isManager(token)){
            throw new AccessException("Данный пользователь - не менеджер");
        }
        if (!tourService.isHavingManager(tourId)){
            throw new DataNotFoundException("Нельзя изменить тур, у которого нет менеджера");
        }

        ImageEntity image = imageService.getImageById(imageId);
        TourEntity tour = tourService.getTourById(tourId);
        UserEntity manager = userService.getUserByToken(token);

        if (tour.getManager() != manager){
            throw new AccessException("Нельзя изменить не свой тур");
        }
        if (newImage.getFilename() != null) image.setFilename(newImage.getFilename());
        if (newImage.getSize() != null) image.setSize(newImage.getSize());
        if (newImage.getPath() != null) image.setPath(newImage.getPath());
        if (newImage.getAlt() != null) image.setAlt(newImage.getAlt());
        if (newImage.getContent_type() != null) image.setContent_type(newImage.getContent_type());

        return imageService.saveImage(image);
    }

    public OrderDetails showTripDetails(Integer trip_id, String token) throws AccessException {
        if (!userService.isManager(token)){
            throw new AccessException("Данный пользователь - не менеджер");
        }

        TripEntity trip = tripService.getTripById(trip_id);
        OrderDetails orderDetails = new OrderDetails();

        if (!trip.getBookingEntity().getTour().getManager().getToken().equals(token)){
            throw new AccessException("Вы не курируете этот тур, чтобы просматривать информацию о поездке");
        }

        orderDetails.setDate(trip.getBookingEntity().getDate());
        orderDetails.setTour(modelMapper.map(trip.getBookingEntity().getTour(), TourCutDTO.class));
        orderDetails.setToken(trip.getUser().getToken());
        orderDetails.setPrice(trip.getPrice());
        orderDetails.setPerson_list(Streamable.of(trip.getParticipants())
                .stream()
                .map(person -> modelMapper.map(person, DocPersonalInfo.class))
                .toList());
        orderDetails.setStatus(trip.getStatus());
        return orderDetails;
    }

    public void loadAndAddImageToTour(String token, Integer tour_id, ImageAddDTO image) throws DataAlreadyExistsException, AccessException {
        if (!userService.isManager(token)){
            throw new AccessException("Данный пользователь - не менеджер");
        }
        if (!tourService.isHavingManager(tour_id)){
            throw new DataNotFoundException("Нельзя изменить тур, у которого нет менеджера");
        }

        TourEntity tour = tourService.getTourById(tour_id);
        UserEntity manager = userService.getUserByToken(token);

        if (tour.getManager() != manager){
            throw new AccessException("Нельзя изменить не свой тур");
        }

        ImageEntity imageEntity = imageService.addImage(image);
        tourService.setImage(tour_id, imageEntity.getId());
    }


    public void removeImageFromTour(String token, Integer tourId, Integer imageId) throws AccessException {
        if (!userService.isManager(token)){
            throw new AccessException("Данный пользователь - не менеджер");
        }
        if (!tourService.isHavingManager(tourId)){
            throw new DataNotFoundException("Нельзя изменить тур, у которого нет менеджера");
        }

        ImageEntity image = imageService.getImageById(imageId);
        TourEntity tour = tourService.getTourById(tourId);
        UserEntity manager = userService.getUserByToken(token);

        if (tour.getManager() != manager){
            throw new AccessException("Нельзя изменить не свой тур");
        }

        List<ImageEntity> image_list = tour.getImages();
        image_list.remove(image);

        tour.setImages(image_list);
        tourService.saveTour(tour);
    }
}
