package wojcik.robert.windsurfersweatherservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wojcik.robert.windsurfersweatherservice.domain.Location;
import wojcik.robert.windsurfersweatherservice.httpclient.WeatherbitDataDto;
import wojcik.robert.windsurfersweatherservice.httpclient.WeatherbitDto;

import java.util.*;
import java.util.stream.Collectors;

//W zależności która lokacja oferuje lepsze warunki do windsurfingu dla tego dnia w 16 dniowym
// zakresie pogody.  Poza zwracaniem nazwy lokacji, odpowiedź powinna zawierać warunki pogodowe
// ( przynajmniej średnią temperaturę - Celsius, wind speed - m/s) dla lokacji w tym dniu.
//Jeśli prędkość wiatru nie jest w zakresie <5;18> m/s i temperatura nie jest w zakresie <5;35> C
// to lokacja nie jest odpowiednia do windsurfingu. Jednak, jeśli jest w tych zakresach,
// najlepsza lokacja jest determinowana najwyższą wartością skalkulowaną wg formuły: v * 3 + temp
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
        List<WeatherbitDataDto> filteredResponcesByDate = new ArrayList<>();
        for (Location location : locationList) {
            WeatherbitDto response = getWeatherForLocation(location.getLatitude(), location.getLongitude());
            responcesList.add(response);
        }

        filteredResponcesByDate = filterByDate(responcesList, date);

        Map<String, Float> bestWeathersLocation = getBestWeathersLocation(filteredResponcesByDate);


        return "";
    }

    private List<WeatherbitDataDto> filterByDate(List<WeatherbitDto> responcesList, String date) {
        List<WeatherbitDataDto> filteredResponces = new ArrayList<>();
        for (int i = 0; i < responcesList.size(); i++) {
            for (int j = 0; j < responcesList.get(i).getData().size(); j++) {
                if (responcesList.get(i).getData().get(j).getValid_date().equals(date)) {
                    filteredResponces.add(responcesList.get(i).getData().get(j));
                }
            }
        }
        return filteredResponces;
    }

    private Map<String, Float> getBestWeathersLocation(List<WeatherbitDataDto> filteredResponcesByDate) {
        Map<String, Float> filteredByConditionsMap = new TreeMap<>(Comparator.reverseOrder());
        List<WeatherbitDataDto> filteredByConditions = filteredResponcesByDate.stream()
                .filter(response -> response.getWind_spd() >= 5 && response.getWind_spd() <= 18)
                .filter(response -> response.getTemp() >= 5 && response.getTemp() <= 35)
                .toList();

        for (int i = 0; i < filteredByConditions.size(); i++) {
            filteredByConditionsMap.put(filteredByConditions.get(i).toString(),
                    (filteredByConditions.get(i).getWind_spd()) * 3
                            + filteredByConditions.get(0).getTemp());
        }
        return filteredByConditionsMap;
    }

    public WeatherbitDto getWeatherForLocation(String latitude, String longitude) {
        WeatherbitDto response = restTemplate.getForObject(URL + KEY, WeatherbitDto.class, latitude, longitude);
        return response;
    }
}
