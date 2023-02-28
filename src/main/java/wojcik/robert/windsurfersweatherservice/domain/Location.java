package wojcik.robert.windsurfersweatherservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
public class Location {
    private final String cityName;
    private final String latitude;
    private final String longitude;

}
