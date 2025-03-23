package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.RegisterPage;

public class RegisterTest extends BaseClass{
	
    @Test
    public void messageTest() {
        LoginPage lp = new LoginPage(driver);
        lp.clickRegister();
        RegisterPage rp = new RegisterPage(driver);

        String username = "User" + randomString(); // Ensuring unique username
        String password = randomAphaNumeric();

        // First registration attempt
        rp.setUsername(username);
        rp.setPassword(password);
        rp.clickRegister();

        verifyRegistrationMessage(rp, true);  // ✅ Checks success or failure and waits if needed

        // Second attempt with the same username (should fail)
        rp.setUsername(username);
        rp.setPassword(password);
        rp.clickRegister();

        verifyRegistrationMessage(rp, false); // ✅ Checks failure scenario
    }
    
    @Test
    public void usernameLengthTest() {
        RegisterPage rp = new RegisterPage(driver);
        rp.setUsername(randomStringLessThanFive());  // Less than 5 characters
        rp.setPassword("password123");
        rp.clickRegister();
        
        String actualMessage = rp.getMessage();
        Assert.assertEquals(actualMessage, "Username must be at least 5 characters!", "Username length validation failed.");
    }

    @Test
    public void passwordLengthTest() {
        RegisterPage rp = new RegisterPage(driver);
        rp.setUsername("validUser");
        rp.setPassword(randomStringLessThanFive());  // Less than 5 characters
        rp.clickRegister();
        
        String actualMessage = rp.getMessage();
        Assert.assertEquals(actualMessage, "Password must be at least 5 characters!", "Password length validation failed.");
    }


    private void verifyRegistrationMessage(RegisterPage rp, boolean isFirstAttempt) {
        String actualMessage = rp.getMessage();
        String expectedSuccessMessage = "User Created Successfully!";
        String expectedFailureMessage = "Username Already Taken!";

        if (actualMessage.equals(expectedSuccessMessage)) {
            System.out.println("User creation successful!");
            Assert.assertTrue(isFirstAttempt, "Unexpected success on second attempt!");
            
            // ✅ Wait for success message to disappear before next attempt
            rp.waitForMessageToDisappear();
        } else if (actualMessage.equals(expectedFailureMessage)) {
            System.out.println("User creation failed: Username already taken.");
            Assert.assertFalse(isFirstAttempt, "Unexpected failure on first attempt!");
        } else {
            Assert.fail("Unexpected message displayed: " + actualMessage);
        }
    }
	
}
