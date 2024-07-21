import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Product extends MallPage{

    Product(WebDriver browser) {
        super(browser);
    }

    void addToCart() {
        wait.until(s -> browser.findElement(By.cssSelector(".info-box__main-btn .add-to-cart-list")).isDisplayed());
        browser.findElement(By.cssSelector(".info-box__main-btn .add-to-cart-list")).click();
    }

    String getProductName() {
        wait.until(s -> browser.findElement(By.cssSelector(".detail__title--desktop")).isDisplayed());
        return browser.findElement(By.cssSelector(".detail__title--desktop")).getText();
    }

    void showProductDescription(){
        wait.until(s -> browser.findElement(By.cssSelector(".product-short-description__full-desc .icon")).isDisplayed());
        browser.findElement(By.cssSelector(".product-short-description__full-desc .icon")).click();
    }



}