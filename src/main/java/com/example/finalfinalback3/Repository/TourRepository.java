package com.example.finalfinalback3.Repository;

import com.example.finalfinalback3.Entity.TourEntity;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<TourEntity, Integer> {

    TourEntity findByName(String name);
}
