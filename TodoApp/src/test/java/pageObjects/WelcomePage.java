package pageObjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePage extends BasePage{

	public WelcomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//button[@class='todo-button']")
	WebElement btnTodo;
	
	@FindBy(xpath = "//button[@class='delete-button']")
	WebElement btnDeleteAcct;
	
	@FindBy(css = "div.welcome-title h1")
	WebElement welcomeTitle;
	
	@FindBy(className = "failure-message")
	WebElement failureMsg;
	
	
	public void clickTodoButton() {
		btnTodo.click();
	}
	
	public void clickDeleteButton() {
		btnDeleteAcct.click();
	}
	
	public boolean isWelcomePageTitleExists() {
		try {
			return welcomeTitle.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public String isErrorMessageDisplayed() {
		try {
			return failureMsg.getText();
		}
		catch(NoSuchElementException e) {
			return "";
		}
	}
	
}
