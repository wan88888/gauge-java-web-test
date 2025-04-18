package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtil.class);
    private static final String SCREENSHOT_DIR = "screenshots";
    
    static {
        // 确保截图目录存在
        try {
            Path screenshotDir = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
                logger.info("Created screenshot directory: {}", screenshotDir);
            }
        } catch (IOException e) {
            logger.error("Failed to create screenshot directory", e);
        }
    }
    
    public static String takeScreenshot(String testName) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) {
            logger.warn("Cannot take screenshot - driver is null");
            return null;
        }
        
        if (!(driver instanceof TakesScreenshot)) {
            logger.warn("Driver does not support taking screenshots");
            return null;
        }
        
        try {
            // 生成带时间戳的文件名
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String fileName = String.format("%s/%s-%s.png", SCREENSHOT_DIR, testName, timestamp);
            
            // 截图
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshotFile.toPath(), Paths.get(fileName));
            
            logger.info("Screenshot saved to: {}", fileName);
            return fileName;
            
        } catch (Exception e) {
            logger.error("Failed to take screenshot", e);
            return null;
        }
    }
} 