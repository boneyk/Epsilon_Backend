package com.example.finalfinalback3.Repository;

import com.example.finalfinalback3.Entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<TodoEntity, Integer> {
}
