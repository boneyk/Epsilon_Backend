package com.example.finalfinalback3.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DateWindow {
    private Integer id;
    private LocalDate dateStart;
    private LocalDate dateEnd;

    public DateWindow(Integer id, LocalDate dateStart, LocalDate dateEnd) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }
}
