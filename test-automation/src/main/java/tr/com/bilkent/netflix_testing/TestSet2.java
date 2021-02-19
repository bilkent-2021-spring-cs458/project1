package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.WebDriver;

import lombok.Getter;
import tr.com.bilkent.netflix_testing.page_object.LandingPage;

/**
 * Second test set. Tests navigating to the sign in page after user signs in.
 * The user should not be able to do so.
 */
public class TestSet2 implements TestSet {
	private final WebDriver driver;
	@Getter
	private TestSetResult result;

	/**
	 * Creates this test set.
	 * 
	 * @param driver The driver to use for this test
	 */
	public TestSet2(WebDriver driver) {
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
	 * Tests using browser's history and going back to sign in page.
	 * 
	 * @return true if the test succeeds, false otherwise
	 */
	private boolean test1() {
		try {
			Util.tryValidLogin(driver);
			return tryGoingBack();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Tests directly opening the sing in URL
	 * 
	 * @return true if the test succeeds, false otherwise
	 */
	private boolean test2() {
		try {
			Util.tryValidLogin(driver);
			return tryOpeningSignIn();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Tries to go back in browser history. Will throw exception if another page is
	 * shown instead of the landing page.
	 * 
	 * @return true if the user is still signed in, false otherwise
	 */
	private boolean tryGoingBack() {
		driver.navigate().back();
		LandingPage landingPage = new LandingPage(driver);
		return landingPage.isSignedIn();
	}

	/**
	 * Tries to open sign in URL. Will throw exception if another page is shown
	 * instead of the landing page.
	 * 
	 * @return true if the user is still signed in, false otherwise
	 */
	private boolean tryOpeningSignIn() {
		driver.navigate().to(Util.SIGNIN_URL);
		LandingPage landingPage = new LandingPage(driver);
		return landingPage.isSignedIn();
	}
}
