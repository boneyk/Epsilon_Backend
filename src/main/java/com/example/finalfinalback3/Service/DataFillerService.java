package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.UserRegisterDTO;
import com.example.finalfinalback3.Entity.UserEntity;
import com.example.finalfinalback3.Enum.PermissionEnum;
import com.example.finalfinalback3.Enum.RoleEnum;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class DataFillerService {

    private final AuthService authService;
    public DataFillerService(AuthService authService) {
        this.authService = authService;
    }

    @PostConstruct
    @Transactional
    @SneakyThrows
    public void fillData() {
        UserRegisterDTO user1 = new UserRegisterDTO("Dum@gmail.com", "Hr0mE", "smth", "smth");
        UserRegisterDTO user2 = new UserRegisterDTO("Numbers@gmail.com", "123", "123", "123");
        UserRegisterDTO user3 = new UserRegisterDTO("Not_admin@gmail.com", "Not_admin", "true", "true");
        UserRegisterDTO user4 = new UserRegisterDTO("Petrovich@gmail.com", "Petrovich", "123", "123");

        authService.registration(user1);
        authService.registration(user2);
        authService.registration(user3);
        authService.registration(user4);
    }
}
