package tr.com.bilkent.netflix_testing;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebDriver;

import tr.com.bilkent.netflix_testing.page_object.SignInPage;

public class TestSetOne {
	private final JSONArray data;

	private WebDriver driver;
	private int passedCases;

	public TestSetOne(WebDriver driver) {
		JSONTokener tokener = new JSONTokener(getClass().getResourceAsStream("TestCaseOneData.json"));
		data = new JSONArray(tokener);
		this.driver = driver;
		passedCases = 0;
	}

	public double run() {
		data.forEach(obj -> {
			boolean result = run((JSONObject) obj);
			if (result) {
				passedCases++;
			}
		});
		return 1.0 * passedCases / data.length();
	}

	private boolean run(JSONObject data) {
		Utils.resetWebDriver(driver);

		String result;
		try {
			driver.get(Utils.SIGNIN_URL);
			SignInPage signIn = new SignInPage(driver);
			signIn.loginUser(data.getString("email"), data.getString("password"));
			result = "success";
		} catch (Exception e) {
			result = "fail";
		}
		return result.equals(data.getString("expected_result"));
	}
}
