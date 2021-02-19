package tr.com.bilkent.netflix_testing;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import lombok.Getter;
import tr.com.bilkent.netflix_testing.page_object.SignInPage;

public class TestSetFour {
	private final JSONArray data;
	private final WebDriver driver;

	@Getter
	private TestSetResult result;

	public TestSetFour(WebDriver driver) {
		JSONTokener tokener = new JSONTokener(getClass().getResourceAsStream("TestCaseFourData.json"));
		data = new JSONArray(tokener);
		this.driver = driver;
		result = new TestSetResult(0, data.length());
	}

	public TestSetResult run() {
		data.forEach(obj -> {
			boolean caseResult = run((JSONObject) obj);
			if (caseResult) {
				result.incrementPassedCases();
			}
		});
		return result;
	}

	private boolean run(JSONObject data) {
		Utils.resetWebDriver(driver);

		try {
			driver.get(Utils.SIGNIN_URL);
			SignInPage signIn = new SignInPage(driver);
			signIn.loginUser(data.getString("email"), data.getString("password"));

			driver.switchTo().alert();
			// No alerts should be visible. XSS attack success
			return false;
		} catch (NoAlertPresentException | WrongPageException e) {
			// There were no XSS alerts, and the sign in failed (test succeeded)
			// Check if database works
			try {
				Utils.tryValidLogin(driver);
				return true;
			} catch (Exception e1) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
