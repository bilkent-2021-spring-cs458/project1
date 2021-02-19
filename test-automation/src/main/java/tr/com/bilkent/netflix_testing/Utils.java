package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;

public final class Utils {
	public static final String BASE_URL = "http://3.140.185.156";
	public static final String SIGNIN_URL = BASE_URL + "/signin";

	public static final String VALID_EMAIL = "test@test.test";
	public static final String VALID_PASSWORD = "test";

	private Utils() {
	}

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
}
