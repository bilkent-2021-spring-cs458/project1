package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Runner {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

		System.out.println("Test Set 1 success rate: " + new TestSetOne(driver).run());
		System.out.println("Test Set 2 success rate: " + new TestSetTwo(driver).run());
//		System.out.println("Test Set 3 success rate: " + new TestSetThree().run(driver));
//		System.out.println("Test Set 4 success rate: " + new TestSetFour().run(driver));
//		System.out.println("Test Set 5 success rate: " + new TestSetFive().run(driver));

		driver.close();
	}
}
