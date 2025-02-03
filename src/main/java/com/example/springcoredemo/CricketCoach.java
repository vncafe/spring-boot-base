package com.example.springcoredemo;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

@Component
public class CricketCoach implements Coach {
    @JsonProperty("name")
    @NotNull(message = "getDailyWorkout() must not return null")
    String name;

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
