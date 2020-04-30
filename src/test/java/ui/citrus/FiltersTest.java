package ui.citrus;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.citrus.HomePage;
import pages.citrus.ProductListPage;

import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.Selenide.open;

public class FiltersTest {

    HomePage homePage;
    ProductListPage productListPage;

    @BeforeClass
    public void setUp() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        Configuration.timeout = 5000;
        open("");

        homePage = new HomePage();
        productListPage = new ProductListPage();

    }

    @BeforeMethod
    public void clearCart() {
        clearBrowserLocalStorage();
        open("");
    }

    @Test
    public void minAndMxPriceFilter() throws Exception {
        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLne("Смартфоны")
                .clickLinkMenu("Samsung");
        productListPage.getFilterFragment()
                .setMinPrice("5000")
                .setMaxPrice("15000");
        productListPage.getFilterFragment()
                .verifyFilterByName("Samsung");
        productListPage.getFilterFragment()
                .verifyPriceFilter(5000, 15000);
    }

    @Test
    public void memorySizeFilter() throws Exception {
        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLne("Смартфоны")
                .clickLinkMenu("Xiaomi");
        productListPage.getFilterFragment()
                .setMemorySize(16)
                .setMemorySize(32);
        productListPage.getFilterFragment()
                .verifyFilterByName("Xiaomi");
        productListPage.getFilterFragment()
                .verifyMemorySizeFilter("16", "32");
    }

    @Test
    public void bodyMaterialFilter() throws Exception {
        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLne("Смартфоны")
                .clickLinkMenu("Google");
        productListPage.getFilterFragment()
                .setBodyMaterialFilter("Металл");
        productListPage.getFilterFragment()
                .verifyFilterByName("Google");
        productListPage.getFilterFragment()
                .verifyBodyMaterialFilter("Металл");
    }
}
