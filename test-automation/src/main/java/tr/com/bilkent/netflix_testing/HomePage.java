package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    protected WebDriver driver;

    // <h1>Hello userName</h1>
    private By messageBy = By.cssSelector(".MuiTypography-root");
    private By signOut = By.xpath("//button[contains(string(), 'Sign out')]");
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Get message
     *
     * @return String message text
     */
    public String getMessageText() {
        return driver.findElement(messageBy).getText();
    }

    public SignInPage signOut()
    {
        WebElement button = driver.findElement(signOut);
        boolean flag = button.isEnabled() && button.isDisplayed();
        if (flag)
        {
            button.click();
            return new SignInPage(driver);
        }
        return null;
    }

    public By getMessageBy() {
        return messageBy;
    }

    public By getSignOut() {
        return signOut;
    }
}
