package com.example.finalfinalback3.Repository;

import com.example.finalfinalback3.Entity.TripEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TripRepository extends CrudRepository<TripEntity, Integer> {
    List<TripEntity> findAllByManager(String token);
}
