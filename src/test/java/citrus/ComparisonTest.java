package citrus;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.citrus.ComparisonPage;
import pages.citrus.HomePage;
import pages.citrus.ProductListPage;
import pages.citrus.ProductPage;

import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.Selenide.open;

public class ComparisonTest {

    HomePage homePage;
    ProductListPage productListPage;
    ProductPage productPage;
    ComparisonPage comparisonPage;

    @BeforeClass
    public void setUp() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        Configuration.timeout = 5000;
        open("");

        homePage = new HomePage();
        productListPage = new ProductListPage();
        productPage = new ProductPage();
        comparisonPage = new ComparisonPage();
    }

    @BeforeMethod
    public void clearCart() {
        clearBrowserLocalStorage();
        open("");
    }
}
