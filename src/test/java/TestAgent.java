import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Date;

public class TestAgent extends TestAgentRegression {

    public TestAgent(RemoteWebDriver remoteWebDriver) {
        super(remoteWebDriver);
    }

    public static void baseTest(String step, Runnable runnable) {
        runnable.run();
        System.out.println("[ Test agent ][ " + new Date().getDate() + " ] " + step);
    }
}
