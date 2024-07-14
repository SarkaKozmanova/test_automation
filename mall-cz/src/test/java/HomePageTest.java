import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.webdriver.WebDriverBrowser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

public class HomePageTest {
    WebDriver browser = WebDriverManager.firefoxdriver().create();
    ProductSelection productSelectionPage;
    Product productPage;
    Cart cartPage;
    Home homePage;
    CrossCart crossCartPage;
    ActionsOfDay actionsOfDayPage;
    CategoryDetails categoryDetailsPage;
    RegistrationForm registrationFormPage;
    LeftMenu leftMenu;

    void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void beforeTest() {
        browser.get("https://mall.cz");
        browser.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        browser.manage().window().fullscreen();
        //accept cookies
        WebElement cookiesAcceptButton = browser.findElement(By.cssSelector(".legal-consent__button--gray"));
        cookiesAcceptButton.click();

        productSelectionPage = new ProductSelection(browser);
        productPage = new Product(browser);
        cartPage = new Cart(browser);
        homePage = new Home(browser);
        crossCartPage = new CrossCart(browser);
        categoryDetailsPage = new CategoryDetails(browser);
        actionsOfDayPage = new ActionsOfDay(browser);
        registrationFormPage = new RegistrationForm(browser);
        leftMenu = new LeftMenu(browser);

    }


    @Test
    void homePageTest() {
        Assertions.assertEquals("MALL.CZ – bílé zboží, elektronika, PC, outdoor, hobby, hračky, kosmetika, chovatelské potřeby", browser.getTitle());

        //click a button based on its index (0,1 or 2)
        //browser.findElements(By.cssSelector(".legal-consent__button--gray")).get(2).click();
    }




    @Test
    void searchFieldTest() {

        //click into search field
        homePage.searchField();
        //click on tips for gifts
        homePage.searchFieldSpecialPhrase();
        //click on section experiences for everyone
        categoryDetailsPage.selectUnpackedSubcategories(6);
        //click on show more button
        productSelectionPage.showMorePopularItems();
        //click on 4. item
        productSelectionPage.selectPopularItem(3);
        //click on whole product description
        productPage.showProductDescription();
        //click on buy button
        productPage.addToCart();
        //click on cart
        homePage.goToCart();


        //blocked by MALL, i can´t continue and check other steps

        //add more pieces
        //browser.findElements(By.cssSelector(".article-counter__btn--plus")).get(1).click();
        //click on delete this shipment
        //browser.findElement(By.cssSelector(".box-alert__body .delete-link .delete-text")).click();
        //click on back button in browser
        //browser.navigate().back();
        //check the cart is empty
        //var actualNot_NoItems = browser.findElement(By.cssSelector(".msg--indent-medium")).getText();
        //Assertions.assertEquals("Momentálně nemáte v košíku vloženo žádné zboží.", actualNot_NoItems);

    }

    @Test
    void clickBackTest() {

        //click on action of the day
        homePage.actionOfTheDayButton();
        //click on back button in browser
        browser.navigate().back();
        //check main page
        Assertions.assertEquals("MALL.CZ – bílé zboží, elektronika, PC, outdoor, hobby, hračky, kosmetika, chovatelské potřeby", browser.getTitle());
    }

    @Test
    void headerMenuTest() {

        //click on section transport price
        homePage.headerMenuSections(0);
        //click on section everything about buying
        homePage.headerMenuSections(1);
        //click on section for companies
        homePage.headerMenuSections(2);
        //click on section dispensing points
        homePage.headerMenuSections(3);
        //click on back
        browser.navigate().back();
        //click on section contact
        homePage.headerMenuSections(4);
        //click on back
        browser.navigate().back();
        //click on categories from A to Z
        homePage.headerMenuSections(5);
        //click on main logo
        browser.navigate().to("https://www.mall.cz/");

        //check main page
        Assertions.assertEquals("MALL.CZ – bílé zboží, elektronika, PC, outdoor, hobby, hračky, kosmetika, chovatelské potřeby", browser.getTitle());

    }


}
