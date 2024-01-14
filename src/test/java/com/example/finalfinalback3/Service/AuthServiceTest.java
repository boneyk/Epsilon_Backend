package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.UserRegisterDTO;
import com.example.finalfinalback3.Entity.UserEntity;
import com.example.finalfinalback3.Exceptions.DataAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.PasswordsNotSameException;
import com.example.finalfinalback3.Model.Token;
import com.example.finalfinalback3.Repository.UserRepository;
import com.example.finalfinalback3.Security.AuthRepository;
import com.example.finalfinalback3.Security.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;
    @Mock
    private AuthRepository authRepo;
    @Mock
    private UserRepository userRepo;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void registratrion_success() throws DataAlreadyExistsException, PasswordsNotSameException {
        UserRegisterDTO new_user = new UserRegisterDTO("Dum@gmail.com", "123", "123", "123");
        Mockito.when(authRepo.findByLogin("123"))
                .thenReturn(null);
        Mockito.when(passwordEncoder.encode("123"))
                .thenReturn("preparedString_password");
        Mockito.when(authRepo.save(Mockito.any()))
                .thenReturn(new UserEntity(1, "Dum@gmail.com", "123", "preparedString_password"));

        Token token = authService.registration(new_user);
        Assertions.assertEquals(token.getToken(), "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3");
    }
    @Test
    void registratrion_DataAlreadyExistsException(){
        UserEntity new_user1 = new UserEntity(1, "Dum@gmail.com", "123", "123");
        UserRegisterDTO new_user2 = new UserRegisterDTO("another@gmail.com", "123", "222", "222");
        Mockito.when(authRepo.findByLogin(Mockito.anyString()))
                .thenReturn(new_user1);
        Assertions.assertThrows(DataAlreadyExistsException.class, () -> authService.registration(new_user2));
    }

    @Test
    void registratrion_PasswordsNotSameException(){
        UserRegisterDTO new_user2 = new UserRegisterDTO("another@gmail.com", "123", "999", "222");
        Mockito.when(authRepo.findByLogin(Mockito.anyString()))
                .thenReturn(null);
        Assertions.assertThrows(PasswordsNotSameException.class, () -> authService.registration(new_user2));
    }

//    @Test
//    void changeAccountInfo_changePasswordAndEmail_success() throws DataAlreadyExistsException, PasswordsNotSameException {
//        Token token = registratrion_success();
//        AccountInfoChangeDTO info = new AccountInfoChangeDTO("new@mail.ru","another_login", "newPassword");
//        String test_token = authService.hashCode("another_login");
//        Mockito.when(userRepo.findByToken(Mockito.anyString()))
//                .thenReturn(new UserEntity(1, "Dum@gmail.com", "123", "preparedString_password", token.getToken()));
//        Mockito.when(passwordEncoder.encode("newPassword"))
//                .thenReturn("hashedPassword");
////        Mockito.when(authRepo.save(Mockito.any(UserEntity.class)))
////                .thenReturn(new UserEntity(1, "new@mail.ru","another_login", "newPassword", test_token));
//        Token new_token = authService.changeAccountInfo(info, token.getToken());
//        UserEntity user = authRepo.findByToken(new_token.getToken());
//        Assertions.assertThrows(UsernameNotFoundException.class, ()-> authService.loadUserByUsername("another_login"));
//        Assertions.assertEquals(user.getToken(), token);
//
//    }

}
