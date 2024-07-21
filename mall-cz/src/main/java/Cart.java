import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cart extends MallPage{


    Cart(WebDriver browser) {
        super(browser);
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
        wait.until(s -> browser.findElement(By.cssSelector(".msg--indent-medium")).isDisplayed());
        return browser.findElement(By.cssSelector(".msg--indent-medium")).getText();
    }

    String getProductName(int index) {
        wait.until(s -> browser.findElement(By.cssSelector(".cart-layout__group .cart-overview-item-title a")).isDisplayed());
        return browser.findElements(By.cssSelector(".cart-layout__group .cart-overview-item-title a")).get(index).getText();
    }

    String getPrice(int index) {
        wait.until(s -> browser.findElement(By.cssSelector(".cart-overview-item-price .bold")).isDisplayed());
        return browser.findElements(By.cssSelector(".cart-overview-item-price .bold")).get(index).getText();
    }

    void addPiece() {
        browser.findElement(By.cssSelector(".box-alert__body .article-counter__btn--plus span")).click();
    }
}
