package com.weatherapp.weather_app.exception;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(String message){
        super(message);
    }
}
