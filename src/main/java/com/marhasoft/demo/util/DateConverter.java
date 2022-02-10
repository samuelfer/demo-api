package com.marhasoft.demo.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateConverter {

    public LocalDate converterStringEmLocalDate(String data) {
        if (data == null) {
            return null;
        }
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
