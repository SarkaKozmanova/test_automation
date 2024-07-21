package dynamicWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class DynamicTest {
    WebDriver browser = WebDriverManager.firefoxdriver().create();
    DynamicPage dynamicPage;


    void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void beforeTest() {
        //browser.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        dynamicPage = new DynamicPage(browser);
        browser.manage().window().fullscreen();
        dynamicPage.open();
    }

    @Test
    void addOneBox() {
        //seleniumDevPage.open();
        //waitFor(2);
        dynamicPage.addBox();
        //waitFor(2);
        var boxDisplayed = dynamicPage.isBoxDisplayed();
        Assertions.assertTrue(boxDisplayed);

    }

    @Test
    void revealNewInput() {
        //seleniumDevPage.open();
        //waitFor(1);
        dynamicPage.revealNewInput();
        //waitFor(1);
        var textFieldDisplayed = dynamicPage.isFieldDisplayed();
        Assertions.assertTrue(textFieldDisplayed);
    }
}
