import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.log4testng.Logger;

public class TestAgent extends TestAgentRegression {

    public TestAgent(RemoteWebDriver remoteWebDriver) {
        super(remoteWebDriver);
    }

    public static void baseTest(String step, Runnable runnable) {
        runnable.run();
        Logger.getLogger(TestAgentRegression.class);

        System.out.println(java.util.logging.Logger.getAnonymousLogger().getLevel() + step);
    }
}
