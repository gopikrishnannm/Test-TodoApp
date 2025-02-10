package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListTodoPage extends BasePage{

	public ListTodoPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//button[text()='Create New Todo']")
	WebElement btnCreateNewTodo;
	
	@FindBy(xpath = "//button[text()='Delete']")
	WebElement btnDeleteTodo;
	
	@FindBy(xpath = "//button[text()='Update']")
	WebElement btnUpdateTodo;
	
	public void clickCreateNewTodoButton() {
		btnCreateNewTodo.click();
	}

	public void clickDeleteTodoButton() {
		btnDeleteTodo.click();
	}
	
	public void clickUpdateTodoButton() {
		btnUpdateTodo.click();
	}
	

	

}
