import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class RegistrationForm {
    WebDriver browser;

    public RegistrationForm(WebDriver browser) {
        this.browser = browser;
    }


    void registrationButton(){
        browser.findElement(By.cssSelector(".reg-btn .btn-inset")).click();
    }

    String getWrongEmailNotification() {
        return browser.findElement(By.cssSelector("#flashmessages")).getText();
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
