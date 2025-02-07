package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CreateUpdateTodoPage extends BasePage{

	public CreateUpdateTodoPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name="description")
	WebElement txtDescription;
	
	@FindBy(name="targetdate")
	WebElement txtTargetDate;
	
	@FindBy(className = "input-field")
	WebElement drpdwnIsDone;
	
	@FindBy(xpath = "//button[text()='Save']")
	WebElement btnSave;
	
	@FindBy(className = "success-message")
	WebElement successMsg;
	

	
	public void setDesctiption(String description) {
		txtDescription.sendKeys(description);
	}
	
	public void setTargetDate(String targetDate) {
		txtTargetDate.sendKeys(targetDate);
	}
	
	public void setIsDone(String isDone) {
		Select select = new Select(drpdwnIsDone);
		select.selectByVisibleText(isDone);
	}
	
	public void clickSaveButton() {
		btnSave.click();
	}
	
	public String isSuccessMessageDisplayed() {
		try {
			return successMsg.getText();
		} catch (Exception e) {
			return "";
		}
	}

}
