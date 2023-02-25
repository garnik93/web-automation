import org.openqa.selenium.remote.RemoteWebDriver;

public class Page {

    protected TestAgentRegression agent;
    protected RemoteWebDriver driver;
    protected PageFactory pageFactory;

    public Page () {}

    public Page (TestAgentRegression agent) {
        this.agent = agent;
        this.driver = agent.getDriver();
        this.pageFactory = agent.getPageFactory();
    }

}
