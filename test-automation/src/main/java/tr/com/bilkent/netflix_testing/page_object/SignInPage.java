package tr.com.bilkent.netflix_testing.page_object;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
	private static final By MAIL_BY = By.name("email");
	private static final By PASSWORD_BY = By.name("password");
	private static final By SIGNIN_BUTTON_BY = By.xpath("//button[contains(string(), 'Sign In')]");

	private WebDriver driver;

	public SignInPage(WebDriver driver) {
		this.driver = driver;

		PageName currentPage = PageUtil.getPageName(driver.getCurrentUrl());
		if (currentPage != PageName.SIGNIN) {
			throw new IllegalStateException("Not in the sign in page");
		}
	}

	public LandingPage loginUser(String username, String password) {
		driver.findElement(MAIL_BY).sendKeys(username);
		driver.findElement(PASSWORD_BY).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON_BY).click();

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.or(PageUtil.waitUntilPage(PageName.LANDING), waitUntilError(),
				ExpectedConditions.presenceOfElementLocated(By.cssSelector(".Mui-error"))));

		return new LandingPage(driver);
	}

	public void clearInputFields() {
		driver.findElement(MAIL_BY).clear();
		driver.findElement(PASSWORD_BY).clear();
	}

	private static boolean isErrorMessageShown(WebDriver driver) {
		WebElement form = driver.findElement(By.tagName("form"));
		List<WebElement> divs = form.findElements(By.tagName("div"));
		return divs.stream().anyMatch(div -> "rgba(232, 124, 3, 1)".equals(div.getCssValue("background-color")));
	}

	private static ExpectedCondition<Boolean> waitUntilError() {
		return SignInPage::isErrorMessageShown;
	}
}