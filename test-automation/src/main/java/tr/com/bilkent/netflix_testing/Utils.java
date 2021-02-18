package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public final class Utils {

    private Utils() {}

    public static HomePage testLogin(WebDriver driver, String username, String pass)
    {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.clearInput();
        /*WebElement text = new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver1 ->
                driver.findElement(homePage.getMessageBy()));
        if (text.getText().contains("You are currently signed in.")) {
            System.out.println(homePage.getMessageText());
            return homePage;
        }
        else
            return null;

         */
        return signInPage.loginUser(username, pass);
    }

}
