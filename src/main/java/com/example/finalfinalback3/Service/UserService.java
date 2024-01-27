package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.AccountInfoChangeDTO;
import com.example.finalfinalback3.Exceptions.AccessException;
import com.example.finalfinalback3.Model.AccountInfoMain;
import com.example.finalfinalback3.Entity.UserEntity;
import com.example.finalfinalback3.Exceptions.DataNotFoundException;
import com.example.finalfinalback3.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepo;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }


    //Спросить насчёт использования Optional
    public UserEntity getUserById(Integer id) throws DataNotFoundException {
        Optional<UserEntity> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new DataNotFoundException("Пользователя с таким id не существует!");
        }
        return user.get();
    }

    public UserEntity getUserByToken(String token) throws DataNotFoundException {
        Optional<UserEntity> user = Optional.ofNullable(userRepo.findByToken(token));
        if (user.isEmpty()) {
            throw new DataNotFoundException("Пользователь с таким токеном не авторизован!");
        }
        return user.get();
    }

    public List<UserEntity> showAll() throws DataNotFoundException{
        if (userRepo.findAll() == null){
            throw new DataNotFoundException("Нет пользователей в базе данных...");
        }
        return (List<UserEntity>) userRepo.findAll();
    }
    public List<UserEntity> getAllByRole(String role){
        return userRepo.findAllByRole(role);
    }

    public Integer deleteUser(Integer id) throws DataNotFoundException {
        if (userRepo.findById(id) == null) {
            throw new DataNotFoundException("Нет пользователя с таким id!");
        }
        userRepo.deleteById(id);
        return id;
    }
    public void saveUser(UserEntity user) {
        userRepo.save(user);
    }

    public AccountInfoMain showAccountInfo(String token) throws DataNotFoundException{
        UserEntity user = getUserByToken(token);
        return modelMapper.map(user, AccountInfoMain.class);
    }

    public UserEntity setUserRoleAdmin(Integer id) throws DataNotFoundException{
        UserEntity user = getUserById(id);
        user.setRole("ADMIN");
        return userRepo.save(user);
    }
    public UserEntity setUserRoleManager(String admin_token, String manager_token) throws DataNotFoundException, AccessException {
        if (!isAdmin(admin_token)){
            throw new AccessException("У вас недостаточно прав, чтобы назначать кого-то менеджером!");
        }
        UserEntity user = getUserByToken(manager_token);
        user.setRole("MANAGER");
        return userRepo.save(user);
    }
    public UserEntity setUserRoleUser(String admin_token, String victim_token) throws DataNotFoundException, AccessException {
        if (!isAdmin(admin_token)){
            throw new AccessException("У вас недостаточно прав, чтобы назначать кому-то другую роль!");
        }
        UserEntity user = getUserByToken(victim_token);
        user.setRole("USER");
        return userRepo.save(user);
    }

    public boolean isManager(String token){
        String role = getUserByToken(token).getRole();
        return (role.equals("MANAGER"));
    }
    public boolean isAdmin(String token){
        String role = getUserByToken(token).getRole();
        return (role.equals("ADMIN"));
    }

}

