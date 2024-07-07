import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductSelection {

    WebDriver browser;

    public ProductSelection(WebDriver browser) {
        this.browser = browser;
    }

    void showMore() {
        browser.findElement(By.cssSelector(".description-accordion__open")).click();
    }

    void showLess() {
        browser.findElement(By.cssSelector(".description-accordion__open")).click();
    }

    void selectPopularItem(int index) {
        browser.findElements(By.cssSelector(".bs__name")).get(index).click();
    }

    void showMorePopularItems() {
        browser.findElement(By.cssSelector(".bs__show-more-link")).click();
    }

    void selectItem(int index) {
        browser.findElements(By.cssSelector(".gallery-list")).get(index).click();
    }

}
