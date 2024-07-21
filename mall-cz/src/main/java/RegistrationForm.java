import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationForm extends MallPage{


    RegistrationForm(WebDriver browser) {
        super(browser);
    }

    String getWrongEmailNotification() {
        wait.until(s -> browser.findElement(By.id("flashmessages")).isDisplayed());
        return browser.findElement(By.id("flashmessages")).getText();
    }

    void insertFirstName(String name){
        browser.findElement(By.id("registration-firstname")).sendKeys(name);
    }

    void insertLastName(String name){
        browser.findElement(By.id("registration-lastname")).sendKeys(name);
    }

    void insertEmail(String email){
        browser.findElement(By.id("recovery-email")).sendKeys(email);
    }

    void insertMobileNumber(String mobile){
        browser.findElement(By.id("registration-phone-wrapped")).sendKeys(mobile);
    }
}
