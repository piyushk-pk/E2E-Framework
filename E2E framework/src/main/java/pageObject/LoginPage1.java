package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage1 {

	WebDriver driver;

	public LoginPage1(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "login1")
	private WebElement id;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(css = "input[value='Sign in']")
	private WebElement signinButton;

	public WebElement id() {
		return id;
	}

	public WebElement password() {
		return password;
	}

	public WebElement signinButton() {
		return signinButton;
	}
}
