package com.example.lab_3.exceptions;

public class NotEnoughBudgetException extends IllegalArgumentException {
    public NotEnoughBudgetException(String errorMessage) {
        super(errorMessage);
    }
}
