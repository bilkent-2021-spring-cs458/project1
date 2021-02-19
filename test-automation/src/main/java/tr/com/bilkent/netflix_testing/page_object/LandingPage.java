package tr.com.bilkent.netflix_testing.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tr.com.bilkent.netflix_testing.WrongPageException;

public class LandingPage {
	private static final By SIGN_IN_BUTTON = By.xpath("//button[contains(string(), 'Sign In')]");
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

	public void signOut() {
		WebElement button = driver.findElement(SIGN_OUT_BUTTON);
		button.click();

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(SIGN_IN_BUTTON));
	}
}
