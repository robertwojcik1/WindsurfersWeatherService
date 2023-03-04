package wojcik.robert.windsurfersweatherservice.domain;

import lombok.*;

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
        return  "City Name: " + cityName + "\n" +
                "wind speed: " + windSpeed + "\n" +
                "temperature: " + temperature + "\n" +
                "date: " + date;
    }
}
