package wojcik.robert.windsurfersweatherservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wojcik.robert.windsurfersweatherservice.httpclient.WeatherbitDataDto;
import wojcik.robert.windsurfersweatherservice.httpclient.WeatherbitDto;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private static final String URL = "https://api.weatherbit.io/v2.0/forecast/daily?lat=54.702839&lon=18.670719&days=16&key=";
    private static final String KEY = "f5f652b875784a5a8f0cc0a7385d9af9";

    private RestTemplate restTemplate = new RestTemplate();
    public String getWeather(String date) {
        WeatherbitDto response = restTemplate.getForObject(URL + KEY, WeatherbitDto.class);

        for (WeatherbitDataDto data : response.getData()) {
            if (data.getValid_date().equals(date)) {
                return data.toString();
            }
        }
        return null;
    }
}
