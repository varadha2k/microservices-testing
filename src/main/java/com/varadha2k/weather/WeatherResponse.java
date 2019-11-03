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
    public int hashCode() {
        return currently != null ? currently.hashCode() : 0;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Currently currently = (Currently) o;

            return icon != null ? icon.equals(currently.icon) : currently.icon == null;
        }

    }
}
