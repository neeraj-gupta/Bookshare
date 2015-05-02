package RunConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration 
public class Cmpe275ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cmpe275ProjectApplication.class, args);
    }
}
