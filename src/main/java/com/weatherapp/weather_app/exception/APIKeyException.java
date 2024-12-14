package com.weatherapp.weather_app.exception;

public class APIKeyException extends RuntimeException{
    public APIKeyException(String message){
        super(message);
    }
}
