package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private static final String URL = "http://the-internet.herokuapp.com/login";
    
    // Page elements
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By successMessage = By.cssSelector("div.flash.success");
    private final By errorMessage = By.cssSelector("div.flash.error");
    private final By secureAreaHeader = By.tagName("h2");
    
    public void navigateTo() {
        driver.get(URL);
    }
    
    public void enterUsername(String username) {
        type(usernameField, username);
    }
    
    public void enterPassword(String password) {
        type(passwordField, password);
    }
    
    public void clickLoginButton() {
        click(loginButton);
    }
    
    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }
    
    public String getSuccessMessage() {
        return getText(successMessage);
    }
    
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }
    
    public String getErrorMessage() {
        return getText(errorMessage);
    }
    
    public boolean isSecureAreaHeaderDisplayed() {
        return isElementDisplayed(secureAreaHeader);
    }
    
    public String getSecureAreaHeaderText() {
        return getText(secureAreaHeader);
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
} 