package com.varadha2k;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.varadha2k.weather.WeatherClient;
import com.varadha2k.weather.WeatherResponse;

@RestController
public class ExampleController {

	private final WeatherClient weatherClient;

	static Map<String, String> map = new HashMap<String, String>();

	static {
		map.put("clear-day", "Umbrella not required");
		map.put("clear-night", "Umbrella not required");
		map.put("rain", "Use Umbrella");
		map.put("snow", "Use Jackets");
		map.put("sleet", "Use Umbrella and Jackets");
		map.put("wind", "Use Jackets");
		map.put("fog", "Use Jackets");
		map.put("cloudy", "Use Jackets");
		map.put("partly-cloudy-day", "Use Umbrella");
		map.put("partly-cloudy-night", "Use Umbrella");
		map.put("hail", "Stay at home");
		map.put("thunderstorm", "Stay at home");
		map.put("tornado", "Stay at home");

	}

	@Autowired
	public ExampleController(final WeatherClient weatherClient) {
		this.weatherClient = weatherClient;
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}

	@GetMapping("/weather")
	public String weather() {
		return weatherClient.fetchWeather().map(WeatherResponse::geticon)
				.orElse("Sorry, I couldn't fetch the weather for you :(");
	}

	@GetMapping("/weatherwithloc")
	public String weatherwithloc(@RequestParam String latitude, @RequestParam String longitude) {
		return weatherClient.fetchWeatherWithAPI(latitude, longitude).map(WeatherResponse::geticon)
				.orElse("Sorry, I couldn't fetch the weather for you :(");
	}

	@GetMapping("/weatherRecommendation")
	public RecommendationResponse weatherRecommendation(@RequestParam String latitude, @RequestParam String longitude) {

		String climate = weatherClient.fetchWeatherWithAPI(latitude, longitude).map(WeatherResponse::geticon)
				.orElse("Sorry, I couldn't fetch the weather for you :(");

		RecommendationResponse resp = new RecommendationResponse();

		resp.setClimate(climate);

		String recommendation = "";

		recommendation = ExampleController.map.get(climate);

		if (null == recommendation)
			recommendation = "Sorry, I couldn't fetch the weather for you :((";

		resp.setRecommendation(recommendation);

		return resp;
	}

	public class RecommendationResponse {

		String climate;
		String recommendation;

		public String getClimate() {
			return climate;
		}

		public void setClimate(String climate) {
			this.climate = climate;
		}

		public String getRecommendation() {
			return recommendation;
		}

		public void setRecommendation(String recommendation) {
			this.recommendation = recommendation;
		}

	}

}
