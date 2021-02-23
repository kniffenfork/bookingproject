package com.petprojects.bookingproject.exceptions;

import lombok.Data;

@Data
public class ExceptionHandler {
    private String message;

    public ExceptionHandler(String message) {
        this.message = message;
    }
}
