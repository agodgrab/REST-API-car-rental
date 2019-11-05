package com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain.ForecastData;
import com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain.Location;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastDto {

    @JsonProperty("city")
    private Location location;

    @JsonProperty("list")
    private List<ForecastData> forecast;

    public WeatherForecastDto() {
    }

    public WeatherForecastDto(Location location, List<ForecastData> forecast) {
        this.location = location;
        this.forecast = forecast;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<ForecastData> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastData> forecast) {
        this.forecast = forecast;
    }
}
