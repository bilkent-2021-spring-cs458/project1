package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.WebDriver;

import tr.com.bilkent.netflix_testing.page_object.LandingPage;
import tr.com.bilkent.netflix_testing.page_object.SignInPage;

public class TestSetTwo {
	private WebDriver driver;

	public TestSetTwo(WebDriver driver) {
		this.driver = driver;
	}

	public double run() {
		int success = 0;
		if (test1()) {
			success++;
		}
		if (test2()) {
			success++;
		}

		return success / 2.0;
	}

	private boolean test1() {
		try {
			init();
			return tryGoingBack();
		} catch (Exception e) {
			return false;
		}
	}

	private boolean test2() {
		try {
			init();
			return tryOpeningSignIn();
		} catch (Exception e) {
			return false;
		}
	}

	private void init() {
		Utils.resetWebDriver(driver);
		driver.get(Utils.SIGNIN_URL);
		SignInPage signInPage = new SignInPage(driver);
		LandingPage landingPage = signInPage.loginUser(Utils.VALID_EMAIL, Utils.VALID_PASSWORD);
		if (!landingPage.isSignedIn()) {
			throw new IllegalStateException("Initial sign in did not work");
		}
	}

	private boolean tryGoingBack() {
		driver.navigate().back();
		LandingPage landingPage = new LandingPage(driver);
		return landingPage.isSignedIn();
	}

	private boolean tryOpeningSignIn() {
		driver.navigate().to(Utils.SIGNIN_URL);
		LandingPage landingPage = new LandingPage(driver);
		return landingPage.isSignedIn();
	}
}
