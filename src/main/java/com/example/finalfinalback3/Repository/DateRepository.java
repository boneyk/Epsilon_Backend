package com.example.finalfinalback3.Repository;

import com.example.finalfinalback3.Entity.DateEntity;
import com.example.finalfinalback3.Entity.TourEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface DateRepository extends CrudRepository<DateEntity, Integer> {
    List<DateEntity> findAllByTour(TourEntity tour);
    List<DateEntity> findAllByDateStart(LocalDate date);
    //void deleteAllByTour(Integer tour_id);
    DateEntity findByDateEndAndDateStartAndTour(LocalDate date_end, LocalDate date_start, TourEntity tour);
}
