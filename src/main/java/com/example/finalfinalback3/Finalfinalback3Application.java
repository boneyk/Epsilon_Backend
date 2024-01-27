package com.example.finalfinalback3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//Замечания по проекту для будущего развития:
// 1) От пользователя приходит DTO и пользователю возвращается DTO
// 2) ModelMapper используется только на уровне контроллера. Он мапит дто на входе до сущности и спускает её на уровень сервиса
// 3) При наличии централизованной обработки ошибок никаких try-catch в контроллере! (ИСПРАВЛЕНО)
// 4) Чистить испорты и неиспользуемые функции
// 5) Гетеры и сеттеры не возвращают ошибок, проверка должна происходить в самих функциях, используюзих геттеры и сеттеры
// 6) Стараться не лепить 20 методов в 1 классе. Разбивать по 5-7 методов.

@SpringBootApplication
@EnableTransactionManagement
public class Finalfinalback3Application {

    public static void main(String[] args) {
        SpringApplication.run(Finalfinalback3Application.class, args);
    }

}
