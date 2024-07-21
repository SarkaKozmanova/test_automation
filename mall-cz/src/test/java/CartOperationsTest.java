import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;


public class CartOperationsTest extends BaseTest {

    WebDriver browser = WebDriverManager.firefoxdriver().create();
    Cart cartPage;
    Home homePage;
    CrossCart crossCartPage;
    LeftMenu leftMenu;


    @BeforeEach
    void beforeTest() {
        cartPage = new Cart(browser);
        homePage = new Home(browser);
        crossCartPage = new CrossCart(browser);
        leftMenu = new LeftMenu(browser);

    }


    @Test
    void cartOperations() {

        cartPage.open();
        cartPage.goBack();
        cartPage.open();
        //cartPage.deleteCartItem(0);
    }
}
