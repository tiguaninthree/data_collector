package grabber.config;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import grabber.service.ui.UiGrabWineStyleService;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class WebDriverInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverInitializer.class);

    @Value("${grabber.url.winestyle}")
    private String url;

    @Value("${grabber.browser.name}")
    private String browser;

    @Value("${grabber.timeout}")
    private String timeout;

    @Autowired
    WebDriver webDriver;

    @Autowired
    UiGrabWineStyleService uiGrabWineStyleService;

//    @Autowired
//    VivinoItemGrabberService vivinoItemGrabberService;

    @PostConstruct
    public void setUp() {
        LOGGER.info("WebDriver start up...");
        webDriver.manage().window().maximize();
        WebDriverRunner.setWebDriver(webDriver);
        Selenide.open(url);
        uiGrabWineStyleService.grabItems();
//        vivinoItemGrabberService.grabAllWineTypes();
    }

    @PreDestroy
    public void shutDown() {
        LOGGER.info("WebDriver shut down...");
        webDriver.quit();
    }
}
