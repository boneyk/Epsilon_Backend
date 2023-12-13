package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.Entity.UserEntity;
import com.example.finalfinalback3.Exceptions.DataNotFoundException;
import com.example.finalfinalback3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    //Спросить насчёт использования Optional
    public UserEntity getSingleUser(Integer id) throws DataNotFoundException {
        if (userRepo.findById(id).get() == null) {
            throw new DataNotFoundException("Пользователя с таким id не существует!");
        }
        return userRepo.findById(id).get();
    }

    public List<UserEntity> showAll() throws DataNotFoundException{
        if (userRepo.findAll() == null){
            throw new DataNotFoundException("Нет пользователей в базе данных...");
        }
        return (List<UserEntity>) userRepo.findAll();
    }

    public Integer deleteUser(Integer id) throws DataNotFoundException {
        if (userRepo.findById(id) == null) {
            throw new DataNotFoundException("Нет пользователя с таким id!");
        }
        userRepo.deleteById(id);
        return id;
    }

}
