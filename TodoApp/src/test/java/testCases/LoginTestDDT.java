package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Header;
import pageObjects.LoginPage;
import pageObjects.WelcomePage;
import utilities.DataProviders;

public class LoginTestDDT extends BaseClass{
	
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void verify_loginDDT(String username, String password, String isValid) {
		
		logger.info("TC STARTED EXECUTING");
		
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		logger.debug("Username is "+ username + "password is "+ password);
		loginPage.clickLogin();
		
		WelcomePage welcomePage = new WelcomePage(driver);
		boolean isLandedToWelcomepage = welcomePage.isWelcomePageTitleExists();
		
		Header header = new Header(driver);
		
		if(isValid.equalsIgnoreCase("Valid")) {
			if(isLandedToWelcomepage) {
				Assert.assertTrue(true);
				header.clickLogoutButton();
			}
			else {
				Assert.assertFalse(false);
			}
		}
		else {
			if(isLandedToWelcomepage) {
				Assert.assertTrue(false);
			}
			else {
				Assert.assertFalse(true);
			}
		}
		
		logger.info("TC FINISHED EXECUTING");
			
	}
	
}