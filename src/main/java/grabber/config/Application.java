package grabber.config;

import org.openqa.selenium.WebDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan(basePackages = {"grabber"})
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Драйвер нормально инициализируется только когда он тут, уточнить почему не работет
     * в произвольных классах.
     */
    @Bean
    public WebDriver webDriver() {
        DriverConfig config = new DriverConfig();
        return config.createDriver("chrome");
    }

}
