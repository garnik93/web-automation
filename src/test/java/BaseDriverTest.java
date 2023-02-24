import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseDriverTest extends RemoteWD {

    public TestAgentRegression one;
    public TestAgentRegression two;
    public TestAgentRegression three;

    private final List<TestAgentRegression> agents = new ArrayList<>();
//    private final TestAgentRegression agentRegression = new TestAgentRegression(remoteDriver());

    public void initAgents(int count) {
        agents.addAll(Arrays.asList(one, two, three));

        switch (count) {
            case 1:
                one = getNewTestAgent();
                break;
            case 2: {
                one = getNewTestAgent();
                two = getNewTestAgent();
                break;
            }
            case 3: {
                one = getNewTestAgent();
                two = getNewTestAgent();
                three = getNewTestAgent();
            }
            default:
                throw new AssertionError("");
        }
    }

    private TestAgentRegression getNewTestAgent() {
        return new TestAgentRegression(remoteDriver());
    }

//    @BeforeClass
//    public void setUp() {
//        remoteDriver.get("https://www.google.com/");
//    }

    @AfterClass
    public void quite() {
        agents.forEach(agent -> {
            agent.getDriver().close();
            agent.getDriver().quit();
        });
    }
}
