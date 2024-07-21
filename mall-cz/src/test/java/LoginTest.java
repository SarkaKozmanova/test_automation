import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;

public class LoginTest extends BaseTest {

    Home homePage;
    RegistrationForm registrationFormPage;


    @BeforeEach
    void beforeTest() {
        //browser.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        homePage = new Home(browser);
        registrationFormPage = new RegistrationForm(browser);

    }


    @Test
    void navigateToRegistrationFormTest() {

        //click on log in
        homePage.logIn();
        //click on sign up
        homePage.signUp();
        //check the registration form page
        Assertions.assertEquals("Registrace | MALL.CZ", browser.getTitle());
    }

    @Test
    void wrongEmailRegistrationTest() {

        //click on log in
        homePage.logIn();
        //click on sign up
        homePage.signUp();
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
