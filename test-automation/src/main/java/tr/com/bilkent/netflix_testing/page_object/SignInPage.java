package tr.com.bilkent.netflix_testing.page_object;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tr.com.bilkent.netflix_testing.WrongPageException;

/**
 * The sign in page object model (POM). This page is used for signing in the
 * user. It contains a form with 3 editable fields: email, password and
 * remeber_me option
 */
public class SignInPage {
	private static final By EMAIL_BY = By.name("email");
	private static final By PASSWORD_BY = By.name("password");
	private static final By REMEMBER_BY = By.name("remember");
	private static final By SIGNIN_BUTTON_BY = By.xpath("//button[contains(string(), 'Sign In')]");

	private WebDriver driver;

	/**
	 * Create a new Landing POM to be controlled with the given driver.
	 * 
	 * @param driver The driver that should be used for controlling this POM
	 */
	public SignInPage(WebDriver driver) {
		this.driver = driver;

		PageName currentPage = PageUtil.getPageName(driver.getCurrentUrl());
		if (currentPage != PageName.SIGNIN) {
			throw new WrongPageException("Not in the sign in page");
		}
	}

	/**
	 * Tries to log in the user with the given credentials. Returns the landing page
	 * that has been redirected after a successful login. If sign in fails, an
	 * unchecked exception is thrown.
	 * 
	 * @param email    The email of the user to log in
	 * @param password The password of the user to log in
	 * @return The resulting LandingPage after the log in
	 */
	public LandingPage loginUser(String email, String password) {
		enterEmail(email);
		enterPassword(password);
		clickSignIn();
		return waitAndReturnLandingPage();
	}

	/**
	 * Sets the value of the email field to the given email
	 * 
	 * @param email Email to set
	 */
	public void enterEmail(String email) {
		WebElement emailField = driver.findElement(EMAIL_BY);
		clearField(emailField);
		emailField.sendKeys(email);
	}

	/**
	 * Sets the value of the password field to the given password
	 * 
	 * @param password Password to set
	 */
	public void enterPassword(String password) {
		WebElement passwordField = driver.findElement(PASSWORD_BY);
		clearField(passwordField);
		passwordField.sendKeys(password);
	}

	/**
	 * Sets the remember me option to the given value
	 * 
	 * @param value The remember me option to set
	 */
	public void setRememberOption(boolean value) {
		WebElement checkbox = driver.findElement(REMEMBER_BY);
		if (checkbox.isSelected() != value) {
			checkbox.click();
		}
	}

	/**
	 * Extracts the curret value of the email field
	 * 
	 * @return the curret value of the email field
	 */
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

	/**
	 * Click the sign in button
	 */
	public void clickSignIn() {
		driver.findElement(SIGNIN_BUTTON_BY).click();
	}

	/**
	 * Waits until the page turns to the LandingPage, or some error occurs. Errors
	 * include the visible error message as a response to sign in, a field
	 * validation error, or a javascript alert being shown. In case of an error,
	 * exception is thrown.
	 * 
	 * @return The resulting Landing page
	 */
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