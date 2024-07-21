import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseTest {

    WebDriver browser = WebDriverManager.firefoxdriver().create();

    @BeforeEach
    void commonBeforeTest() {
        browser.get("https://mall.cz");
        browser.manage().window().fullscreen();
        //accept cookies
        WebElement cookiesAcceptButton = browser.findElement(By.cssSelector(".legal-consent__button--gray"));
        cookiesAcceptButton.click();

    }

    void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
