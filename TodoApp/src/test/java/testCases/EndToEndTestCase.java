package testCases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.CreateUpdateTodoPage;
import pageObjects.DeleteAccountPage;
import pageObjects.Header;
import pageObjects.ListTodoPage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import pageObjects.WelcomePage;

public class EndToEndTestCase extends BaseClass{
	
	@Test(groups= {"regression"})
	public void verifyE2EFuncionality() {
		
		logger.info("Starting Of E2E TC");
		
		SoftAssert softAssert = new SoftAssert();
		
		logger.info("Navigate to Register Page");
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickRegister();
		

		
		logger.debug("Entering registration details");
		RegisterPage registerPage = new RegisterPage(driver);
		String username = property.getProperty("username");
		String password = property.getProperty("password");
		
		registerPage.setUsername(username);
		registerPage.setPassword(password);
		registerPage.clickRegister();
		
		
		
		if(registerPage.isSuccessMessageDisplayed()) {
			softAssert.assertTrue(true, "No successMessage");
		}
		else if (registerPage.isFailureMessageDisplayed()) {
        logger.error("User registration failed: User Already Exists");
        logger.debug("Debug logs: Failure message detected on Register Page");
			Assert.fail("User Already Exists");

		}else {
			 logger.error("Neither success nor failure message displayed.");
		    Assert.fail("No success or failure message displayed, check the UI.");
		}
		
		
		
	    logger.info("Navigating back and logging in with new user");
		driver.navigate().back();
		
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();
		
		
	    logger.info("Navigating to Todo Management");
		WelcomePage welcomePage = new WelcomePage(driver);
		welcomePage.clickManageTodoButton();
		
		ListTodoPage listTodoPage = new ListTodoPage(driver);
		listTodoPage.clickCreateNewTodoButton();
		
		logger.debug("Filling new Todo details");
		CreateUpdateTodoPage createUpdateTodoPage = new CreateUpdateTodoPage(driver);
		createUpdateTodoPage.setDescription(property.getProperty("sampledescription"));
		createUpdateTodoPage.setTargetDate(property.getProperty("sampleTargerDate"));
		createUpdateTodoPage.setIsDone(property.getProperty("isDone"));
		createUpdateTodoPage.clickSaveButton();
		

		
		String expectedMessage = "New Todo created successfully!";
		String actualMessage = createUpdateTodoPage.getSuccessMessage();
		
//		if (!actualMessage.equals(expectedMessage)) {
//        logger.warn("New Todo creation message mismatch. Expected: " + expectedMessage + ", Found: " + actualMessage);
//    }
		
		softAssert.assertEquals(actualMessage, expectedMessage, "No New Todo Created");
		
		if(actualMessage.equals(expectedMessage)) {
			
	        logger.info("Navigating to update Todo");
			driver.navigate().back();
			listTodoPage.clickUpdateTodoButton();
			createUpdateTodoPage.setDescription(property.getProperty("updatedescription"));
			createUpdateTodoPage.setTargetDate(property.getProperty("updatedTargetDate"));
			createUpdateTodoPage.setIsDone(property.getProperty("updatedIsDone"));
			createUpdateTodoPage.clickSaveButton();
			
			String expectedUpdateMessage = "Your changes have been saved";
			String actualUpdateMessage = createUpdateTodoPage.getSuccessMessage();
			softAssert.assertEquals(actualMessage, expectedMessage, "Todo Item Not Updated");
			
			driver.navigate().back();
			
			
			listTodoPage.clickDeleteTodoButton();
			
			String expectedTodoDeleteMessage = "Todo item has been deleted";
			String actualTodoDeleteMessage = createUpdateTodoPage.getSuccessMessage();
			softAssert.assertEquals(actualTodoDeleteMessage, expectedTodoDeleteMessage, "Todo Item Not Deleted");
			
			
		}
		
	    logger.info("Logging out and deleting account");
		Header header = new Header(driver);
		header.clickLogoutButton();
		
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();
		
		welcomePage.clickDeleteAccountButton();
		
		DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);
		deleteAccountPage.setUsername(username);
		deleteAccountPage.clickDeleteButton();
		
		String expectedURL = property.getProperty("appUrl");
		
		String actualURL = driver.getCurrentUrl();
		
		
		softAssert.assertTrue(deleteAccountPage.isDeleted(expectedURL), "Account Not Deleted");
		
	    logger.info("End-to-End Test Case Execution Completed");
		softAssert.assertAll();
		
	}

}
