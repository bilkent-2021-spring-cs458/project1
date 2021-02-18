package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TestCaseTwo {

    public static void main( String[] args )
    {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://3.140.185.156");
        boolean flag = false;
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//button[contains(string(), 'Sign In')]")).click();

        HomePage homePage = Utils.testLogin(driver, "test@t.t", "test");

        if (homePage != null)
        {
            driver.navigate().back();
            driver.navigate().forward();
            flag = homePage.getMessageText().equals(driver.findElement(homePage.getMessageBy()).getText());
        }
        System.out.println("Back and forward buttons allow traversal to home page: " + flag);

        driver.quit();

    }
}
