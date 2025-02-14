package com.weatherapp.weather_app.service;

import com.weatherapp.weather_app.exception.CityNotFoundException;
import com.weatherapp.weather_app.model.WeatherResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String API_KEY = "e5e2976d63454f55be7c53753aed0c06";
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";

    @Cacheable("weather")
    public WeatherResponse getWeatherByCity(String city) {
        String url = BASE_URL + city + "&appid=" + API_KEY ;//+ "&units=metric" ;  //for Celsius
        RestTemplate restTemplate = new RestTemplate();
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);

        return response;
    }
}
