package wojcik.robert.windsurfersweatherservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter@ToString
public class Location {
    private final String cityName;
    private final String latitude;
    private final String longitude;

}
