package tr.com.bilkent.netflix_testing.page_object;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tr.com.bilkent.netflix_testing.WrongPageException;

public class SignInPage {
	private static final By EMAIL_BY = By.name("email");
	private static final By PASSWORD_BY = By.name("password");
	private static final By REMEMBER_BY = By.name("remember");
	private static final By SIGNIN_BUTTON_BY = By.xpath("//button[contains(string(), 'Sign In')]");

	private WebDriver driver;

	public SignInPage(WebDriver driver) {
		this.driver = driver;

		PageName currentPage = PageUtil.getPageName(driver.getCurrentUrl());
		if (currentPage != PageName.SIGNIN) {
			throw new WrongPageException("Not in the sign in page");
		}
	}

	public LandingPage loginUser(String email, String password) {
		enterEmail(email);
		enterPassword(password);
		clickSignIn();
		return waitAndReturnLandingPage();
	}

	public void enterEmail(String email) {
		WebElement emailField = driver.findElement(EMAIL_BY);
		clearField(emailField);
		emailField.sendKeys(email);
	}

	public void enterPassword(String password) {
		WebElement passwordField = driver.findElement(PASSWORD_BY);
		clearField(passwordField);
		passwordField.sendKeys(password);
	}

	public void setRememberOption(boolean value) {
		WebElement checkbox = driver.findElement(REMEMBER_BY);
		if (checkbox.isSelected() != value) {
			checkbox.click();
		}
	}

	public String getEmailFieldValue() {
		WebElement emailField = driver.findElement(EMAIL_BY);
		return emailField.getAttribute("value");
	}

	private static void clearField(WebElement field) {
		while (field.getAttribute("value").length() != 0) {
			field.sendKeys(Keys.BACK_SPACE);
			field.sendKeys(Keys.DELETE);
		}
	}

	public void clickSignIn() {
		driver.findElement(SIGNIN_BUTTON_BY).click();
	}

	public LandingPage waitAndReturnLandingPage() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.or(PageUtil.waitUntilPage(PageName.LANDING), SignInPage::isErrorMessageShown,
				ExpectedConditions.presenceOfElementLocated(By.cssSelector(".Mui-error")),
				ExpectedConditions.alertIsPresent()));

		return new LandingPage(driver);
	}

	private static boolean isErrorMessageShown(WebDriver driver) {
		WebElement form = driver.findElement(By.tagName("form"));
		List<WebElement> divs = form.findElements(By.tagName("div"));
		return divs.stream().anyMatch(div -> "rgba(232, 124, 3, 1)".equals(div.getCssValue("background-color")));
	}
}