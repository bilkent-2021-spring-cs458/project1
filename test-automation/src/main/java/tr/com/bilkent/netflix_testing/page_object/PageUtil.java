package tr.com.bilkent.netflix_testing.page_object;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Provides utility methods for working with pages.
 */
public class PageUtil {
	private PageUtil() {
	}

	/**
	 * Finds the {@link PageName} associated with the given URL.
	 * 
	 * @param url The URL
	 * @return the associated {@link PageName}
	 */
	public static PageName getPageName(String url) {
		try {
			String path = new URL(url).getPath();
			switch (path) {
			case "":
			case "/":
				return PageName.LANDING;
			case "/signin":
				return PageName.SIGNIN;
			default:
				throw new IllegalStateException("Unknown page");
			}
		} catch (MalformedURLException e) {
			throw new IllegalStateException("Unknown page");
		}
	}

	/**
	 * To be used in {@link WebDriverWait} to wait until the URL shows the specified
	 * page
	 * 
	 * @param waitFor The page to wait for
	 * @return the function to be used for waiting
	 */
	public static ExpectedCondition<Boolean> waitUntilPage(PageName waitFor) {
		return driver -> getPageName(driver.getCurrentUrl()) == waitFor;
	}
}
