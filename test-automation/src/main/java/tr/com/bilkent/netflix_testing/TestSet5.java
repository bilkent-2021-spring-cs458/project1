package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;

import lombok.Getter;
import tr.com.bilkent.netflix_testing.page_object.LandingPage;
import tr.com.bilkent.netflix_testing.page_object.SignInPage;

public class TestSet5 implements TestSet {
	private final WebDriver driver;

	@Getter
	private TestSetResult result;

	public TestSet5(WebDriver driver) {
		this.driver = driver;
		result = new TestSetResult(0, 2);
	}

	public TestSetResult run() {
		if (test1()) {
			result.incrementPassedCases();
		}
		if (test2()) {
			result.incrementPassedCases();
		}

		return result;
	}

	private boolean test1() {
		try {
			// Remember me is on
			LandingPage landingPage = Utils.tryValidLogin(driver);
			landingPage.signOut();

			// Simulate long time pass
			driver.manage().deleteAllCookies();
			((WebStorage) driver).getSessionStorage().clear();

			// Check if email box is filled correctly
			driver.get(Utils.SIGNIN_URL);
			String email = new SignInPage(driver).getEmailFieldValue();

			return Utils.VALID_EMAIL.equals(email);
		} catch (Exception e) {
			return false;
		}
	}

	private boolean test2() {
		try {
			// Remember me is off
			LandingPage landingPage = Utils.tryValidLogin(driver, false);
			landingPage.signOut();

			// Simulate long time pass
			driver.manage().deleteAllCookies();
			((WebStorage) driver).getSessionStorage().clear();

			// Check if email box is filled
			driver.get(Utils.SIGNIN_URL);
			String email = new SignInPage(driver).getEmailFieldValue();

			return !Utils.VALID_EMAIL.equals(email);
		} catch (Exception e) {
			return false;
		}
	}
}
