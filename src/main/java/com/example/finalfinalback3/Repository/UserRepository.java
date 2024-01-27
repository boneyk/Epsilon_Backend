package com.example.finalfinalback3.Repository;

import com.example.finalfinalback3.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByToken(String token);
    List<UserEntity> findAllByRole(String role);
}
