package dynamicWait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicPage {

    WebDriver browser;
    WebDriverWait wait;

    public DynamicPage(WebDriver browser) {
        this.browser = browser;
        wait = new WebDriverWait(browser, Duration.ofMillis(3000));
    }

    void open() {
        browser.get("https://www.selenium.dev/selenium/web/dynamic.html");
    }

    void addBox(){
        browser.findElement(By.cssSelector("#adder")).click();
    }

    void revealNewInput(){
        browser.findElement(By.cssSelector("#reveal")).click();
    }

    boolean isBoxDisplayed() {
        wait.until(s -> browser.findElement(By.id("box0")).isDisplayed());
        return browser.findElement(By.id("box0")).isDisplayed();
    }

    boolean isFieldDisplayed() {
        wait.until(s -> browser.findElement(By.id("revealed")).isDisplayed());
        return browser.findElement(By.id("revealed")).isDisplayed();
    }
}

