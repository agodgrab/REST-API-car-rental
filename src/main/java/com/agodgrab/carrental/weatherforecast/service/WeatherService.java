package com.kodilla.kodilla.diplomaBackend.weatherforecast.service;

import com.kodilla.kodilla.diplomaBackend.weatherforecast.client.WeatherClient;
import com.kodilla.kodilla.diplomaBackend.weatherforecast.domain.CurrentWeatherDto;
import com.kodilla.kodilla.diplomaBackend.weatherforecast.domain.WeatherForecastDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private WeatherClient weatherClient;

    public CurrentWeatherDto getCurrentWeather(String cityName, String countryCode) {
        return weatherClient.currentWeather(cityName, countryCode);
    }

    public WeatherForecastDto getWeatherForecast(String cityName, String countryCode) {
        return weatherClient.weatherForecast(cityName, countryCode);
    }
}
