import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MallPage {

    WebDriver browser;
    WebDriverWait wait;

    MallPage (WebDriver browser) {
        this.browser = browser;
        wait = new WebDriverWait(browser, Duration.ofMillis(3000));
    }

    void mainLogo() {
        browser.findElement(By.cssSelector(".header__big-logo")).click();
    }

    void clickOnCart() {
        browser.findElement(By.cssSelector(".cart__link > div:nth-child(1)")).click();
    }

    void logIn() {
        browser.findElement(By.cssSelector(".desktop-icons__item--user")).click();
    }

    void searchField() {
        browser.findElement(By.cssSelector("#site-search")).click();
    }

    void signUp() {
        wait.until(s -> browser.findElement(By.cssSelector(".user-unsigned__registration")).isDisplayed());
        browser.findElement(By.cssSelector(".user-unsigned__registration")).click();
    }

    void searchFieldSpecialPhrase() {
        browser.findElement(By.cssSelector(".site-search-special-phrase")).click();
    }

    void headerMenuSections(int index) {
        browser.findElements(By.cssSelector(".list-item__link__text")).get(index).click();
    }

    void registrationButton(){
        wait.until(s -> browser.findElement(By.cssSelector(".reg-btn .btn-inset")).isDisplayed());
        browser.findElement(By.cssSelector(".reg-btn .btn-inset")).click();
    }

}
