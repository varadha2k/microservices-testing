package com.varadha2k.weather;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.varadha2k.helper.FileLoader;

public class WeatherResponseTest {

    @Test
    public void shouldDeserializeJson() throws Exception {
        String jsonResponse = FileLoader.read("classpath:weatherApiResponse.json");
        WeatherResponse expectedResponse = new WeatherResponse("Very Cloudy");
        WeatherResponse parsedResponse = new ObjectMapper().readValue(jsonResponse, WeatherResponse.class);

        assertThat(parsedResponse, is(expectedResponse));
        assertNotNull(expectedResponse.getCurrently());
        assertNotNull(expectedResponse.hashCode());
        assertNotNull(expectedResponse.getCurrently().hashCode());
        assertNotNull(expectedResponse.getCurrently().toString());
        assertNotNull(expectedResponse.toString());
    }
}