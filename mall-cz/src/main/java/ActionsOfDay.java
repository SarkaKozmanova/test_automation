import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ActionsOfDay {
    WebDriver browser;

    public ActionsOfDay(WebDriver browser) {
        this.browser = browser;
    }

    void selectOfferOfTheDay(int index) {
        browser.findElements(By.cssSelector(".bb-banners__slide")).get(index).click();
    }

}
