package com.kodilla.kodilla.diplomaBackend.WeatherForecast.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeatherConfig {

    @Value("${openweathermap.api.endpoint.prod}")
    private String weatherAppEndpoint;

    @Value("${openweathermap.app.key}")
    private String weatherAppKey;

    public static final String CURR_ENDPOINT = "/data/2.5/weather";
    public static final String FORECAST_ENDPOINT = "/data/2.5/forecast";

    public String getWeatherAppEndpoint() {
        return weatherAppEndpoint;
    }

    public String getWeatherAppKey() {
        return weatherAppKey;
    }
}
