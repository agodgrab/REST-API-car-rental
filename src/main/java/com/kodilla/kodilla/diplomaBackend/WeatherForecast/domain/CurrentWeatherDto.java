package com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherDto {

    @JsonProperty("name")
    private String location;

    @JsonProperty("main")
    private WeatherMetrics metrics;

    @JsonProperty("weather")
    private List<WeatherDescription> description;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("wind")
    private Wind windSpeed;

    @JsonProperty("rain")
    private Rain rainLevel;

    @JsonProperty("snow")
    private Snow snow;

    public CurrentWeatherDto(WeatherMetrics metrics, List<WeatherDescription> description, Clouds clouds, Wind windSpeed, Rain rainLevel, Snow snow) {
        this.metrics = metrics;
        this.description = description;
        this.clouds = clouds;
        this.windSpeed = windSpeed;
        this.rainLevel = rainLevel;
        this.snow = snow;
    }

    public CurrentWeatherDto() {
    }

    public WeatherMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(WeatherMetrics metrics) {
        this.metrics = metrics;
    }


    public List<WeatherDescription> getDescription() {
        return description;
    }

    public void setDescription(List<WeatherDescription> description) {
        this.description = description;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Wind windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Rain getRainLevel() {
        return rainLevel;
    }

    public void setRainLevel(Rain rainLevel) {
        this.rainLevel = rainLevel;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }
}
