package com.kodilla.kodilla.diplomaBackend.WeatherForecast.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastData {

    @JsonProperty("main")
    private WeatherMetrics weatherMetrics;

    @JsonProperty("weather")
    private List<WeatherDescription> weatherDescriptionList;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("wind")
    private Wind windSpeed;

    @JsonProperty("rain")
    private Rain rainLevel;

    @JsonProperty("snow")
    private Snow snow;

    @JsonProperty("dt_txt")
    private String dateTime;

    public ForecastData() {
    }

    public ForecastData(WeatherMetrics weatherMetrics, List<WeatherDescription> weatherDescriptionList, Clouds clouds, Wind windSpeed, Rain rainLevel, Snow snow, String dateTime) {
        this.weatherMetrics = weatherMetrics;
        this.weatherDescriptionList = weatherDescriptionList;
        this.clouds = clouds;
        this.windSpeed = windSpeed;
        this.rainLevel = rainLevel;
        this.snow = snow;
        this.dateTime = dateTime;
    }

    public WeatherMetrics getWeatherMetrics() {
        return weatherMetrics;
    }

    public void setWeatherMetrics(WeatherMetrics weatherMetrics) {
        this.weatherMetrics = weatherMetrics;
    }

    public List<WeatherDescription> getWeatherDescriptionList() {
        return weatherDescriptionList;
    }

    public void setWeatherDescriptionList(List<WeatherDescription> weatherDescriptionList) {
        this.weatherDescriptionList = weatherDescriptionList;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
