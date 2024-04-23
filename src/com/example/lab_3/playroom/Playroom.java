package com.example.lab_3.playroom;

import com.example.lab_3.exceptions.InvalidPriceRangeException;
import com.example.lab_3.exceptions.NotEnoughBudgetException;
import com.example.lab_3.toys.Toy;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;
import java.util.stream.Collectors;

public class Playroom {
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private Deque<Toy> toys = new ArrayDeque<>();
    private double budget;

    public Playroom(double budget) {
        this.budget = budget;
    }

    public void addToy(Toy toy) {
        if (budget - toy.getPrice() >= 0) {
            budget -= toy.getPrice();
            toys.offerLast(toy);
        } else {
            throw new NotEnoughBudgetException(STR."Не вистачило коштів, щоб додати іграшку: \{toy.getName()}");
        }
    }

    public double getBudget() {
        return budget;
    }

    public Deque<Toy> getToys() {
        return toys;
    }
    public void setToys(List<Toy> toys) {
        this.toys.clear();
        this.toys.addAll(toys);
    }

    public void sortToysByPrice() {
        List<Toy> sortedToys = new ArrayList<>(toys);
        Collections.sort(sortedToys);

        toys = new ArrayDeque<>(sortedToys);
    }

    public Deque<Toy> findToysInPriceRange(double minPrice, double maxPrice) {
        if (minPrice > maxPrice) {
            throw new InvalidPriceRangeException("Мінімальна ціна має бути менше або дорівнювати максимальній");
        }

        return toys.stream()
                .filter(toy -> toy.getPrice() >= minPrice && toy.getPrice() <= maxPrice)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    public void displayToys() {
        for (var toy : toys) {
            System.out.println(toy);
        }
    }
}
