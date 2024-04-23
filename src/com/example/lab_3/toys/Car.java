package com.example.lab_3.toys;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

import static java.lang.StringTemplate.STR;

public class Car extends Toy {
    @JsonProperty("car_type")
    private final String carType;

    public Car(
            @JsonProperty("price") double price,
            @JsonProperty("size") String size,
            @JsonProperty("name") String name,
            @JsonProperty("age_group") String ageGroup,
            @JsonProperty("car_type") String carType) {
        super(price, size, ageGroup, name);
        this.carType = carType;
    }

    public Car(Car car) {
        super(car.getPrice(), car.getSize(), car.getAgeGroup(), car.getName());

        this.carType = car.carType;
    }

    public String getCarType() {
        return carType;
    }

    @Override
    public HashMap<String, String> getToyProperties() {
        var properties = getBaseProperties();

        properties.put("type", getCarType());

        return properties;
    }

    @Override
    public Toy clone() {
        return new Car(this);
    }
}
