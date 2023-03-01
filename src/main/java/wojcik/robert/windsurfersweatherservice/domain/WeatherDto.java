package wojcik.robert.windsurfersweatherservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class WeatherDto {
    private float windSpeed;
    private float temperature;
    private String date;
}
