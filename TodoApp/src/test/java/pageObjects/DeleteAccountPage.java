package pageObjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteAccountPage extends BasePage{

	public DeleteAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name="username")
	WebElement txtUsername;
	
	@FindBy(xpath = "//button[text()='Delete']")
	WebElement btnDelete;
	
	public void setUsername(String username){
		txtUsername.sendKeys(username);	
	}
	
	public void clickDeleteButton() {
		btnDelete.click();
	}	
//	public boolean isDeleted(String url) {
//		
//		try {
//			waitForURLToBe(url, 10);
//			return driver.getCurrentUrl().equals(url);
//		}
//		catch(TimeoutException e) {
//			return false;
//		}
//
//	}

}
