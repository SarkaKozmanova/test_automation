import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home {

    WebDriver browser;

    public Home(WebDriver browser) {
        this.browser = browser;
    }

    void openSubcategories(int index) {
        browser.findElements(By.cssSelector(".desktop-menu__item-title")).get(index).click();
    }

    //use for categories spotřebiče/mobily/TV
    void openItemsOfCategory(int index) {
        browser.findElements(By.cssSelector(".desktop-menu__item-title")).get(index).click();
    }

    void searchField() {
        browser.findElement(By.cssSelector("#site-search")).click();
    }

    void headerMenuSections(int index) {
        browser.findElements(By.cssSelector(".list-item__link__text")).get(index).click();
    }

    void mainLogo() {
        browser.findElement(By.cssSelector(".header__big-logo")).click();
    }

    void actionOfTheDayButton() {
        browser.findElement(By.cssSelector(".corner-wrapper__img")).click();
    }

    void goToCart() {
        browser.findElement(By.cssSelector(".cart__link")).click();
    }

    void searchFieldSpecialPhrase(){
        browser.findElement(By.cssSelector(".site-search-special-phrase")).click();
    }
}
