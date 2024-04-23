package com.example.lab_3.toys;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class Ball extends Toy {
    @JsonProperty("color")
    private final String color;

    public Ball(
            @JsonProperty("price") double price,
            @JsonProperty("size") String size,
            @JsonProperty("name") String name,
            @JsonProperty("age_group") String ageGroup,
            @JsonProperty("color") String color) {
        super(price, size, ageGroup, name);
        this.color = color;
    }

    public Ball(Ball ball) {
        super(ball.getPrice(), ball.getSize(), ball.getAgeGroup(), ball.getName());

        this.color = ball.getColor();
    }

    public String getColor() {
        return color;
    }

    @Override
    public HashMap<String, String> getToyProperties() {
        var properties = getBaseProperties();

        properties.put("color", getColor());

        return properties;
    }

    @Override
    public Toy clone() {
        return new Ball(this);
    }
}
