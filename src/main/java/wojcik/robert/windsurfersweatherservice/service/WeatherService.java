package wojcik.robert.windsurfersweatherservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private static final String URL = "https://api.weatherbit.io/v2.0/forecast/daily?city=Raleigh,NC&key=";
    private static final String KEY = "f5f652b875784a5a8f0cc0a7385d9af9";

    private RestTemplate restTemplate = new RestTemplate();
    public String getWeather() {
        return restTemplate.getForObject(URL + KEY, String.class);
    }
}
