package com.varadha2k.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private Currently currently;

    public WeatherResponse() {}

    public WeatherResponse(String currenticon) {
        this.currently = new Currently(currenticon);
    }

    public Currently getCurrently() {
        return currently;
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

    @Override
    public int hashCode() {
        return currently != null ? currently.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "currently=" + currently +
                '}';
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

        @Override
        public int hashCode() {
            return icon != null ? icon.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "Currently{" +
                    "icon='" + icon + '\'' +
                    '}';
        }
    }
}
