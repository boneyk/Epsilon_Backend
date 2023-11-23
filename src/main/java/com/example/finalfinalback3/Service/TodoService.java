package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.Entity.TodoEntity;
import com.example.finalfinalback3.Entity.UserEntity;
import com.example.finalfinalback3.Exceptions.UserNotFoundException;
import com.example.finalfinalback3.Model.Todo;
import com.example.finalfinalback3.Repository.TodoRepository;
import com.example.finalfinalback3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepo;
    @Autowired
    private UserRepository userRepo;

    public Todo create(TodoEntity todo, Integer userId) throws UserNotFoundException {
        if (userRepo.findById(userId).get() == null){
            throw new UserNotFoundException("Не удаётся создать задачу - пользователь не найден");
        }
        todo.setUser(userRepo.findById(userId).get());
        return Todo.toModel(todoRepo.save(todo));
    }

    public Todo setComplete(Integer id){
        TodoEntity todo = todoRepo.findById(id).get();
        todo.setIs_completed(!todo.getIs_completed());
        return Todo.toModel(todoRepo.save(todo));
    }
}
