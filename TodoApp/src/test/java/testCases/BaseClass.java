package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	
	WebDriver driver;
	Properties property;
	
	@BeforeClass
	public void setup() throws IOException {
		
		property = new Properties();
		FileReader fi = new FileReader("./src/test/resources/config.properties");
		property.load(fi);
		
		String appUrl = property.getProperty("appUrl");
		
		
		
		driver = new ChromeDriver();
		driver.get(appUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void captureScreen(String name) {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		
		String timestamp = new SimpleDateFormat("yyyyMMddhhss").format(new Date());
		String targetFilePath = System.getProperty("user.dir")+"\\Screenshots\\"+name+"_"+timestamp+".png";
		
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
	}

}
