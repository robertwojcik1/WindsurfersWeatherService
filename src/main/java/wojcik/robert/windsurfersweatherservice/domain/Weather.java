package wojcik.robert.windsurfersweatherservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Weather {
    private float windSpeed;
    private float temperature;
    private String date;
    private String cityName;
    private float calculatedValue;

    @Override
    public String toString() {
        return "City Name: " + cityName + ", " +
                "wind speed: " + windSpeed + ", " +
                "temperature: " + temperature + ", " +
                "date: " + date;
    }
}
