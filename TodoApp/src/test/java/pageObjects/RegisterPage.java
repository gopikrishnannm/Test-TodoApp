package pageObjects;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage{

	public RegisterPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="username")
	WebElement txtUsername;
	
	@FindBy(id="password")
	WebElement txtPassword;

	@FindBy(xpath = "//button[text()='Register']")
	WebElement btnRegister;
	
	@FindBy(xpath = "//div[@class='success-message' or @class='failure-message']")
	WebElement msg;
	
	@FindBy(xpath = "//div[@class='error-message']")
	WebElement errorMsg;
	
	public void setUsername(String username) {
	    txtUsername.sendKeys(Keys.CONTROL + "a", Keys.DELETE); // Clears input field properly
	    txtUsername.sendKeys(username);
	}

	public void setPassword(String password) {
	    txtPassword.sendKeys(Keys.CONTROL + "a", Keys.DELETE); // Clears input field properly
	    txtPassword.sendKeys(password);
	}

	public void clickRegister() {
		btnRegister.click();
	}	
    public String getMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOf(msg));
            return msg.getText();
        } catch (TimeoutException e) {
            return "";  // If no message appears, return empty string
        }
    }

    public void waitForMessageToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(msg));
    }
	

}
