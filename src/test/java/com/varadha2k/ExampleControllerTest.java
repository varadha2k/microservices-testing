package com.varadha2k;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.varadha2k.ExampleController.RecommendationResponse;
import com.varadha2k.weather.WeatherClient;
import com.varadha2k.weather.WeatherResponse;

public class ExampleControllerTest {

    private ExampleController subject;

    @Mock
    private WeatherClient weatherClient;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        subject = new ExampleController(weatherClient);
    }

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        assertThat(subject.hello(), is("Hello World!"));
    }



    @Test
    public void shouldReturnWeatherClientResult() throws Exception {
        WeatherResponse weatherResponse = new WeatherResponse("Hamburg, 8°C raining");
        given(weatherClient.fetchWeather()).willReturn(Optional.of(weatherResponse));

        String weather = subject.weather();

        assertThat(weather, is("Hamburg, 8°C raining"));
    }

    @Test
    public void shouldReturnWeatherWithLocation() throws Exception {
        WeatherResponse weatherResponse = new WeatherResponse("Hamburg, 8°C raining");
        given(weatherClient.fetchWeatherWithAPI("1","1")).willReturn(Optional.of(weatherResponse));

        String weather = subject.weatherwithloc("1","1");

        assertThat(weather, is("Hamburg, 8°C raining"));
    }


    @Test
    public void shouldReturnWeatherRecommendation() throws Exception {
    	
    	
        WeatherResponse weatherResponse = new WeatherResponse("rain");
        given(weatherClient.fetchWeatherWithAPI("1","1")).willReturn(Optional.of(weatherResponse));

        RecommendationResponse  weather = subject.weatherRecommendation("1","1");

        assertThat(weather.recommendation, is("Use Umbrella"));
    }

    @Test
    public void shouldReturnErrorMessageIfWeatherClientIsUnavailable() throws Exception {
        given(weatherClient.fetchWeather()).willReturn(Optional.empty());

        String weather = subject.weather();

        assertThat(weather, is("Sorry, I couldn't fetch the weather for you :("));
    }
}