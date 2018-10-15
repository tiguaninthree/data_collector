package grabber.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;


public class DriverConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(DriverConfig.class);

    public WebDriver createDriver(String browser) {
        WebDriver driver = null;
        if (browser.contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
            DesiredCapabilities caps = DesiredCapabilities.chrome();
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.BROWSER, Level.ALL);
            caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
            driver = new ChromeDriver(caps);
            LOGGER.info("ChromeDriver instance successfully created.");
        } else {
            LOGGER.error("Driver not supported. Check conditions and settings!");
        }
        return driver;
    }
}
