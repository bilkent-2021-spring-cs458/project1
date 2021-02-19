package tr.com.bilkent.netflix_testing;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import tr.com.bilkent.netflix_testing.page_object.SignInPage;

public class TestSetThree {
    public static void main( String[] args )
    {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://3.140.185.156");
        Actions action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//button[contains(string(), 'Sign In')]")).click();

        SignInPage page = new SignInPage(driver);
        WebElement email = driver.findElement(page.getMailBy());
        WebElement password = driver.findElement(page.getPasswordBy());

        //Type the valid password in email field to copy from
        email.sendKeys("test");
        action.click(email);
        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        action.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        email.clear();
        email.sendKeys("test@t.t");

        action.click(password).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
        driver.findElement(By.xpath("//button[contains(string(), 'Sign In')]")).click();

        driver.quit();


    }
}
