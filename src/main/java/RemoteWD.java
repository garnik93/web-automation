import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.codeborne.selenide.Configuration.browserCapabilities;

public class RemoteWD {

    //public final RemoteWebDriver remoteDriver = remoteDriver();

    public RemoteWebDriver remoteDriver(Browser browser) {

        switch (browser) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();

                Configuration.remote = "http://localhost:4444/wd/hub";
                Configuration.browser = "chrome";
                Configuration.browserVersion = "110.0";
                Configuration.browserSize = "1920x1080";
                Configuration.remoteConnectionTimeout = 30;
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
                chromeOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
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
                browserCapabilities = chromeOptions;
                break;
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                Configuration.remote = "http://localhost:4444/wd/hub";
                Configuration.browser = "firefox";
                Configuration.browserVersion = "110.0";
                Configuration.browserSize = "1920x1080";
                Configuration.remoteConnectionTimeout = 30;
                firefoxOptions.addArguments("--start-maximized");
                firefoxOptions.addArguments("--disable-extensions");
                firefoxOptions.setCapability("excludeSwitches", List.of("disable-popup-blocking"));
                firefoxOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
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
                browserCapabilities = firefoxOptions;
                break;
            default:
                throw new RuntimeException("Browsers not find, please enter correctly browser name");
        }

        try {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), browserCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}

enum Browser {
    CHROME, FIREFOX
}
