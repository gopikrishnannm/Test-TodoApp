package testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterTest extends BaseClass{
	
	
	@Test(dataProvider="usernameAndPasswordProvider")
	public void test_RegisterWithValidAndInvalidUsernames(String username, boolean isValid) {
		
		if(isValid) {
			System.out.println("Testing with valid Username" + );
		}
		
	}
	
	
	@DataProvider(name="usernameAndPasswordProvider")
	public Object[][] usernameProvider(){
		return new Object[][] {
			{"validUser",true},
			{"1invalidUser",false}
			{""}
		}
	}
	
	
	

}
