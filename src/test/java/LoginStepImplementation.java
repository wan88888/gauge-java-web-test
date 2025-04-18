import com.thoughtworks.gauge.Step;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPage;
import utils.ScreenshotUtil;

public class LoginStepImplementation {
    
    private static final Logger logger = LoggerFactory.getLogger(LoginStepImplementation.class);
    private LoginPage loginPage;
    
    public LoginStepImplementation() {
        loginPage = new LoginPage();
    }
    
    @Step("Navigate to login page")
    public void navigateToLoginPage() {
        logger.info("Navigating to login page");
        loginPage.navigateTo();
    }
    
    @Step("Enter username <username>")
    public void enterUsername(String username) {
        logger.info("Entering username: {}", username);
        loginPage.enterUsername(username);
    }
    
    @Step("Enter password <password>")
    public void enterPassword(String password) {
        logger.info("Entering password: ********");
        loginPage.enterPassword(password);
    }
    
    @Step("Click login button")
    public void clickLoginButton() {
        logger.info("Clicking login button");
        loginPage.clickLoginButton();
    }
    
    @Step("Verify successful login message is displayed")
    public void verifySuccessfulLogin() {
        logger.info("Verifying successful login");
        
        // 截图记录成功登录状态
        ScreenshotUtil.takeScreenshot("successful-login");
        
        Assertions.assertThat(loginPage.isSuccessMessageDisplayed())
                .as("Success message should be displayed")
                .isTrue();
        
        String successMessage = loginPage.getSuccessMessage();
        logger.info("Success message received: {}", successMessage);
        
        Assertions.assertThat(successMessage)
                .as("Success message should contain 'You logged into a secure area'")
                .contains("You logged into a secure area");
        
        Assertions.assertThat(loginPage.isSecureAreaHeaderDisplayed())
                .as("Secure area header should be displayed")
                .isTrue();
        
        String headerText = loginPage.getSecureAreaHeaderText();
        logger.info("Secure area header: {}", headerText);
        
        Assertions.assertThat(headerText)
                .as("Page should display 'Secure Area' header")
                .contains("Secure Area");
        
        logger.info("Login verification completed successfully");
    }
} 