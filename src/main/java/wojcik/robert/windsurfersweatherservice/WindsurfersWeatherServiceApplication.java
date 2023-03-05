package wojcik.robert.windsurfersweatherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WindsurfersWeatherServiceApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(WindsurfersWeatherServiceApplication.class, args);
    }
}
