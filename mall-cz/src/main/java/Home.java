import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home extends MallPage{


    Home(WebDriver browser) {
        super(browser);
    }

    void openSubcategories(int index) {
        browser.findElements(By.cssSelector(".desktop-menu__item-title")).get(index).click();
    }

    void actionOfTheDayButton() {
        browser.findElement(By.cssSelector(".corner-wrapper__img")).click();
    }


}
