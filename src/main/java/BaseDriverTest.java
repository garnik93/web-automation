import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseDriverTest extends RemoteWD {

    ThreadJobSynchronization parallel = new ThreadJobSynchronization();
    public TestAgentRegression one;
    public TestAgentRegression two;
    public TestAgentRegression three;

    public final List<TestAgentRegression> agents = new ArrayList<>();

    public void initAgents(int count) {
        switch (count) {
            case 1 -> {
                one = getTestAgent();
                agents.add(one);
            }
            case 2 -> {
                parallel.addThreadJobRunnable(() -> one = getTestAgent(), "");
                parallel.addThreadJobRunnable(() -> two = getTestAgent(), "");
                parallel.runTasks();
//                one = getTestAgent();
//                two = getTestAgent();
                agents.addAll(Arrays.asList(one, two));
            }
            case 3 -> {
                one = getTestAgent();
                two = getTestAgent();
                three = getTestAgent();
                agents.addAll(Arrays.asList(one, two, three));

            }
            default -> throw new AssertionError("");
        }
    }

    private TestAgentRegression getTestAgent() {
        return new TestAgentRegression(remoteDriver(Browser.FIREFOX));
    }
}
