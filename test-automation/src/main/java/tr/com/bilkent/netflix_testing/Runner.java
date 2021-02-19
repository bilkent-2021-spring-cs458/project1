package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Runner {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

		System.out.println("Test Set 1: " + new TestSetOne(driver).run());
		System.out.println("Test Set 2: " + new TestSetTwo(driver).run());
		System.out.println("Test Set 3: " + new TestSetThree(driver).run());
		System.out.println("Test Set 4: " + new TestSetFour(driver).run());
//		System.out.println("Test Set 5: " + new TestSetFive().run(driver));

		driver.close();
	}
}
