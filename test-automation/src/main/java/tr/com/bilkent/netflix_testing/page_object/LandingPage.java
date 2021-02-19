package tr.com.bilkent.netflix_testing.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import tr.com.bilkent.netflix_testing.WrongPageException;

public class LandingPage {
	private static final By SIGN_OUT_BUTTON = By.xpath("//button[contains(string(), 'Sign out')]");

	protected WebDriver driver;

	public LandingPage(WebDriver driver) {
		this.driver = driver;

		PageName currentPage = PageUtil.getPageName(driver.getCurrentUrl());
		if (currentPage != PageName.LANDING) {
			throw new WrongPageException("Not in the landing page");
		}
	}

	public boolean isSignedIn() {
		try {
			driver.findElement(SIGN_OUT_BUTTON);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public SignInPage signOut() {
		WebElement button = driver.findElement(SIGN_OUT_BUTTON);
		boolean flag = button.isEnabled() && button.isDisplayed();
		if (flag) {
			button.click();
			return new SignInPage(driver);
		}
		return null;
	}
}
