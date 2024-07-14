import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class CartOperationsTest {

    WebDriver browser = WebDriverManager.firefoxdriver().create();
    Cart cartPage;
    Home homePage;
    CrossCart crossCartPage;
    LeftMenu leftMenu;

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
        browser.manage().window().fullscreen();
        //accept cookies
        WebElement cookiesAcceptButton = browser.findElement(By.cssSelector(".legal-consent__button--gray"));
        cookiesAcceptButton.click();

        cartPage = new Cart(browser);
        homePage = new Home(browser);
        crossCartPage = new CrossCart(browser);
        leftMenu = new LeftMenu(browser);

    }



    @Test
    void cartOperations() {

        cartPage.open();
        cartPage.goBack();
        cartPage.open();
        //cartPage.deleteCartItem(0);
    }
}
