import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeftMenu extends MallPage{


    LeftMenu(WebDriver browser) {
        super(browser);
    }

    void clickMenuItem(String mainMenu, String secondaryMenu) {
        browser.findElement(By.xpath("//ul[@class='desktop-menu__list']//span[contains(text(), '" + mainMenu + "')]")).click();
        browser.findElement(By.xpath("//ul[@class='menu-container_list-item']//a[contains(text(), '" + secondaryMenu + "')]")).click();
    }

    void clickCategories(String mainMenu, String secondaryMenu) {
        browser.findElement(By.xpath("//ul[@class='desktop-menu__list']//span[contains(text(), '" + mainMenu + "')]")).click();
        browser.findElement(By.xpath("//a[@class='category-tile-item__full-width']//span[contains(text(), '" + secondaryMenu + "')]")).click();
    }

    void clickSubcategories(String mainMenu, String secondaryMenu, String tertiaryMenu) {
        browser.findElement(By.xpath("//ul[@class='navigation-menu']//a[contains(text(), '" + mainMenu + "')]")).click();
        browser.findElement(By.xpath("//ul[@class='navigation-menu']//a[contains(text(), '" + secondaryMenu + "')]")).click();
        browser.findElement(By.xpath("//ul[@class='navigation-menu']//a[contains(text(), '" + tertiaryMenu + "')]")).click();
    }

}
