package utils;

import com.thoughtworks.gauge.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HooksImplementation {
    
    private static final Logger logger = LoggerFactory.getLogger(HooksImplementation.class);
    
    @BeforeSuite
    public void setupSuite() {
        logger.info("Starting test suite execution");
        DriverFactory.initializeDriver();
    }
    
    @AfterSuite
    public void tearDownSuite() {
        logger.info("Completing test suite execution");
        DriverFactory.quitDriver();
    }
    
    @BeforeScenario
    public void setupScenario(ExecutionContext context) {
        String scenarioName = context.getCurrentScenario().getName();
        logger.info("Starting scenario: {}", scenarioName);
    }
    
    @AfterScenario
    public void tearDownScenario(ExecutionContext context) {
        String scenarioName = context.getCurrentScenario().getName();
        logger.info("Completed scenario: {}", scenarioName);
    }
    
    @AfterStep
    public void afterStep(ExecutionContext context) {
        // 无需每个步骤都截图
    }
    
    @AfterScenario(tags = "failed")
    public void afterFailedScenario(ExecutionContext context) {
        String scenarioName = context.getCurrentScenario().getName();
        logger.error("Scenario failed: {}", scenarioName);
        String screenshotPath = ScreenshotUtil.takeScreenshot("failed-" + scenarioName.replaceAll("\\s+", "-"));
        if (screenshotPath != null) {
            logger.info("Failure screenshot saved to: {}", screenshotPath);
        }
    }
} 