package pageObjects;

import java.time.Duration;
import java.util.NoSuchElementException;

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
	
	@FindBy(className = "success-message")
	WebElement successMsg;
	
	@FindBy(className = "failure-message")
	WebElement failureMsg;
	
	public void setUsername(String username){
		txtUsername.sendKeys(username);	
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickRegister() {
		btnRegister.click();
	}
	
//	public boolean isMessageDisplayed() {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		boolean isSuccessMessagePresent = wait.until(ExpectedConditions.or(
//	            ExpectedConditions.visibilityOf(successMsg),
//	            ExpectedConditions.visibilityOf(failureMsg)
//	        ));
//		return isSuccessMessagePresent;
//	}

	
//	public boolean isSuccessMessageDisplayed() {
//		try {
//			waitForElementFluent(successMsg, 3, 300);
//			return successMsg.isDisplayed();
//		}
//		catch(TimeoutException | NoSuchElementException e ) {
//			return false;
//		}
//	}
//	
	public boolean isFailureMessageDisplayed() {
		
		try {
			waitForElement(failureMsg, 5);
			return true;
		}
		catch(TimeoutException | NoSuchElementException e) {
			return false;
		}
	}




	

}
