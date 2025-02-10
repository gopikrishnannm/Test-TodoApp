package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateUpdateTodoPage extends BasePage{

	public CreateUpdateTodoPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@name='description']")
	WebElement txtDescription;
	
	@FindBy(name="targetdate")
	WebElement txtTargetDate;
	
	@FindBy(className = "input-field")
	WebElement drpdwnIsDone;
	
	@FindBy(xpath = "//button[text()='Save']")
	WebElement btnSave;
	
	@FindBy(className = "success-message")
	WebElement successMsg;
	

	
	public void setDescription(String description) {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", txtDescription);
//		txtDescription.click();
		txtDescription.sendKeys(description);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	    WebElement descField = wait.until(ExpectedConditions.elementToBeClickable(By.name("description")));
//	    // Click only if necessary; otherwise, directly sendKeys
//	    descField.click(); 
//	    descField.clear(); // Clear existing text if needed
//	    descField.sendKeys(description);

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
	
	public String getSuccessMessage() {
		try {
			return successMsg.getText();
		} catch (Exception e) {
			return "";
		}
	}

}
