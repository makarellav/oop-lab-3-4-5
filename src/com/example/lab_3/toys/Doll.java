package com.example.lab_3.toys;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class Doll extends Toy {
    @JsonProperty("hair_color")
    private final String hairColor;

    public Doll(
            @JsonProperty("price") double price,
            @JsonProperty("size") String size,
            @JsonProperty("name") String name,
            @JsonProperty("age_group") String ageGroup,
            @JsonProperty("hair_color") String hairColor) {
        super(price, size, ageGroup, name);
        this.hairColor = hairColor;
    }

    public Doll(Doll doll) {
        super(doll.getPrice(), doll.getSize(), doll.getAgeGroup(), doll.getName());

        this.hairColor = doll.getHairColor();
    }

    public String getHairColor() {
        return hairColor;
    }

    @Override
    public HashMap<String, String> getToyProperties() {
        var properties = getBaseProperties();

        properties.put("hairColor", getHairColor());

        return properties;
    }

    @Override
    public Toy clone() {
        return new Doll(this);
    }
}
