package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	WebDriver driver;
	Properties property;
	Logger logger; //log4j
	
	@BeforeClass
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws IOException {
		
		logger = LogManager.getLogger(this.getClass());
		
		property = new Properties();
		FileReader fi = new FileReader("./src/test/resources/config.properties");
		property.load(fi);
		String appUrl = property.getProperty("appUrl");
		
		switch(browser.toLowerCase()) {
			case "chrome": driver = new ChromeDriver();break;
			case "edge" : driver = new EdgeDriver();break;
			default : System.out.println("Invalid Browser Name");return;
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(appUrl); 
		driver.manage().window().maximize();

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
