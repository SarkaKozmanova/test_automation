import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductSelection extends MallPage {


    ProductSelection(WebDriver browser) {
        super(browser);
    }

    void showMore() {
        browser.findElement(By.cssSelector(".description-accordion__open")).click();
    }

    void showLess() {
        browser.findElement(By.cssSelector(".description-accordion__open")).click();
    }

    void selectPopularItem(int index) {
        wait.until(s -> browser.findElement(By.cssSelector(".bs__name")).isDisplayed());
        browser.findElements(By.cssSelector(".bs__name")).get(index).click();
    }

    void showMorePopularItems() {
        wait.until(s -> browser.findElement(By.cssSelector(".bs__show-more-link")).isDisplayed());
        browser.findElement(By.cssSelector(".bs__show-more-link")).click();
    }

    void selectItem(int index) {
        wait.until(s -> browser.findElement(By.cssSelector(".hooper-list")).isDisplayed());
        browser.findElements(By.cssSelector(".hooper-list")).get(index).click();
    }

}
