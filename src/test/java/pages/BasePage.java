package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DriverFactory;
import utils.ScreenshotUtil;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);

    public BasePage() {
        driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        logger.debug("Initialized {} page object", getClass().getSimpleName());
    }

    protected WebElement waitForVisibility(By locator) {
        logger.debug("Waiting for element visibility: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickability(By locator) {
        logger.debug("Waiting for element to be clickable: {}", locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        logger.debug("Clicking element: {}", locator);
        try {
            waitForClickability(locator).click();
        } catch (Exception e) {
            logger.error("Failed to click element: {}", locator, e);
            ScreenshotUtil.takeScreenshot("click-error");
            throw e;
        }
    }

    protected void type(By locator, String text) {
        logger.debug("Typing text into element: {}", locator);
        try {
            WebElement element = waitForVisibility(locator);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            logger.error("Failed to type text into element: {}", locator, e);
            ScreenshotUtil.takeScreenshot("type-error");
            throw e;
        }
    }

    protected String getText(By locator) {
        logger.debug("Getting text from element: {}", locator);
        try {
            return waitForVisibility(locator).getText();
        } catch (Exception e) {
            logger.error("Failed to get text from element: {}", locator, e);
            ScreenshotUtil.takeScreenshot("getText-error");
            throw e;
        }
    }

    protected boolean isElementDisplayed(By locator) {
        logger.debug("Checking if element is displayed: {}", locator);
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            logger.debug("Element is not displayed: {}", locator);
            return false;
        }
    }
    
    protected boolean isElementPresent(By locator) {
        logger.debug("Checking if element is present: {}", locator);
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            logger.debug("Element is not present: {}", locator);
            return false;
        }
    }
    
    protected void selectByVisibleText(By locator, String text) {
        logger.debug("Selecting option by visible text: {} in element: {}", text, locator);
        try {
            Select select = new Select(waitForVisibility(locator));
            select.selectByVisibleText(text);
        } catch (Exception e) {
            logger.error("Failed to select option by visible text: {}", text, e);
            ScreenshotUtil.takeScreenshot("select-error");
            throw e;
        }
    }
    
    protected List<WebElement> findElements(By locator) {
        logger.debug("Finding elements: {}", locator);
        try {
            return driver.findElements(locator);
        } catch (Exception e) {
            logger.error("Failed to find elements: {}", locator, e);
            return List.of();
        }
    }
    
    protected void scrollToElement(By locator) {
        logger.debug("Scrolling to element: {}", locator);
        try {
            WebElement element = waitForVisibility(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            logger.error("Failed to scroll to element: {}", locator, e);
            ScreenshotUtil.takeScreenshot("scroll-error");
            throw e;
        }
    }
} 