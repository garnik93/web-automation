import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LoggingHandler;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class TestAgentRegression extends Page {
    private final WebDriverWait wait;
    private final SessionId sessionId;
    private final String browserName;
    private final String browserVersion;
    private static final Logger logger = Logger.getLogger(RemoteWebDriver.class.getName());
    private final RemoteWebDriver driver;
    private final PageFactory pageFactory;

    public TestAgentRegression(RemoteWebDriver remoteWebDriver) {
        this.sessionId = remoteWebDriver.getSessionId();
        this.browserName = remoteWebDriver.getCapabilities().getBrowserName();
        this.browserVersion = remoteWebDriver.getCapabilities().getBrowserVersion();
        this.driver = remoteWebDriver;
        logger.addHandler(LoggingHandler.getInstance());
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.pageFactory = new PageFactory(this);
    }

    public SessionId getSessionId() {
        return sessionId;
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public PageFactory getPageFactory() {
        return pageFactory;
    }

    public TestAgentRegression openGoogle() {
        driver.get("https://www.google.com/");
        return this;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void checkVisibility() {
        Assert.isTrue(waitForElementVisibility("[name='p']").isDisplayed(),
                "google page should be visible");
    }

    public WebElement findElementByLocator(String locator) {
        return driver.findElement(By.cssSelector(locator));
    }

    public WebElement waitForElementVisibility(String locator) {
        return wait.pollingEvery(Duration.ofMillis(500))
                .withMessage("Element " + locator + " not find of 30 seconds")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
    }

    public WelcomeGooglePage onWelcomeGooglePage() {
        return this.pageFactory.getPage(WelcomeGooglePage.class);
    }

    public GoogleSearchPage onGoogleSearchPage() {
        return this.pageFactory.getPage(GoogleSearchPage.class);
    }
}
