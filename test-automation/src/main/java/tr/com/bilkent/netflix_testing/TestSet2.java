package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.WebDriver;

import lombok.Getter;
import tr.com.bilkent.netflix_testing.page_object.LandingPage;

public class TestSet2 implements TestSet {
	private final WebDriver driver;
	@Getter
	private TestSetResult result;

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

	private boolean test1() {
		try {
			Utils.tryValidLogin(driver);
			return tryGoingBack();
		} catch (Exception e) {
			return false;
		}
	}

	private boolean test2() {
		try {
			Utils.tryValidLogin(driver);
			return tryOpeningSignIn();
		} catch (Exception e) {
			return false;
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
