package com.weatherapp.weather_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class WeatherRequest {

    @NotBlank(message = "City name cannot be empty.")
    private String city;

    private String country; // Optional field, no validation

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
