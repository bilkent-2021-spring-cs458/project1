package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import lombok.Getter;
import tr.com.bilkent.netflix_testing.page_object.SignInPage;

public class TestSetThree {
	private final WebDriver driver;
	@Getter
	private TestSetResult result;

	public TestSetThree(WebDriver driver) {
		this.driver = driver;
		result = new TestSetResult(0, 1);
	}

	public TestSetResult run() {
		if (test()) {
			result.incrementPassedCases();
		}

		return result;
	}

	private boolean test() {
		try {
			Utils.resetWebDriver(driver);
			driver.get(Utils.SIGNIN_URL);
			SignInPage page = new SignInPage(driver);
			Actions action = new Actions(driver);

			page.enterEmail(Utils.VALID_EMAIL);
			// Clear clipboard by writing email into it
			action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
			action.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();

			page.enterPassword(Utils.VALID_PASSWORD);

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
