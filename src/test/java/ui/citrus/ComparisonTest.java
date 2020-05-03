package ui.citrus;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.citrus.BasePage;
import steps.ComparisonPageSteps;
import steps.HomePageSteps;
import steps.ProductListPageSteps;
import steps.ProductPageSteps;

import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.Selenide.open;

public class ComparisonTest {

    HomePageSteps homePageSteps;
    ProductListPageSteps productListPageSteps;
    ProductPageSteps productPageSteps;
    ComparisonPageSteps comparisonPageSteps;

    @BeforeClass
    public void setUp() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        Configuration.timeout = 5000;
        open("");

        homePageSteps = new HomePageSteps();
        productListPageSteps = new ProductListPageSteps();
        productPageSteps = new ProductPageSteps();
        comparisonPageSteps = new ComparisonPageSteps();
    }

    @BeforeMethod
    public void clearCart() {
        clearBrowserLocalStorage();
        open("");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            BasePage.screenshot();
        }
    }

    @Test(description = "Comparison two product and adding third product")
    public void comparisonTwoPlusOneProduct(){
        homePageSteps.clickOnLinkInMenu("Ноутбуки", "Acer");
        productListPageSteps.addProductToCartByPositionFromMenu(1);
        String firstProductPrice = productListPageSteps.rememberFirstProductPriceByPositionFromMenu(1);
        String firstProductName = productListPageSteps.rememberFirstProductNameByPositionFromMenu(1);

        productListPageSteps.addProductToCartByPositionFromMenu(2);
        String secondProductPrice = productListPageSteps.rememberFirstProductPriceByPositionFromMenu(1);
        String secondProductName = productListPageSteps.rememberFirstProductNameByPositionFromMenu(1);

        productListPageSteps.clickOnComparison();
        comparisonPageSteps.verifyContentInComparisonProduct(firstProductName, firstProductPrice, secondProductName, secondProductPrice);

        comparisonPageSteps.clickOnAddToCompareButton();
        String addedProductPrice = productListPageSteps.rememberAddedProductPriceFromComparison(1);
        String addedProductName = productListPageSteps.rememberAddedProductNameFromComparison(1);
        comparisonPageSteps.addThirdProductToComparison();

        comparisonPageSteps.verifyContentInComparisonProduct(firstProductName, firstProductPrice, secondProductName, secondProductPrice, addedProductName, addedProductPrice);
    }
}
