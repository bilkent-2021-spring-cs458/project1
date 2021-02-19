package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import tr.com.bilkent.netflix_testing.page_object.LandingPage;
import tr.com.bilkent.netflix_testing.page_object.SignInPage;

import java.util.concurrent.TimeUnit;

public class TestSetFive {
	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://3.140.185.156");

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[contains(string(), 'Sign In')]")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		driver.findElement(By.name("remember")).click();
		LandingPage homePage = Utils.testLogin(driver, "test@t.t", "test");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		SignInPage signInPage = homePage.signOut();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//button[contains(string(), 'Sign In')]")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		boolean flag = false;
		if (signInPage != null) {
			flag = !driver.findElement(signInPage.getPasswordBy()).getText().equals("")
					|| !driver.findElement(signInPage.getMailBy()).getText().equals("");
		}

		System.out.println("Remember me button preserves data: " + flag);

		driver.quit();
	}
}
