import jdk.jfr.Name;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomeGooglePage extends Page {
    private final RemoteWebDriver remoteWebDriver;

    @Name("Input search fields")
    @FindBy(css = "[name='q']")
    public PageElements<WelcomeGooglePage> inputField;

    public WelcomeGooglePage (TestAgentRegression driver) {
        this.remoteWebDriver = driver.getDriver();
        PageFactory.initElements(remoteWebDriver, this);
    }

    public String getInputValue () {
        return inputField.getText();
    }
}
