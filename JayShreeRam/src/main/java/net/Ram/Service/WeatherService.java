package net.Ram.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.Ram.Response.WeatherResponse;

@Service
public class WeatherService {
	public static final String apiKey ="7f98d067be81b99bb0169d3a37a40371";
	private static final String API = "http://api.openweathermap.org/data/2.5/weather?q=city&APPID=API_KEY";
	@Autowired
	private RestTemplate restTemplate ; 
	@Autowired 
	private RedisService redisService ; 
	public WeatherResponse getWeather(String city) {
		WeatherResponse weatherResponse = redisService.get("weather of " + city , WeatherResponse.class );
		if(weatherResponse!=null) {
			return weatherResponse ; 
		}
		else {
		String finalAPI = API.replace("city", city).replace("API_KEY", apiKey);
		ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET ,null , WeatherResponse.class );
		WeatherResponse body = response.getBody();
		if (body != null) {
            redisService.set("weather_of_" + city, body, 300l);
        }
		return body  ; 
	}
	}
	

}
