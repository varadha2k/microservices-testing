package com.varadha2k;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.varadha2k.weather.WeatherClient;
import com.varadha2k.weather.WeatherResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ExampleController.class)
public class ExampleControllerAPITest {

	@Autowired
	private MockMvc mockMvc;


	@MockBean
	private WeatherClient weatherClient;

	@Test
	public void shouldReturnHelloWorld() throws Exception {
		mockMvc.perform(get("/hello")).andExpect(content().string("Hello World!"))
				.andExpect(status().is2xxSuccessful());
	}


	@Test
	public void shouldReturnCurrentWeather() throws Exception {
		WeatherResponse weatherResponse = new WeatherResponse("rain");
		given(weatherClient.fetchWeather()).willReturn(Optional.of(weatherResponse));

		mockMvc.perform(get("/weather")).andExpect(status().is2xxSuccessful()).andExpect(content().string("rain"));
	}

	@Test
	public void shouldReturnCurrentWeatherwithLatLong() throws Exception {
		WeatherResponse weatherResponse = new WeatherResponse("rain");
		given(weatherClient.fetchWeatherWithAPI("13.0827", "80.2707")).willReturn(Optional.of(weatherResponse));

		mockMvc.perform(get("/weatherwithloc/?latitude=13.0827&longitude=80.2707"))
				.andExpect(status().is2xxSuccessful()).andExpect(content().string("rain"));
	}

	@Test
	public void shouldReturnRecommendationFoRain() throws Exception {
		WeatherResponse weatherResponse = new WeatherResponse("rain");
		given(weatherClient.fetchWeatherWithAPI("13.0827", "80.2707")).willReturn(Optional.of(weatherResponse));

		mockMvc.perform(get("/weatherRecommendation/?latitude=13.0827&longitude=80.2707"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().string("{\"climate\":\"rain\",\"recommendation\":\"Use Umbrella\"}"));
	}

	@Test
	public void shouldReturnRecommendationFoClearDay() throws Exception {
		WeatherResponse weatherResponse = new WeatherResponse("clear-day");
		given(weatherClient.fetchWeatherWithAPI("13.0827", "80.2707")).willReturn(Optional.of(weatherResponse));

		mockMvc.perform(get("/weatherRecommendation/?latitude=13.0827&longitude=80.2707"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().string("{\"climate\":\"clear-day\",\"recommendation\":\"Umbrella not required\"}"));
	}

}
