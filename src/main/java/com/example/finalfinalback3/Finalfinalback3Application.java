package com.example.finalfinalback3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//Замечания по проекту на будущее:
// 1) От пользователя приходит DTO и пользователю возвращается DTO
// 2) ModelMapper используется только на уровне контроллера. Он мапит дто на входе до сущности и спускает её на уровень сервиса
// 3) При наличии централизованной обработки ошибок никаких try-catch в контроллере! (ИСПРАВЛЕНО)
// 4) Чистить испорты и неиспользуемые функции
// 5)
@SpringBootApplication
@EnableTransactionManagement
public class Finalfinalback3Application {

    public static void main(String[] args) {
        SpringApplication.run(Finalfinalback3Application.class, args);
    }

}
