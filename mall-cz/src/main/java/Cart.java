import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cart {

    WebDriver browser;

    public Cart(WebDriver browser) {
        this.browser = browser;
    }

    void open() {
        browser.get("https://www.mall.cz/kosik");
    }

    void goBack() {
        browser.findElement(By.cssSelector(".go_back_btn")).click();
    }

    void goCheckout() {
        browser.findElement(By.cssSelector(".cart-overview__wrapper .cart-layout__submit .btn--primary")).click();
    }

    void deleteCartItem(int index) {
        browser.findElements(By.cssSelector(".cart-overview-item-row__delete .cart__remove-icon")).get(index).click();
    }

    String getCartNotification() {
        return browser.findElement(By.cssSelector(".msg--indent-medium")).getText();
    }

    String getProductName(int index) {
        return browser.findElements(By.cssSelector(".cart-layout__group .cart-overview-item-title a")).get(index).getText();
    }


}
