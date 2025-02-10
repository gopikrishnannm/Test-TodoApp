package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="username")
	WebElement txtUsername;
	
	@FindBy(id="password")
	WebElement txtPassword;
	
	@FindBy(id="register-button")
	WebElement btnRegister;
	
	@FindBy(xpath = "//input[@id='login-button']")
	WebElement btnLogin;
	
	public void setUsername(String username){
		txtUsername.sendKeys(username);	
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
	public void clickRegister() {
		btnRegister.click();
	}
}