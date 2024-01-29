package com.example.finalfinalback3.Repository;

import com.example.finalfinalback3.Entity.ImageEntity;
import com.example.finalfinalback3.Entity.TourEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<ImageEntity, Integer> {
    ImageEntity findByFilename(String filename);
    List<ImageEntity> findAllByToursContains(TourEntity tour);
}
