package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public static String appUrl;
	public Properties property;
	public Logger logger; //log4j
	
	@BeforeClass(groups= {"regression"})
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws IOException {
		
		logger = LogManager.getLogger(this.getClass());
		
		property = new Properties();
		FileReader fi = new FileReader("./src/test/resources/config.properties");
		property.load(fi);

		
		if(property.getProperty("execution_env").equalsIgnoreCase("remote")) {
			appUrl = property.getProperty("remote_appUrl");
			String hubURL = "http://localhost:4444/wd/hub";
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			
			switch(os.toLowerCase()) {
				case "windows" : desiredCapabilities.setPlatform(Platform.WIN11);break;
				case "mac" : desiredCapabilities.setPlatform(Platform.MAC);break;
				case "linux" : desiredCapabilities.setPlatform(Platform.LINUX);break;
				default:System.out.println("No Matching Operation System");return;
			}
			
			switch(browser.toLowerCase()) {
				case "chrome": desiredCapabilities.setBrowserName("chrome");break;
				case "edge" : desiredCapabilities.setBrowserName("MicrosoftEdge");break;
				default : System.out.println("No Matching Browser");return;
			}
			
			driver = new RemoteWebDriver(new URL(hubURL), desiredCapabilities);
			 	
		}
		if(property.getProperty("execution_env").equalsIgnoreCase("local")) {
			appUrl = property.getProperty("appUrl");
			switch(browser.toLowerCase()) {
			case "chrome": driver = new ChromeDriver();break;
			case "edge" : driver = new EdgeDriver();break;
			default : System.out.println("Invalid Browser Name");return;
			}
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(appUrl); 
		driver.manage().window().maximize();

	}
	
	@AfterClass(groups= {"regression"})
	public void tearDown() {
		driver.quit();
	}

	public String captureScreen(String testMethodname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + testMethodname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}


}
