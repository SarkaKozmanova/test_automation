import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Product {

    WebDriver browser;

    public Product(WebDriver browser) {
        this.browser = browser;
    }

    void addToCart() {
        browser.findElement(By.cssSelector(".info-box__main-btn .add-to-cart-list")).click();
    }

    String getProductName() {
        return browser.findElement(By.cssSelector(".detail__title--desktop")).getText();
    }

    void showProductDescription(){
        browser.findElement(By.cssSelector(".product-short-description__full-desc .icon")).click();
    }



}