import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class LoginTest {

    WebDriver browser = WebDriverManager.firefoxdriver().create();
    Home homePage;
    RegistrationForm registrationFormPage;


    void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void beforeTest() {
        browser.get("https://mall.cz");
        browser.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        browser.manage().window().fullscreen();
        //accept cookies
        WebElement cookiesAcceptButton = browser.findElement(By.cssSelector(".legal-consent__button--gray"));
        cookiesAcceptButton.click();

        homePage = new Home(browser);
        registrationFormPage = new RegistrationForm(browser);

    }


    @Test
    void navigateToRegistrationFormTest() {

        //click on log in
        homePage.logIn();
        //click on sign up
        homePage.singUp();
        //check the registration form page
        Assertions.assertEquals("Registrace | MALL.CZ", browser.getTitle());
    }

    @Test
    void wrongEmailRegistrationTest() {

        //click on log in
        homePage.logIn();
        //click on sign up
        homePage.singUp();
        //fill registration form
        registrationFormPage.insertFirstName("Sarka");
        registrationFormPage.insertLastName("Kozmanova");
        registrationFormPage.insertEmail("SaKo");
        registrationFormPage.insertMobileNumber("725425750");
        //click on reg button
        registrationFormPage.registrationButton();
        //Check that a good error message is displayed and that the registration has not been completed successfully.
        var wrongEmailNotification = registrationFormPage.getWrongEmailNotification();
        Assertions.assertEquals("E-mail není zadán ve správném tvaru.", wrongEmailNotification);

    }
}
