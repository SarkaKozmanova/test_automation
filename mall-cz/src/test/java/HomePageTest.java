import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.webdriver.WebDriverBrowser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageTest {


    @Test
    void homePageTest() {
        WebDriver browser = WebDriverManager.firefoxdriver().create();
        browser.get("https://mall.cz");

        //accept cookies
        WebElement cookiesAcceptButton = browser.findElement(By.cssSelector(".legal-consent__button--gray"));
        cookiesAcceptButton.click();

        Assertions.assertEquals("MALL.CZ – bílé zboží, elektronika, PC, outdoor, hobby, hračky, kosmetika, chovatelské potřeby", browser.getTitle());

        //click a button based on its index (0,1 or 2)
        //browser.findElements(By.cssSelector(".legal-consent__button--gray")).get(2).click();
    }


    @Test
    void hairDryerTest() {
        WebDriver browser = WebDriverManager.firefoxdriver().create();
        browser.get("https://mall.cz");

        //accept cookies
        WebElement cookiesAcceptButton = browser.findElement(By.cssSelector(".legal-consent__button--gray"));
        cookiesAcceptButton.click();

        browser.findElement(By.cssSelector(".desktop-menu__item-title")).click();


        // Click on hairdryers
        browser.findElement(By.xpath("//a[@href='/feny']")).click();
        browser.findElement(By.cssSelector(".bs__name")).click();

        var expectedName = browser.findElement(By.cssSelector(".detail__title--desktop")).getText();

        browser.findElement(By.cssSelector(".info-box__main-btn .add-to-cart-list")).click();
        browser.findElement(By.cssSelector(".cross-sell__button__to-cart__to")).click();

        var actualName = browser.findElement(By.cssSelector(".cart-overview-item-title")).getText();

        Assertions.assertEquals(expectedName, actualName);

    }

    @Test
    void navigateToRegistrationFormTest() {
        WebDriver browser = WebDriverManager.firefoxdriver().create();
        browser.get("https://mall.cz");

        //accept cookies
        WebElement cookiesAcceptButton = browser.findElement(By.cssSelector(".legal-consent__button--gray"));
        cookiesAcceptButton.click();

        //click on Přihlásit
        browser.findElement(By.cssSelector(".drop-down .desktop-icon")).click();
        //click on Zaregistrovat se
        browser.findElement(By.cssSelector(".user-unsigned__button-link")).click();
        //check the registration form page
        Assertions.assertEquals("Registrace | MALL.CZ", browser.getTitle());
    }

    @Test
    void registrationNegativeTest() {
        WebDriver browser = WebDriverManager.firefoxdriver().create();
        browser.get("https://mall.cz");
        //accept cookies
        WebElement cookiesAcceptButton = browser.findElement(By.cssSelector(".legal-consent__button--gray"));
        cookiesAcceptButton.click();

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
        WebDriver browser = WebDriverManager.firefoxdriver().create();
        browser.get("https://mall.cz");

        //accept cookies
        WebElement cookiesAcceptButton = browser.findElement(By.cssSelector(".legal-consent__button--gray"));
        cookiesAcceptButton.click();

        //click on Akce dne
        browser.findElement(By.cssSelector(".corner-wrapper__img")).click();
        //click on uplnenejvic
        browser.findElements(By.xpath("//a[@href='/uplnenejvic']//img")).get(1).click();
        //click on Lednice
        browser.findElement(By.xpath("//a[@href='/listy/cooling-chladnicky']")).click();
        //click on Koupit
        browser.findElement(By.cssSelector(".add-to-cart-wrapper")).click();
        //go to cart
        browser.findElement(By.cssSelector(".cross-sell__button")).click();
        //delete items
        browser.findElement(By.cssSelector(".cart-overview-item-row__delete .cart__remove-icon")).click();
        //ověřit, že je košík prázdný
        var actualNot_NoItems = browser.findElement(By.cssSelector(".msg--indent-medium")).getText();
        Assertions.assertEquals("Momentálně nemáte v košíku vloženo žádné zboží.", actualNot_NoItems);


    }


    @Test
    void TipyNaDarkyTest() {
        WebDriver browser = WebDriverManager.firefoxdriver().create();
        browser.get("https://mall.cz");

        WebElement cookiesAcceptButton = browser.findElement(By.cssSelector(".legal-consent__button--gray"));
        cookiesAcceptButton.click();
        //click into search field
        browser.findElement(By.cssSelector("#site-search")).click();
        //click on Tipy na dárky
        browser.findElement(By.cssSelector(".site-search-special-phrase")).click();
        //click on Zážitky pro všechny
        browser.findElements(By.cssSelector(".category-menu-item__wrapper")).get(6).click();
        //click on zobrazit více
        browser.findElement(By.cssSelector(".bs__show-more-link")).click();
        //click on 4. item
        browser.findElements(By.cssSelector(".bs__link")).get(3).click();
        //click on celý popis
        browser.findElement(By.cssSelector(".product-short-description__full-desc")).click();
        //click on koupit
        browser.findElement(By.cssSelector(".rounded-button__wrapper__slot")).click();
        //click on zpět
        browser.findElement(By.cssSelector(".cross-sell .rounded-button__wrapper")).click();
        //click on košík
        browser.findElement(By.xpath("//a[@href='/kosik']")).click();

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
        WebDriver browser = WebDriverManager.firefoxdriver().create();
        browser.get("https://mall.cz");
        WebElement cookiesAcceptButton = browser.findElement(By.cssSelector(".legal-consent__button--gray"));
        cookiesAcceptButton.click();

        //click on Akce dne
        browser.findElement(By.cssSelector(".corner-wrapper__img")).click();
        //click on zpět v prohlížeči
        browser.navigate().back();
        //ověřit, že jsem o krok zpět na úvodní stránce
        Assertions.assertEquals("MALL.CZ – bílé zboží, elektronika, PC, outdoor, hobby, hračky, kosmetika, chovatelské potřeby", browser.getTitle());
    }

    @Test
    void nextClickableTest() {
        WebDriver browser = WebDriverManager.firefoxdriver().create();
        browser.get("https://mall.cz");
        WebElement cookiesAcceptButton = browser.findElement(By.cssSelector(".legal-consent__button--gray"));
        cookiesAcceptButton.click();
        //click on Ceny dopravy
        browser.findElements(By.cssSelector(".list-item__link__text")).get(0).click();
        //go back
        browser.navigate().back();
        //click on Vše o nákupu
        browser.findElements(By.cssSelector(".list-item__link__text")).get(1).click();
        //go back
        browser.navigate().back();
        //click on Pro firmy
        browser.findElements(By.cssSelector(".list-item__link__text")).get(2).click();
        //go back
        browser.navigate().back();
        //click on Výdejní místa
        browser.findElements(By.cssSelector(".list-item__link__text")).get(3).click();
        //go back
        browser.navigate().back();
        //click on Kontakt
        browser.findElements(By.cssSelector(".list-item__link__text")).get(4).click();
        //go back
        browser.navigate().back();
        //click on Kategorie od A do Z
        browser.findElements(By.cssSelector(".list-item__link__text")).get(5).click();
        //go back
        browser.navigate().back();
        //click on Objevovat
        browser.findElement(By.cssSelector(".anp__button-content")).click();
        //click on nesouhlasím ??
        //close window
        browser.close();
        //ověřit, že jsem na hlavní stránce mall
        Assertions.assertEquals("MALL.CZ – bílé zboží, elektronika, PC, outdoor, hobby, hračky, kosmetika, chovatelské potřeby", browser.getTitle());

    }

    @Test
    void boatTest() {
        WebDriver browser = WebDriverManager.firefoxdriver().create();
        browser.get("https://mall.cz");

        //accept cookies
        WebElement cookiesAcceptButton = browser.findElement(By.cssSelector(".legal-consent__button--gray"));
        cookiesAcceptButton.click();

        //click on Sport a outdoor
        browser.findElements(By.cssSelector(".desktop-menu__item-title")).get(8).click();
        // Click on Sporty
        browser.findElement(By.xpath("//a[@href='/sportovni-aktivity']")).click();
        // Click on Rybářské potřeby
        browser.findElement(By.xpath("//a[@href='/rybarske-potreby']")).click();
        // Click on Rybářské lodě
        browser.findElement(By.xpath("//a[@href='/rybarske-lode']")).click();
        // Click on Rybářské čluny
        browser.findElements(By.cssSelector(".crossroad-tiles__title")).get(3).click();
        // Click on 1. Nejprodávanější
        browser.findElement(By.cssSelector(".bs__name")).click();

        var expectedName = browser.findElement(By.cssSelector(".detail__title--desktop")).getText();

        // Click on Koupit
        browser.findElement(By.cssSelector(".info-box__buy-buttons__button-wrapper")).click();
        // Click on Přejít do košíku
        browser.findElement(By.cssSelector(".cross-sell__button__to-cart__to")).click();

        var actualName = browser.findElement(By.cssSelector(".cart-overview-item-title")).getText();

        Assertions.assertEquals(expectedName, actualName);

    }
}
