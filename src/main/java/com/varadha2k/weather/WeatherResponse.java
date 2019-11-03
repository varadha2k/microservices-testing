package com.varadha2k.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private Currently currently;

    public WeatherResponse() {}

    public WeatherResponse(String currenticon) {
        this.currently = new Currently(currenticon);
    }

    public String geticon() {
        return currently.geticon();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherResponse response = (WeatherResponse) o;

        return currently != null ? currently.equals(response.currently) : response.currently == null;
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Currently {
        private String icon;

        public Currently() {}

        public Currently(String icon) {
            this.icon = icon;
        }

        public String geticon() {
            return icon;
        }

    }
}
