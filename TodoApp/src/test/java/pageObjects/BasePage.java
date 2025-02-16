package pageObjects;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void waitForURLToBe(String expectedURL, int timeoutSeconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	    wait.until(ExpectedConditions.urlToBe(expectedURL));
	}
	
	public void waitForElement(WebElement element,int timeoutSeconds ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public boolean waitForElementFluent(WebElement element, int timeoutSeconds, int polling) {
		
		try {
			
			Wait<WebDriver> fluentWait = new FluentWait<>(driver)
					.withTimeout(Duration.ofSeconds(timeoutSeconds))
					.pollingEvery(Duration.ofMillis(polling))
					.ignoring(NoSuchElementException.class);
					
					fluentWait.until(ExpectedConditions.visibilityOf(element));
					return true;
		}
		catch(TimeoutException e) {
			return false;
		}
			
	}
}
