package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Header;
import pageObjects.LoginPage;
import pageObjects.WelcomePage;
import utilities.DataProviders;

public class LoginTestDDT extends BaseClass{
	
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups= {"smoke"})
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
		
		if (isValid.equalsIgnoreCase("Valid")) {
		    Assert.assertTrue(isLandedToWelcomepage, "Login should have succeeded but did not.");
		    if (isLandedToWelcomepage) {
		        header.clickLogoutButton();
		    }
		} else {
		    Assert.assertFalse(isLandedToWelcomepage, "Login should have failed, but user landed on welcome page.");
		    if (isLandedToWelcomepage) {
		        header.clickLogoutButton();
		    }
		}
		
		logger.info("TC FINISHED EXECUTING");
			
	}
	
}