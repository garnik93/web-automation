import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.codeborne.selenide.Configuration.browserCapabilities;

public class RemoteWD {

    public final RemoteWebDriver remoteDriver = remoteDriver();

    public RemoteWebDriver remoteDriver() {
        ChromeOptions options = new ChromeOptions();

        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "110.0";
        Configuration.browserSize = "1920x1080";
        Configuration.remoteConnectionTimeout = 30;
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("name", "garnik");
            put("sessionTimeout", "15m");
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});
            put("labels", new HashMap<String, Object>() {{
                put("automated", "true");
            }});
            put("enableVideo", false);
            put("enableVNC", true);
        }});
        browserCapabilities = options;

        try {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), browserCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
