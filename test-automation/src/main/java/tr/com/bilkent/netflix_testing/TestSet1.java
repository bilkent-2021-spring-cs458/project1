package tr.com.bilkent.netflix_testing;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebDriver;

import lombok.Getter;
import tr.com.bilkent.netflix_testing.page_object.SignInPage;

/**
 * First test set. Includes test cases for testing various combinations of
 * emails and passwords. In particular, tests lowercase and uppercase values,
 * short invalid values, very long invalid values, minimum and maximum length
 * valid values.
 */
public class TestSet1 implements TestSet {
	private final JSONArray data;
	private final WebDriver driver;

	@Getter
	private TestSetResult result;

	/**
	 * Creates this test set by fetching its data from the JSON resource file.
	 * 
	 * @param driver The driver to use for this test
	 */
	public TestSet1(WebDriver driver) {
		JSONTokener tokener = new JSONTokener(getClass().getResourceAsStream("TestCaseOneData.json"));
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

	/**
	 * Runs a test case specified by the JSON object.
	 * 
	 * @param data JSON data object
	 * @return true if the test succeeds, false otherwise
	 */
	private boolean run(JSONObject data) {
		Util.resetWebDriver(driver);

		String caseResult;
		try {
			driver.get(Util.SIGNIN_URL);
			SignInPage signIn = new SignInPage(driver);
			signIn.loginUser(data.getString("email"), data.getString("password"));
			caseResult = "success";
		} catch (Exception e) {
			caseResult = "fail";
		}
		return caseResult.equals(data.getString("expected_result"));
	}
}
