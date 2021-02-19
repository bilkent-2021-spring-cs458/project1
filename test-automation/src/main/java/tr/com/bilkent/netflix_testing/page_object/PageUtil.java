package tr.com.bilkent.netflix_testing.page_object;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.support.ui.ExpectedCondition;

public class PageUtil {
	private PageUtil() {
	}

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

	public static ExpectedCondition<Boolean> waitUntilPage(PageName waitFor) {
		return driver -> getPageName(driver.getCurrentUrl()) == waitFor;
	}
}
