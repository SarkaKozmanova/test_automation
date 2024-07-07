import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryDetails {
    WebDriver browser;

    public CategoryDetails(WebDriver browser) {
        this.browser = browser;
    }


    //use for catogories sport/household/fashion

    void selectCategory(int index) {
        browser.findElements(By.cssSelector(".category-tile-item__wrapper")).get(index).click();
    }

    //use for all categories

    void selectSubcategory(int index) {
        browser.findElements(By.cssSelector(".navigation-menu__link")).get(index).click();
    }

    //use for category unpacked, tips for gifts

    void selectUnpackedSubcategories(int index) {
        browser.findElements(By.cssSelector(".category-menu-item__title")).get(index).click();
    }

    //use for actionOfTheDayTest

    void selectActionOfTheDaySubcategories(int index) {
        browser.findElements(By.cssSelector(".campaign__category-box")).get(index).click();
    }


}