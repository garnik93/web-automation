import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseTest extends BaseDriverTest {

    @BeforeMethod
    public void setUp() {
        initAgents(2);
    }

    @Test
    public void checkURL() {
        one.openGoogle();

        Assert.assertTrue(one.getUrl().contains("www.google.com"),
                "should be 'www.google.com'");
    }

    @Test
    public void sendSomeTextInput() {
        final String text = "Some Text";

        TestAgent.baseTest("Open google page", () -> {
            one.openGoogle();
            two.openGoogle();
        });

        TestAgent.baseTest("Check text of input must show '" + text + "'", () -> {
            WebElement input = one.findElementByLocator("[name='q']");
            input.sendKeys(text);

            Assert.assertEquals(input.getAttribute("value"), text, "should be '" + text + "'");

            WebElement inputTwo = two.findElementByLocator("[name='q']");
            inputTwo.sendKeys(text);

            Assert.assertEquals(inputTwo.getAttribute("value"), text, "should be '" + text + "'");
        });

        int i = 0;
    }
}
