package wojcik.robert.windsurfersweatherservice.httpclient;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class WeatherbitDataDto {
    private float wind_spd;
    private float temp;
}
