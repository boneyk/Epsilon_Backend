package com.example.finalfinalback3.Repository;

import com.example.finalfinalback3.Entity.BookingEntity;
import com.example.finalfinalback3.Entity.DateEntity;
import com.example.finalfinalback3.Entity.TourEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<BookingEntity, Integer> {
    BookingEntity findByDateAndTour(DateEntity date, TourEntity tour);
}
