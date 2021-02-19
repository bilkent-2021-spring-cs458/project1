package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;

import tr.com.bilkent.netflix_testing.page_object.LandingPage;
import tr.com.bilkent.netflix_testing.page_object.SignInPage;

/**
 * A collection of utility methods for test cases.
 */
public final class Util {
	/**
	 * The landing page URL of the website
	 */
	public static final String BASE_URL = "http://3.140.185.156";
	/**
	 * The sign in page URL of the website
	 */
	public static final String SIGNIN_URL = BASE_URL + "/signin";

	/**
	 * A valid user email registered in the website. To be used in combination with
	 * {@link #VALID_PASSWORD}.
	 */
	public static final String VALID_EMAIL = "test@test.test";
	/**
	 * A valid user password registered in the website. To be used in combination
	 * with {@link #VALID_EMAIL}.
	 */
	public static final String VALID_PASSWORD = "test";

	private Util() {
	}

	/**
	 * Resets the given driver by deleting its cookies, and clearing its
	 * sessionStorage and localStorage.
	 * 
	 * @param driver The driver to reset.
	 */
	public static void resetWebDriver(WebDriver driver) {
		driver.manage().deleteAllCookies();
		try {
			WebStorage webStorage = (WebStorage) driver;
			webStorage.getSessionStorage().clear();
			webStorage.getLocalStorage().clear();
		} catch (Exception e) {
			// Probably it is the first test case, so no need
		}
	}

	/**
	 * Tries a valid login using the given driver. The driver is reset before
	 * logging in. Remember me option is set to true. Throws an exception in case of
	 * not being able to log in.
	 * 
	 * @param driver the driver to use for sign in
	 * @return The resulting landing page after sign in.
	 */
	public static LandingPage tryValidLogin(WebDriver driver) {
		return tryValidLogin(driver, true);
	}

	/**
	 * Tries a valid login using the given driver. The driver is reset before
	 * logging in. Throws an exception in case of not being able to log in.
	 * 
	 * @param driver         the driver to use for sign in
	 * @param rememberOption whether to use remember me option
	 * @return The resulting landing page after sign in.
	 */
	public static LandingPage tryValidLogin(WebDriver driver, boolean rememberOption) {
		resetWebDriver(driver);
		driver.get(Util.SIGNIN_URL);

		SignInPage signInPage = new SignInPage(driver);
		signInPage.setRememberOption(rememberOption);
		LandingPage landingPage = signInPage.loginUser(VALID_EMAIL, VALID_PASSWORD);

		if (!landingPage.isSignedIn()) {
			throw new IllegalStateException("Initial sign in did not work");
		}
		return landingPage;
	}
}
