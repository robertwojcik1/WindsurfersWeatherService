package wojcik.robert.windsurfersweatherservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import wojcik.robert.windsurfersweatherservice.service.WeatherService;

@RestController
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("weather/{date}")
    public String getWeather(@PathVariable String date) {
        return weatherService.getWeather(date);
    }
}
