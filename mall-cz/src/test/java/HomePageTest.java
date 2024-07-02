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
    }


    @Test
    void homePageTest() {
        Assertions.assertEquals("MALL.CZ – bílé zboží, elektronika, PC, outdoor, hobby, hračky, kosmetika, chovatelské potřeby", browser.getTitle());

        //click a button based on its index (0,1 or 2)
        //browser.findElements(By.cssSelector(".legal-consent__button--gray")).get(2).click();
    }


    @Test
    void hairDryerTest() {

        ProductSelection productSelectionPage = new ProductSelection(browser);
        Product productPage = new Product(browser);
        Cart cartPage = new Cart(browser);
        Home homePage = new Home(browser);
        CrossCart crossCartPage = new CrossCart(browser);

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
        //click on Přihlásit
        browser.findElement(By.cssSelector(".drop-down .desktop-icon")).click();
        //click on Zaregistrovat se
        browser.findElement(By.cssSelector(".user-unsigned__button-link")).click();
        //check the registration form page
        Assertions.assertEquals("Registrace | MALL.CZ", browser.getTitle());
    }

    @Test
    void registrationNegativeTest() {
        //click on Přihlásit
        browser.findElement(By.cssSelector(".drop-down .desktop-icon")).click();
        //click on Zaregistrovat se
        //browser.findElement(By.cssSelector(".user-unsigned__button-link")).click();
        browser.findElement(By.xpath("//a[@href='/user/register/register']")).click();
        //fill registration form
        browser.findElement(By.id("registration-firstname")).sendKeys("Sarka");
        browser.findElement(By.id("registration-lastname")).sendKeys("Kozman");
        browser.findElement(By.id("recovery-email")).sendKeys("SarkaKozman");
        browser.findElement(By.id("registration-phone-wrapped")).sendKeys("725425750");
        //scroll down??
        //click on reg button
        browser.findElement(By.cssSelector(".reg-btn .btn-inset")).click();
        //ověření, že vyskočí správná chybová hláška a registrace nebude dokončena
        var actualNot = browser.findElement(By.cssSelector("#flashmessages")).getText();
        Assertions.assertEquals("E-mail není zadán ve správném tvaru.", actualNot);

    }


    @Test
    void akceDneTest() {
        Cart cartPage = new Cart(browser);
        Product productPage = new Product(browser);
        Home homePage = new Home(browser);
        CategoryDetails categoryDetailsPage = new CategoryDetails(browser);
        CrossCart crossCartPage = new CrossCart(browser);
        ActionsOfDay actionsOfDayPage = new ActionsOfDay(browser);
        ProductSelection productSelectionPage = new ProductSelection(browser);

        //click on Akce dne
        homePage.actionOfTheDayButton();
        //click on uplnenejvic
        actionsOfDayPage.selectOfferOfTheDay(0);
        //click on Lednice
        categoryDetailsPage.selectAkceDneSubcategories(0);
        //click on unique item
        productSelectionPage.selectItem(0);
        //click on Koupit
        productPage.addToCart();
        //go to cart
        crossCartPage.goToCart();
        //delete items
        cartPage.deleteCartItem(0);
        //ověřit, že je košík prázdný
        var noItemNotification = cartPage.getCartNotification();
        Assertions.assertEquals("Momentálně nemáte v košíku vloženo žádné zboží.", noItemNotification);


    }


    @Test
    void searchFieldTest() {
        Product productPage = new Product(browser);
        ProductSelection productSelectionPage = new ProductSelection(browser);
        Home homePage = new Home(browser);
        Cart cartPage = new Cart(browser);
        CrossCart crossCartPage = new CrossCart(browser);
        CategoryDetails categoryDetailsPage = new CategoryDetails(browser);

        //click into search field
        homePage.searchField();
        //click on Tipy na dárky
        homePage.searchFieldSpecialPhrase();
        //click on Zážitky pro všechny
        categoryDetailsPage.selectRozbalenoSubcategories(6);
        //click on zobrazit více
        productSelectionPage.showMorePopularItems();
        //click on 4. item
        productSelectionPage.selectPopularItem(3);
        //click on celý popis
        productPage.showProductDescription();
        //click on koupit
        productPage.addToCart();
        //click on zpět
        crossCartPage.goBack();
        //refresh the page
        browser.navigate().refresh();
        //click on košík
        //homePage.goToCart();

        //block od MALLu, nemůžu dokončit test a zkontrolovat další kroky

        //přidat počet kusů
        //browser.findElements(By.cssSelector(".article-counter__btn--plus")).get(1).click();
        //click on odstranit tuto zásilku
        //browser.findElement(By.cssSelector(".box-alert__body .delete-link .delete-text")).click();
        //click on zpět v prohlížeči
        //browser.navigate().back();
        //ověřit, že v košíku není zboží, které bylo odstraněno
        //var actualNot_NoItems = browser.findElement(By.cssSelector(".msg--indent-medium")).getText();
        //Assertions.assertEquals("Momentálně nemáte v košíku vloženo žádné zboží.", actualNot_NoItems);

    }

    @Test
    void clickBackTest() {
        Home homePage = new Home(browser);
        //click on Akce dne
        homePage.actionOfTheDayButton();
        //click on zpět v prohlížeči
        browser.navigate().back();
        //ověřit, že jsem o krok zpět na úvodní stránce
        Assertions.assertEquals("MALL.CZ – bílé zboží, elektronika, PC, outdoor, hobby, hračky, kosmetika, chovatelské potřeby", browser.getTitle());
    }

    @Test
    void headerMenuTest() {
        Home homePage = new Home(browser);
        //click on Ceny dopravy
        homePage.headerMenuSections(0);
        //click on Vše o nákupu
        homePage.headerMenuSections(1);
        //click on Pro firmy
        homePage.headerMenuSections(2);
        //click on Výdejní místa
        homePage.headerMenuSections(3);
        //click on back
        browser.navigate().back();
        //click on Kontakt
        homePage.headerMenuSections(4);
        //click on back
        browser.navigate().back();
        //click on Kategorie od A do Z
        homePage.headerMenuSections(5);
        //click on main logo
        browser.navigate().to("https://www.mall.cz/");

        //ověřit, že jsem na hlavní stránce mall
        Assertions.assertEquals("MALL.CZ – bílé zboží, elektronika, PC, outdoor, hobby, hračky, kosmetika, chovatelské potřeby", browser.getTitle());

    }

    @Test
    void boatTest() {
        Product productPage = new Product(browser);
        Home homePage = new Home(browser);
        ProductSelection productSelectionPage = new ProductSelection(browser);
        Cart cartPage = new Cart(browser);
        CategoryDetails categoryDetailsPage = new CategoryDetails(browser);
        CrossCart crossCartPage = new CrossCart(browser);

        //click on Sport a outdoor
        homePage.openItemsOfCategory(8);
        // Click on Sporty
        categoryDetailsPage.selectCategory(0);
        // Click on Rybářské potřeby
        categoryDetailsPage.selectSubcategory(40);
        // Click on Rybářské lodě
        categoryDetailsPage.selectSubcategory(5);
        // Click on Rybářské čluny
        categoryDetailsPage.selectSubcategory(4);
        // Click on 1. Nejprodávanější
        productSelectionPage.selectPopularItem(0);

        var expectedName = productPage.getProductName();

        // Click on Koupit
        productPage.addToCart();
        // Click on Přejít do košíku
        crossCartPage.goToCart();

        var actualName = cartPage.getProductName(0);

        Assertions.assertEquals(expectedName, actualName);

    }


    @Test
    void cartOperations() {
        Cart cartPage = new Cart(browser);

        cartPage.open();
        cartPage.goBack();
        cartPage.open();
        //cartPage.deleteCartItem(0);
    }


}
