import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;

public class E2ETest extends BaseTest{

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
       // browser.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));


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
    void hairDryerTest() {

        //open menu with categories and click on hairdryers
        leftMenu.clickMenuItem("Spotřebiče", "Fény");
        //select first item from popular items
        productSelectionPage.selectPopularItem(0);

        var expectedName = productPage.getProductName();

        //add to item to cart
        productPage.addToCart();
        //go to cart
        crossCartPage.goToCart();

        var actualName = cartPage.getProductName(0);
        //check if actualName of item is same as expectedName of item
        Assertions.assertEquals(expectedName, actualName);

    }

    @Test
    void actionOfTheDayTest() {

        //click on action of the day
        homePage.actionOfTheDayButton();
        //click on section absolutely the most
        actionsOfDayPage.selectOfferOfTheDay(1);
        //click on section fridges
        categoryDetailsPage.selectActionOfTheDaySubcategories(0);
        //click on unique item
        productSelectionPage.selectItem(1);
        //click on buy button
        productPage.addToCart(); //advert error
        //go to cart
        crossCartPage.goToCart();
        //delete items
        cartPage.deleteCartItem(0);
        //check the cart is empty
        var noItemNotification = cartPage.getCartNotification();
        Assertions.assertEquals("Momentálně nemáte v košíku vloženo žádné zboží.", noItemNotification);


    }

    @Test
    void boatTest() {

        //click on category sports
        homePage.openSubcategories(8);
        // click on outdoor and sports
        categoryDetailsPage.selectCategory(0);
        // Click on fishing equipment => fishing boats => fishing boats
        leftMenu.clickSubcategories("Rybaření", "Rybářské lodě", "Rybářské čluny");
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
    void freezerTest() {

        //open menu with categories and click on freezer
        leftMenu.clickMenuItem("Spotřebiče","Mrazničky");
        //select first item from popular items
        productSelectionPage.selectPopularItem(0);

        var expectedName = productPage.getProductName();

        //add to item to cart
        productPage.addToCart();
        //go to cart
        crossCartPage.goToCart();

        var actualName = cartPage.getProductName(0);
        //check if actualName of item is same as expectedName of item
        Assertions.assertEquals(expectedName, actualName);

    }

    @Test
    void notebookTest() {

        //open menu with categories and click on notebooky
        leftMenu.clickMenuItem("Mobily, PC a kancelář", "Notebooky");
        //select first item from popular items
        productSelectionPage.selectPopularItem(1);
        //add to item to cart
        productPage.addToCart();
        //go to cart
        crossCartPage.goToCart();

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
}
