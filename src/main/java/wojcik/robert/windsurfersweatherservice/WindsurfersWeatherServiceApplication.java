package wojcik.robert.windsurfersweatherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import wojcik.robert.windsurfersweatherservice.domain.Location;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WindsurfersWeatherServiceApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(WindsurfersWeatherServiceApplication.class, args);

        List<Location> locationList = new ArrayList<>();
        locationList.add(new Location("Jastarnia", "54.702839", "18.670719"));
        locationList.add(new Location("Barbados", "13.096851", "-59.614483"));
        locationList.add(new Location("Fortaleza", "-3.732714", "-38.526997"));
        locationList.add(new Location("Pissouri", "34.670231", "32.701118"));
        locationList.add(new Location("LeMorne", "-20.469570", "57.344238"));

    }
}
