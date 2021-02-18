package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TestCaseOne
{
    public static void main( String[] args )
    {

        WebDriver driver = new FirefoxDriver();
        driver.get("http://3.140.185.156");
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//button[contains(string(), 'Sign In')]")).click();
        String signInURL = driver.getCurrentUrl();
        HomePage destination;

        destination = Utils.testLogin(driver, "", "");
        destination = Utils.testLogin(driver, "invalid", "test");
        destination = Utils.testLogin(driver, "test@t.t", "invalidPass");
        destination = Utils.testLogin(driver, "invalid", "invalidPass");
        destination = Utils.testLogin(driver, "test@t.t", "test");

        if (!driver.getCurrentUrl().equals(signInURL))
        {
            System.out.println(destination.getMessageText());
            driver.quit();
        }

    }
}
