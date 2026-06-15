package com.edigest.my.first.project.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private Location location;
    private Current current;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {
        private String name;
        private String country;
    }

    @Getter
    @Setter
   // @JsonIgnoreProperties(ignoreUnknown = true)
    public class Current {

        @JsonProperty("temp_c")
        private double tempC;

        @JsonProperty("feelslike_c")
        private double feelsLikeC;

        private int humidity;

        @JsonProperty("wind_kph")
        private double windKph;

        private Condition condition;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Condition {
        private String text;
        private String icon;
    }
}