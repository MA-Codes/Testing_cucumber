package StepDefnitions;

import com.qa.base.TestBase;

import com.qa.pages.login;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

public class LoginPageSteps extends TestBase {

	WebDriver driver;
	login loginP;
	@Then("^login into website$")
	public void login_into_website() throws Throwable {
		loginP = new login();
		loginP.signin();
	}

	@Then("^verify logout$")
	public void verify_logout() throws Throwable {
		loginP = new login();
		loginP.logout();
	}
}
