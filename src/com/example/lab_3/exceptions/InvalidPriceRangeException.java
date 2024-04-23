package com.example.lab_3.exceptions;

public class InvalidPriceRangeException extends IllegalArgumentException {
    public InvalidPriceRangeException(String errorMessage) {
        super(errorMessage);
    }
}
