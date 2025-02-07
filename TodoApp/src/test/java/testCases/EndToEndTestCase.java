package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.LoginPage;
import pageObjects.RegisterPage;

public class EndToEndTestCase extends BaseClass{
	
	@Test()
	public void verifyE2EFuncionality() {
		
		SoftAssert softAssert = new SoftAssert();
		
		//Navigate to Register Page
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickRegister();
		
		//Register Functionality
		RegisterPage registerPage = new RegisterPage(driver);
		
		String username = property.getProperty("username");
		String password = property.getProperty("password");
		
		registerPage.setUsername(username);
		registerPage.setPassword(password);
		registerPage.clickRegister();
		
		if (registerPage.isFailureMessageDisplayed()) {
			Assert.fail("User Already Exists");
		}
		softAssert.assertTrue(registerPage.isSuccessMessageDisplayed());
		
		driver.navigate().back();
		
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();
		
		
		softAssert.assertAll();
		
	}

}
