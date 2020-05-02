package ui.citrus;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.HomePageSteps;
import steps.ProductListPageSteps;

import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.Selenide.open;

public class FiltersTest {

    HomePageSteps homePageSteps;
    ProductListPageSteps productListPageSteps;

    @BeforeClass
    public void setUp() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        Configuration.timeout = 5000;
        open("");

        homePageSteps = new HomePageSteps();
        productListPageSteps = new ProductListPageSteps();

    }

    @BeforeMethod
    public void clearCart() {
        clearBrowserLocalStorage();
        open("");
    }

    @Test
    public void minAndMxPriceFilter() throws Exception {
        homePageSteps.clickOnLinkInMenu("Смартфоны", "Samsung");
        productListPageSteps.setPriceFilter("5000", "15000");
        productListPageSteps.verifyPriceFilter("Samsung", 5000, 15000);
    }

    @Test
    public void memorySizeFilter() throws Exception {
        homePageSteps.clickOnLinkInMenu("Смартфоны", "Xiaomi");
        productListPageSteps.setMemorySizeFilter(16,32);
        productListPageSteps.verifyMemorySizeFilter("Xiaomi", "16", "32");
    }

    @Test
    public void bodyMaterialFilter() throws Exception {
        homePageSteps.clickOnLinkInMenu("Смартфоны", "Google");
        productListPageSteps.settBodyMaterialFilter("Металл");
        productListPageSteps.verifyBodyMaterialFilter("Google", "Металл");
    }
}
