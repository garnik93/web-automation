import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChromeWelcomePageTest extends BaseTestClass {

    @BeforeMethod
    public void setUp() {
        initAgents(2);
        openGoogle(one, two);
    }

    @Test
    public void checkURL() {
        Assert.assertTrue(one.getCurrentUrl().contains("www.google.com"),
                "should be 'www.google.com'");
    }

    @Test
    public void sendSomeTextInput() {
        final String text = "Some Text";

        TestAgent.baseTest("Check text of input must show '" + text + "'", () -> {
            one.onGoogleSearchPage().inputField.sendKeys(text);
            two.onGoogleSearchPage().inputField.sendKeys(text);

            Assert.assertEquals(one.onGoogleSearchPage().getInputValue(), text, "should be '" + text + "'");
            Assert.assertEquals(two.onGoogleSearchPage().inputField.getAttribute("value"), text, "should be '" + text + "'");
        });
    }

}
