import jdk.jfr.Name;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage extends Page {
    private final RemoteWebDriver remoteWebDriver;

    @Name("Input search fields")
    @FindBy(css = "[name='q']")
    public PageElements<GoogleSearchPage> inputFieldClass;

    @Name("Input search fields")
    @FindBy(css = "[name='q']")
    public WebElement inputField;

    public GoogleSearchPage (TestAgentRegression driver) {
        this.remoteWebDriver = driver.getDriver();
        PageFactory.initElements(remoteWebDriver, this);
    }

    public String getInputValue () {
        return inputField.getAttribute("value");
    }
}
