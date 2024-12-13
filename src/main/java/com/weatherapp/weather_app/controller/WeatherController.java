package com.weatherapp.weather_app.controller;

import com.weatherapp.weather_app.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.weatherapp.weather_app.service.WeatherService;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<?> getWeather(@RequestParam String city, @RequestParam(required = false) String country) {
        try {
            String query = (country!=null && !country.isEmpty()) ? city + "," + country : city;
            WeatherResponse weather = weatherService.getWeatherByCity(query);
            return ResponseEntity.ok(weather);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("City not found or API error.");
        }
    }
}


