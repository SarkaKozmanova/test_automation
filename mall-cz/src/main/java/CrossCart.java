import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CrossCart extends MallPage{


    CrossCart(WebDriver browser) {
        super(browser);
    }

    void goBack(){
        browser.findElement(By.cssSelector(".cross-sell__button .cross-sell__button__close")).click();
    }

    void goToCart() {
        wait.until(s -> browser.findElement(By.cssSelector(".cross-sell__button__to-cart__to")).isDisplayed());
        browser.findElement(By.cssSelector(".cross-sell__button__to-cart__to")).click();
    }

    void findOutMore() {
        browser.findElement(By.cssSelector(".smb .rounded-button__wrapper__slot")).click();
    }

    void closeAdvert() {
        browser.findElement(By.cssSelector(".smb .close-button")).click();
    }

}
