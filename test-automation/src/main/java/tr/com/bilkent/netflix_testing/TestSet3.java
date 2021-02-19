package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import lombok.Getter;
import tr.com.bilkent.netflix_testing.page_object.SignInPage;

/**
 * Third test set. Tests copying the password from the password field, which
 * should not be possible.
 */
public class TestSet3 implements TestSet {
	private final WebDriver driver;
	@Getter
	private TestSetResult result;

	/**
	 * Creates this test set.
	 * 
	 * @param driver The driver to use for this test
	 */
	public TestSet3(WebDriver driver) {
		this.driver = driver;
		result = new TestSetResult(0, 1);
	}

	public TestSetResult run() {
		if (test()) {
			result.incrementPassedCases();
		}

		return result;
	}

	/**
	 * Tests by first copying email to clipboard to make clipboard invalid. Then
	 * types the password and tries to copy the password from there. After that,
	 * sets the password field to clipboard contents, and tries signing in.
	 * 
	 * @return true if the user could not sign in, false otherwise
	 */
	private boolean test() {
		try {
			Util.resetWebDriver(driver);
			driver.get(Util.SIGNIN_URL);
			SignInPage page = new SignInPage(driver);
			Actions action = new Actions(driver);

			page.enterEmail(Util.VALID_EMAIL);
			// Clear clipboard by writing email into it
			action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
			action.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();

			page.enterPassword(Util.VALID_PASSWORD);

			action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
			action.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();

			page.enterPassword("");
			action.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();

			page.clickSignIn();
			page.waitAndReturnLandingPage();
			return false;
		} catch (Exception e) {
			return true;
		}
	}
}
