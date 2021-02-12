package piyush.E2EFrameworkProject_Section29;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base1 {

   /*
	*  Declearing the driver and prop at class level so that other classes can
	* inherit it
	* 
	* If driver is declared as static then it can only support sequential execution, 
	* unless a local driver is initialized in the inherited class to run the test cases
	* in parallel even if driver is static 
	*/
	public static WebDriver driver; // In this framework it will run all the testcases, because a local copy of driver is maintained
	public Properties prop;

	public WebDriver initializeDriver() throws IOException {

		// Initializing properties object.
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");

		// loading the "data.properties" file.
		prop.load(fis);

		/*
		 * To provide the browser name directly from maven without using property object
		 * Command is = mvn test -Dbrowser=chrome String browserName =
		 * System.getProperty("browser");
		 */

		// works with mvn command
		String browserName = System.getProperty("browser");

		// works with property file
		// String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.contains("chrome")) {
			
			
			
			// To run chrome browser in headless mode
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				
			options.addArguments("headless");
			}
			
			
			
			driver = new ChromeDriver(options);
		} 
		
		else if (browserName.equalsIgnoreCase("IE")) {
			driver = new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ss = (TakesScreenshot) driver;
		File src = ss.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(src, new File(destinationFile));
		return destinationFile;
	}
}
