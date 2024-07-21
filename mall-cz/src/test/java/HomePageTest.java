import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;


public class HomePageTest extends BaseTest{
    ProductSelection productSelectionPage;
    Product productPage;
    Cart cartPage;
    Home homePage;
    CrossCart crossCartPage;
    ActionsOfDay actionsOfDayPage;
    CategoryDetails categoryDetailsPage;
    RegistrationForm registrationFormPage;
    LeftMenu leftMenu;


    @BeforeEach
    void beforeTest() {
        //browser.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

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
        productPage.showProductDescription(); //advert error
        //click on buy button
        productPage.addToCart();
        //click on cart
        crossCartPage.goToCart(); //advert error
        //set var for actual price - 1 piece
        var price = cartPage.getPrice(0);
        int priceNum = Integer.parseInt(price.replaceAll("[^\\d.]", ""));
        //add 1 piece
        cartPage.addPiece();
        waitFor(3);
        //set var for actual price - 2 pieces
        var cartPrice = cartPage.getPrice(0);
        int cartPriceNum = Integer.parseInt(cartPrice.replaceAll("[^\\d.]", ""));
        //check if actualPriceTwoPieces is the same as expectedPriceTwoPieces
        Assertions.assertEquals(priceNum * 2, cartPriceNum);


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
