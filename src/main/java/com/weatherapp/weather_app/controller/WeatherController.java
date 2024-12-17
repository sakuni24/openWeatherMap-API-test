package com.weatherapp.weather_app.controller;

import com.weatherapp.weather_app.dto.WeatherRequest;
import com.weatherapp.weather_app.exception.APIKeyException;
import com.weatherapp.weather_app.exception.CityNotFoundException;
import com.weatherapp.weather_app.model.WeatherResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.weatherapp.weather_app.service.WeatherService;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<?> getWeather(@Valid WeatherRequest request) {
        try {
            String query = (request.getCountry() != null && !request.getCountry().isEmpty())
                    ? request.getCity() + "," + request.getCountry()
                    : request.getCity();
            WeatherResponse weather = weatherService.getWeatherByCity(query);
            return ResponseEntity.ok(weather);

        } catch (CityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("City not found. Please check the City name");
        } catch (APIKeyException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid API key. Please update your API key.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred.");
        }
    }
}


