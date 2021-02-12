package piyush.E2EFrameworkProject_Section29;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.HomePage1;
import pageObject.LoginPage1;

public class HomePageTest extends Base1 {

	// If you want to run all the test cases in parallel then driver has to be
	// declared locally
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(Base1.class.getName());

	// BeforeTest not working with DataProvider annotation for second and above
	// parameters
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
	}

	@Test(dataProvider = "getData")
	public void basePageNavigation(String username, String password) throws IOException {

		driver.get(prop.getProperty("url"));

		// Home Page Test
		HomePage1 hp = new HomePage1(driver);

		LoginPage1 lp = hp.signin1();

		// Login Page Test
		lp.id().sendKeys(username);
		lp.password().sendKeys(password);
		lp.signinButton().click();

	}

	@DataProvider
	public Object[][] getData() {

		Object[][] data = new Object[2][2];

		data[0][0] = "username@gmail.com";
		data[0][1] = "password";

		data[1][0] = "newusername@gmail.com";
		data[1][1] = "newPassword";

		return data;
	}

	@AfterTest
	public void close() {
		driver.quit();
		log.info("Driver closed in HomePageTest");
	}
}
