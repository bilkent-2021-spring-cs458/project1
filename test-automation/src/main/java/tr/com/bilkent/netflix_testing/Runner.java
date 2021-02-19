package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Runner {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		TestSetResult overall = new TestSetResult(0, 0);

		TestSet[] sets = { new TestSet1(driver), new TestSet2(driver), new TestSet3(driver), new TestSet4(driver),
				new TestSet5(driver) };

		for (int setNum = 0; setNum < sets.length; setNum++) {
			System.out.println("Test Set " + (setNum + 1) + ": " + sets[setNum].run());
			overall.add(sets[setNum].getResult());
		}
		driver.quit();

		System.out.println();
		System.out.println("Overall Test Results: \t" + overall);
	}
}
