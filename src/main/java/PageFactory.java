import java.util.ArrayList;
import java.util.List;

public class PageFactory {

    private List<Page> pages = new ArrayList<>();
    private TestAgentRegression agent;

    public PageFactory(TestAgentRegression agent) {
        this.agent = agent;
    }

    public  <T extends Page> T getPage(Class<T> pageClass) {
        for (Page page : pages) {
            if (page.getClass() == pageClass) {
                return (T) page;
            }
        }

        try {
            T newPage = pageClass.getConstructor(TestAgentRegression.class).newInstance(this.agent);

            pages.add(newPage);
            return newPage;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Unable to create page object of" + pageClass.getSimpleName(), e);
        }
    }

    public Page getPage() {
        return new Page(agent) {};
    }
}
