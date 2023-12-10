package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.UserAuthDTO;
import com.example.finalfinalback3.DTO.UserRegisterDTO;
import com.example.finalfinalback3.Entity.UserEntity;
import com.example.finalfinalback3.Exceptions.PasswordsNotSameException;
import com.example.finalfinalback3.Exceptions.UserAlreadyExistsException;
import com.example.finalfinalback3.Repository.AuthRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    private final AuthRepository authRepo;
    private final ModelMapper modelMapper;
    public AuthService(AuthRepository authRepo, ModelMapper modelMapper) {
        this.authRepo = authRepo;
        this.modelMapper = modelMapper;
    }

    public Integer authUser(UserAuthDTO user) throws EntityNotFoundException,
            PasswordsNotSameException {
        UserEntity find_user = authRepo.findByLogin(user.login);
        if (find_user == null){
            throw new EntityNotFoundException("Такого пользователя не существует!");
        }
        if (!find_user.getPassword().equals(user.password)){
            throw new PasswordsNotSameException("Неверный пароль!");
        }
        return(find_user.getId());
    }

    public UserEntity registration(UserRegisterDTO user) throws UserAlreadyExistsException,
            PasswordsNotSameException {
        UserEntity find_user = authRepo.findByLogin(user.getLogin());
        if (find_user != null) {
            throw new UserAlreadyExistsException("Пользователь с таким логином уже существует!");
        }
        if (!user.getPassword().equals(user.getPassword_confirm())){
            throw new PasswordsNotSameException("Пароли не совпадают");
        }
        return authRepo.save(modelMapper.map(user, UserEntity.class));
    }

}
