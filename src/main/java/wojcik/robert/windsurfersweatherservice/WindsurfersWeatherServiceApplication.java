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

    }
}
