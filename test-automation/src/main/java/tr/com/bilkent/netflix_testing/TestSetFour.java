package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import tr.com.bilkent.netflix_testing.page_object.LandingPage;

import java.util.concurrent.TimeUnit;

public class TestSetFour {

	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://3.140.185.156");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[contains(string(), 'Sign In')]")).click();

		LandingPage homePage = Utils.testLogin(driver, "sql@t.t", "\" or \"\"=\"");

		String result = homePage.getMessageText();
		System.out.println(result);

		// driver.quit();
	}
}
