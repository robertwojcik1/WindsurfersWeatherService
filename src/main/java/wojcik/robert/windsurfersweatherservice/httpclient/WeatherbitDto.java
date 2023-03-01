package wojcik.robert.windsurfersweatherservice.httpclient;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class WeatherbitDto {
    private List<WeatherbitDataDto> data;
}
