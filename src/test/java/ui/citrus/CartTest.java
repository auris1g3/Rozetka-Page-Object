package ui.citrus;

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


public class CartTest {

    HomePageSteps homePageSteps;
    ProductListPageSteps productListPageSteps;
    ProductPageSteps productPageSteps;
    ComparisonPageSteps comparisonPageSteps;
    String productName = "Apple iPhone 11 64Gb Black";

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

    @Test(description = "Add product to cart from menu")
    public void addProductToCartViaMenu() {
        homePageSteps.clickOnLinkInMenu("Смартфоны", "Apple");
        productListPageSteps.clickOnProduct(productName);
        String productPrice = productPageSteps.rememberProductPrice();
        productPageSteps.addProductToBasket();
        productPageSteps.verifyContentInCart(productName, productPrice);
    }

    @Test(description = "Add product to cart from search input")
    public void addProductToCartViaSearch() {
        homePageSteps.searchProductByName("Apple iPhone 11");
        String productPrice = productListPageSteps.rememberProductPriceByName(productName);
        productListPageSteps.addProductToCartByName(productName);
        productListPageSteps.verifyContentInCart(productName, productPrice);
    }

    @Test(description = "Add two product to cart search input")
    public void addTwoProductToCartViaSearch() {
        homePageSteps.searchProductByName("Apple iPhone");
        String firstProductPrice = productListPageSteps.rememberFirstProductPriceByPositionFromSearch(1);
        String firstProductName = productListPageSteps.rememberFirstProductNameByPositionFromSearch(1);
        productListPageSteps.addProductToCartByPosition(1);
        String secondProductPrice = productListPageSteps.rememberFirstProductPriceByPositionFromSearch(2);
        String secondProductName = productListPageSteps.rememberFirstProductNameByPositionFromSearch(2);
        productListPageSteps.addProductToCartByPosition(2);
        productListPageSteps.clickOnCart();
        productListPageSteps.verifyContentInCartTwoProduct(firstProductName, firstProductPrice, secondProductName, secondProductPrice);
    }

    @Test(description = "Add two product to cart from comparison page")
    public void addTwoProductToCartViaComparison() {
        homePageSteps.searchProductByName("Apple iPhone");
        String firstProductPrice = productListPageSteps.rememberFirstProductPriceByPositionFromSearch(1);
        String firstProductName = productListPageSteps.rememberFirstProductNameByPositionFromSearch(1);
        productListPageSteps.addToComparisonByPosition(1);
        String secondProductPrice = productListPageSteps.rememberFirstProductPriceByPositionFromSearch(2);
        String secondProductName = productListPageSteps.rememberFirstProductNameByPositionFromSearch(2);
        productListPageSteps.addToComparisonByPosition(2);
        productListPageSteps.clickOnComparison();
        comparisonPageSteps.addProductToCartByPosition(0);
        comparisonPageSteps.addProductToCartByPosition(2);
        comparisonPageSteps.clickOnCart();
        comparisonPageSteps.verifyContentInCartTwoProduct(firstProductName, firstProductPrice, secondProductName, secondProductPrice);
    }
}
