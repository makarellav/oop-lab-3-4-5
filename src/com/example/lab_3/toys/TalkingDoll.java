package com.example.lab_3.toys;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class TalkingDoll extends Doll {
    @JsonProperty("language")
    private String language;

    @JsonProperty("phrase")
    private String phrase;

    public TalkingDoll(
            @JsonProperty("price") double price,
            @JsonProperty("size") String size,
            @JsonProperty("name") String name,
            @JsonProperty("age_group") String ageGroup,
            @JsonProperty("hair_color") String hairColor,
            @JsonProperty("language") String language,
            @JsonProperty("phrase") String phrase) {
        super(price, size, name, ageGroup, hairColor);

        this.language = language;
        this.phrase = phrase;
    }

    public TalkingDoll(TalkingDoll talkingDoll) {
        super(talkingDoll.getPrice(), talkingDoll.getSize(), talkingDoll.getName(), talkingDoll.getAgeGroup(), talkingDoll.getHairColor());

        this.language = talkingDoll.language;
        this.phrase = talkingDoll.phrase;
    }

    public String getLanguage() {
        return language;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public HashMap<String, String> getToyProperties() {
        var properties = getBaseProperties();

        properties.put("hairColor", getHairColor());
        properties.put("language", getLanguage());
        properties.put("phrase", getPhrase());

        return properties;
    }
    
    @Override
    public TalkingDoll clone() {
        var talkingDoll = (TalkingDoll)super.clone();
        talkingDoll.language = getLanguage();
        talkingDoll.phrase = getPhrase();

        return new TalkingDoll(talkingDoll);
    }
}
