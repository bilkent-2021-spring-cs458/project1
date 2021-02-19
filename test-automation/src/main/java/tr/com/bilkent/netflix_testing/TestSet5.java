package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;

import lombok.Getter;
import tr.com.bilkent.netflix_testing.page_object.LandingPage;
import tr.com.bilkent.netflix_testing.page_object.SignInPage;

/**
 * Fifth test case. Tests whether the remember me option works.
 */
public class TestSet5 implements TestSet {
	private final WebDriver driver;

	@Getter
	private TestSetResult result;

	/**
	 * Creates this test set.
	 * 
	 * @param driver The driver to use for this test
	 */
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

	/**
	 * First signs in with the remember me option turned on. Then signs out, and
	 * clears browser temporary data to simulate them expiring. After that, opens
	 * the login page and checks whether the email field is correctly initialized.
	 * 
	 * @return true if email field is correctly set up, false otherwise
	 */
	private boolean test1() {
		try {
			// Remember me is on
			LandingPage landingPage = Util.tryValidLogin(driver);
			landingPage.signOut();

			// Simulate long time pass
			driver.manage().deleteAllCookies();
			((WebStorage) driver).getSessionStorage().clear();

			// Check if email box is filled correctly
			driver.get(Util.SIGNIN_URL);
			String email = new SignInPage(driver).getEmailFieldValue();

			return Util.VALID_EMAIL.equals(email);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * First signs in with the remember me option turned off. Then signs out, and
	 * clears browser temporary data to simulate them expiring. After that, opens
	 * the login page and checks whether the email field is empty.
	 * 
	 * @return true if email field is correctly set up (i.e. empty), false otherwise
	 */
	private boolean test2() {
		try {
			// Remember me is off
			LandingPage landingPage = Util.tryValidLogin(driver, false);
			landingPage.signOut();

			// Simulate long time pass
			driver.manage().deleteAllCookies();
			((WebStorage) driver).getSessionStorage().clear();

			// Check if email box is filled
			driver.get(Util.SIGNIN_URL);
			String email = new SignInPage(driver).getEmailFieldValue();

			return !Util.VALID_EMAIL.equals(email);
		} catch (Exception e) {
			return false;
		}
	}
}
