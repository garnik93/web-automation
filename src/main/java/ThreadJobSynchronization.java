import dev.failsafe.internal.util.Assert;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadJobSynchronization {

    private final List<Thread> threadList = new ArrayList<>();

    public ThreadJobSynchronization addThreadJobRunnable(Runnable element, String name) {
        threadList.add(new Thread(() -> element.run(), name));
        return this;
    }

    public ThreadJobSynchronization addThreadJobWaitElement(TestAgentRegression agent, By element, String name) {

        Wait<WebDriver> wait = new WebDriverWait(agent.getDriver(), Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(5));

        threadList.add(new Thread(() -> {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
        }, name));
        return this;
    }

    public ThreadJobSynchronization addThreadJobAssert(boolean bool, boolean isTrue, String name) {
        String step = isTrue ? "disabled" : "enabled";
        threadList.add(new Thread(() -> {
            if (isTrue) {
                Assert.isTrue(bool, name + " should be " + step);
            } else {
                Assert.isTrue(!bool, name + " should be " + step);
            }
        }, name));
        return this;
    }

    public void runTasks() {
        List<Throwable> exceptions = Collections.synchronizedList(new ArrayList<>());
        Thread.UncaughtExceptionHandler exceptionHandler = (Thread th, Throwable ex) -> {
            synchronized (exceptions) {
                exceptions.add(new RuntimeException(th.getName() + " failed: " + ex.getMessage(), ex));
            }
        };

        threadList.parallelStream().forEach(job -> job.setUncaughtExceptionHandler(exceptionHandler));
        threadList.parallelStream().forEach(Thread::start);

        threadList.parallelStream().forEach(job -> {
            try {
                job.join();
            } catch (InterruptedException e) {
                throw new RuntimeException("Could not synchronize thread - " + e.getMessage(), e);
            }
            synchronized (exceptions) {
                if (!exceptions.isEmpty()) {
                    throw new RuntimeException(exceptions.stream().map(Throwable::getMessage).reduce("", (e1, e2) -> e1 + e2 + "\n"), exceptions.get(0));
                }
            }
        });

        threadList.clear();
    }
}