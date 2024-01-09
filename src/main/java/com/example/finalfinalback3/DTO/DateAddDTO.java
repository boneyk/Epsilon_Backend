package com.example.finalfinalback3.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DateAddDTO{
    private LocalDate dateStart;
    private LocalDate dateEnd;

    public DateAddDTO(LocalDate dateStart, LocalDate dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }
}
