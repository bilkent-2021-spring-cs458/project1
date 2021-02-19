package tr.com.bilkent.netflix_testing;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebDriver;

import lombok.Getter;
import tr.com.bilkent.netflix_testing.page_object.SignInPage;

public class TestSet1 implements TestSet {
	private final JSONArray data;
	private final WebDriver driver;

	@Getter
	private TestSetResult result;

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

	private boolean run(JSONObject data) {
		Utils.resetWebDriver(driver);

		String caseResult;
		try {
			driver.get(Utils.SIGNIN_URL);
			SignInPage signIn = new SignInPage(driver);
			signIn.loginUser(data.getString("email"), data.getString("password"));
			caseResult = "success";
		} catch (Exception e) {
			caseResult = "fail";
		}
		return caseResult.equals(data.getString("expected_result"));
	}
}
