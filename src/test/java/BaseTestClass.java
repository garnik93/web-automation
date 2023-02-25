import org.testng.annotations.AfterClass;

import java.util.Arrays;

public class BaseTestClass extends BaseDriverTest {

    public void openGoogle(TestAgentRegression... agents) {
        Arrays.asList(agents).parallelStream().forEach(TestAgentRegression::openGoogle);
    }

    @AfterClass(alwaysRun = true)
    public void quite() {
        agents.parallelStream().forEach(agent -> agent.getDriver().quit());
    }
}
