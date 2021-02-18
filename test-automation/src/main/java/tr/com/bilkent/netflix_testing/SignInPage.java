package tr.com.bilkent.netflix_testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {
    protected WebDriver driver;

    private By mailBy = By.name("email");
    private By passwordBy = By.name("password");
    private By signinBy = By.xpath("//button[contains(string(), 'Sign In')]");
    private By rememberMeBy = By.name("remember");

    public SignInPage(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Login
     *
     * @param userName
     * @param password
     * @return HomePage object
     */
    public HomePage loginUser(String userName, String password) {
        driver.findElement(mailBy).sendKeys(userName);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(signinBy).click();
        return new HomePage(driver);
    }

    public void clearInput()
    {
        driver.findElement(mailBy).clear();
        driver.findElement(passwordBy).clear();
    }

    public By getMailBy() {
        return mailBy;
    }

    public By getPasswordBy() {
        return passwordBy;
    }

}