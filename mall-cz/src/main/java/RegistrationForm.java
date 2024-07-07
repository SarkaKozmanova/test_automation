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


    /*
    void insertFirstName(String name){
        Objects.requireNonNull(name);

        var firstNameField = elementFinder.findElement(By.id("registration-firstname")).sendKeys(name);
    }


        void insertName(String name) {
        Objects.requireNonNull(name);

        var nameInputBox = elementFinder.findByCssSelector("#name");
        nameInputBox.sendKeys(name);
    }
     */

}
