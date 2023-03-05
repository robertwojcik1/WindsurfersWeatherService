package wojcik.robert.windsurfersweatherservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wojcik.robert.windsurfersweatherservice.domain.Location;
import wojcik.robert.windsurfersweatherservice.domain.Weather;
import wojcik.robert.windsurfersweatherservice.httpclient.WeatherbitDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    private static final String URL = "https://api.weatherbit.io/v2.0/forecast/daily?lat={latitude}&lon={longitude}&days=16&key=";
    private static final String KEY = "f5f652b875784a5a8f0cc0a7385d9af9";
    private RestTemplate restTemplate = new RestTemplate();
    List<Location> locationList = new ArrayList<>();

    public WeatherService() {
        locationList.add(new Location("Jastarnia", "54.702839", "18.670719"));
        locationList.add(new Location("Barbados", "13.096851", "-59.614483"));
        locationList.add(new Location("Fortaleza", "-3.732714", "-38.526997"));
        locationList.add(new Location("Pissouri", "34.670231", "32.701118"));
        locationList.add(new Location("LeMorne", "-20.469570", "57.344238"));
    }

    public String getWeather(String date) {
        List<WeatherbitDto> responcesList = new ArrayList<>();
        List<Weather> filteredWeatherByDate = new ArrayList<>();
        for (Location location : locationList) {
            WeatherbitDto response = getWeatherForLocation(location.getLatitude(), location.getLongitude());
            responcesList.add(response);
        }

        filteredWeatherByDate = filterByDateAndMapToObject(responcesList, date);

        Optional<Weather> bestWeathersLocation = getBestWeathersLocation(filteredWeatherByDate);

        return bestWeathersLocation.toString();
    }

    private List<Weather> filterByDateAndMapToObject(List<WeatherbitDto> responcesList, String date) {
        List<Weather> filteredObjects = new ArrayList<>();

        for (int i = 0; i < responcesList.size(); i++) {
            for (int j = 0; j < responcesList.get(i).getData().size(); j++) {

                if (responcesList.get(i).getData().get(j).getValid_date().equals(date)) {
                    Weather weather = Weather.builder()
                            .windSpeed(responcesList.get(i).getData().get(j).getWind_spd())
                            .temperature(responcesList.get(i).getData().get(j).getTemp())
                            .date(responcesList.get(i).getData().get(j).getValid_date())
                            .cityName(responcesList.get(i).getCity_name())
                            .build();
                    filteredObjects.add(weather);
                }
            }
        }
        return filteredObjects;
    }

    private Optional<Weather> getBestWeathersLocation(List<Weather> filteredWeatherByDate) {

        List<Weather> filteredByConditions = filteredWeatherByDate.stream()
                .filter(weather -> weather.getWindSpeed() >= 5 && weather.getWindSpeed() <= 18)
                .filter(weather -> weather.getTemperature() >= 5 && weather.getTemperature() <= 35)
                .toList();

        filteredByConditions.stream()
                .forEach(weather -> {
                    weather.setCalculatedValue(((weather.getWindSpeed() * 3)) + weather.getTemperature());
                });

        List<Weather> bestLocation = filteredByConditions.stream()
                .sorted((w1, w2) -> (int) (w2.getCalculatedValue() - w1.getCalculatedValue()))
                .limit(1L)
                .toList();

        return Optional.ofNullable(bestLocation.get(0));
    }

    public WeatherbitDto getWeatherForLocation(String latitude, String longitude) {
        WeatherbitDto response = restTemplate.getForObject(URL + KEY, WeatherbitDto.class, latitude, longitude);
        return response;
    }
}
