package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePage{

	public Header(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath = "//button[text()='Logout']")
	WebElement btnLogout;
	
	public void clickLogoutButton() {
		btnLogout.click();
	}

}
