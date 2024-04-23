package com.example.lab_3.toys;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.HashMap;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Doll.class, name = "doll"),
        @JsonSubTypes.Type(value = Ball.class, name = "ball"),
        @JsonSubTypes.Type(value = Car.class, name = "car"),
        @JsonSubTypes.Type(value = TalkingDoll.class, name = "talking_doll"),
})
public abstract class Toy implements Comparable<Toy> {
    @JsonProperty("price")
    private final double price;

    @JsonProperty("size")
    private final String size;

    @JsonProperty("age_group")
    private final String ageGroup;

    @JsonProperty("name")
    private final String name;

    public Toy(double price, String size, String ageGroup, String name) {
        this.price = price;
        this.size = size;
        this.ageGroup = ageGroup;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getName() {
        return name;
    }

    protected HashMap<String, String> getBaseProperties() {
        var args = new HashMap<String, String>();

        args.put("size", getSize());
        args.put("price", String.valueOf(getPrice()));
        args.put("name", getName());
        args.put("ageGroup", getAgeGroup());

        return args;
    }

    @Override
    public int compareTo(Toy other) {
        return Double.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        var properties = getToyProperties();

        var sb = new StringBuilder();

        sb.append("{\n\t");

        properties.forEach((key, value) -> sb.append(STR."\{key}: \{value},\n\t"));

        var last = sb.lastIndexOf("\n");

        sb.delete(last, sb.length());

        sb.append("\n}");

        return sb.toString();
    }

    // Template method
    public abstract HashMap<String, String> getToyProperties();

    // Prototype
    public abstract Toy clone();
}
