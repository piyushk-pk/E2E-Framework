package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage1 {

	public WebDriver driver;

	public HomePage1(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page Factory
	@FindBy(css = "a[title*='Already a user']")
	private WebElement signin;

	@FindBy(css = "a[title='Create Rediffmail Account']")
	private WebElement createAccount;

	// Validating title
	public WebElement createAccount() {
		return createAccount;
	}

	public WebElement signin() {
		return signin;
	}

	// Page Object Model
	private By signin1 = By.cssSelector("a[title*='Already a user']");

	public LoginPage1 signin1() {
		driver.findElement(signin1).click();
		LoginPage1 lp = new LoginPage1(driver);
		return lp;
	}

}
