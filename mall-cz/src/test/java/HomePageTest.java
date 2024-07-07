import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.webdriver.WebDriverBrowser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    }


    @Test
    void homePageTest() {
        Assertions.assertEquals("MALL.CZ – bílé zboží, elektronika, PC, outdoor, hobby, hračky, kosmetika, chovatelské potřeby", browser.getTitle());

        //click a button based on its index (0,1 or 2)
        //browser.findElements(By.cssSelector(".legal-consent__button--gray")).get(2).click();
    }


    @Test
    void hairDryerTest() {

        //open menu with categories
        homePage.openItemsOfCategory(0);
        //click on hairdryers
        browser.findElement(By.xpath("//a[@href='/feny']")).click();
        waitFor(5);
        //select first item from popular items
        productSelectionPage.selectPopularItem(0);
        waitFor(3);

        var expectedName = productPage.getProductName();

        //add to item to cart
        productPage.addToCart();
        waitFor(3);
        //go to cart
        crossCartPage.goToCart();
        waitFor(3);

        var actualName = cartPage.getProductName(0);
        //check if actualName of item is same as expectedName of item
        Assertions.assertEquals(expectedName, actualName);

    }

    @Test
    void navigateToRegistrationFormTest() {
        
        //click on log in
        homePage.logIn();
        //click on sign up
        homePage.singUp();
        //check the registration form page
        Assertions.assertEquals("Registrace | MALL.CZ", browser.getTitle());
    }

    @Test
    void wrongEmailRegistrationTest() {

        //click on log in
        homePage.logIn();
        //click on sign up
        homePage.singUp();
        //fill registration form
        browser.findElement(By.id("registration-firstname")).sendKeys("Sarka");
        browser.findElement(By.id("registration-lastname")).sendKeys("Kozman");
        browser.findElement(By.id("recovery-email")).sendKeys("SarkaKozman");
        browser.findElement(By.id("registration-phone-wrapped")).sendKeys("725425750");
        //click on reg button
        registrationFormPage.registrationButton();
        //Check that a good error message is displayed and that the registration has not been completed successfully.
        var wrongEmailNotification = registrationFormPage.getWrongEmailNotification();
        Assertions.assertEquals("E-mail není zadán ve správném tvaru.", wrongEmailNotification);

    }


    @Test
    void actionOfTheDayTest() {

        //click on action of the day
        homePage.actionOfTheDayButton();
        //click on section absolutely the most
        actionsOfDayPage.selectOfferOfTheDay(0);
        //click on section fridges
        categoryDetailsPage.selectActionOfTheDaySubcategories(0);
        //click on unique item
        productSelectionPage.selectItem(0);
        //click on buy button
        productPage.addToCart();
        //go to cart
        crossCartPage.goToCart();
        //delete items
        cartPage.deleteCartItem(0);
        //check the cart is empty
        var noItemNotification = cartPage.getCartNotification();
        Assertions.assertEquals("Momentálně nemáte v košíku vloženo žádné zboží.", noItemNotification);


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
        //click on back button
        crossCartPage.goBack();
        //refresh the page
        browser.navigate().refresh();
        //click on cart
        //homePage.goToCart();


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

    @Test
    void boatTest() {

        //click on section sports and outdoor
        homePage.openItemsOfCategory(8);
        // Click on section sports
        categoryDetailsPage.selectCategory(0);
        // Click on fishing equipment
        categoryDetailsPage.selectSubcategory(40);
        // Click on section fishing boats
        categoryDetailsPage.selectSubcategory(5);
        // Click on section fishing boats
        categoryDetailsPage.selectSubcategory(4);
        // Click on 1. item from section bestsellers
        productSelectionPage.selectPopularItem(0);

        var expectedName = productPage.getProductName();

        // Click on buy button
        productPage.addToCart();
        // Click on go to cart
        crossCartPage.goToCart();

        var actualName = cartPage.getProductName(0);

        Assertions.assertEquals(expectedName, actualName);

    }


    @Test
    void cartOperations() {

        cartPage.open();
        cartPage.goBack();
        cartPage.open();
        //cartPage.deleteCartItem(0);
    }


}
