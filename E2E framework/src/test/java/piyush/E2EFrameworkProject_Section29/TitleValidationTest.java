package piyush.E2EFrameworkProject_Section29;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObject.HomePage1;



public class TitleValidationTest extends Base1 {
	
	// If you want to run all the test cases in parallel then driver has to be declared locally
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(Base1.class.getName());

	@BeforeTest
	public void initialize() throws IOException {
		log.info("Chrome driver starting");
		driver = initializeDriver();

		log.info("Navigated to url");
		driver.get(prop.getProperty("url"));
	}

	@Test
	public void titleValidation() {


		HomePage1 hp = new HomePage1(driver);
		
		Assert.assertEquals(hp.createAccount().getText(), "Create Account");
		log.info("Titel Validated");
	}

	@AfterTest
	public void close() {
		driver.quit();
		log.info("Driver closed in TitelValidationTest");
	}

}
