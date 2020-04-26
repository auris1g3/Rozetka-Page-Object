package citrus;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.citrus.ComparisonPage;
import pages.citrus.HomePage;
import pages.citrus.ProductListPage;
import pages.citrus.ProductPage;

import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.Selenide.open;


public class CartTest {

    HomePage homePage;
    ProductListPage productListPage;
    ProductPage productPage;
    ComparisonPage comparisonPage;
    String productName = "Apple iPhone 11 128Gb Black";

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

    @Test
    public void addProductToCartViaMenu() {
        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLne("Смартфоны")
                .clickLinkMenu("Apple");
        productListPage.clickOnProductByName(productName);
        String productPrice = productPage.getProductPrice();
        productPage.clickAddToCartButton();

        productPage.getCartFragment().getCart().shouldBe(Condition.visible);
        productPage.getCartFragment().getNamesFromCart().shouldHaveSize(1);
        productPage.getCartFragment().getNamesFromCart().get(0).shouldHave(Condition.text(productName));
        productPage.getCartFragment().getPricesFromCart().get(0).shouldHave(Condition.text(productPrice));
        productPage.getCartFragment().getTotalPriceFromCart().shouldHave(Condition.text(productPrice));
    }

    @Test
    public void addProductToCartViaSearch() {
        homePage.waitForPageToLoad()
                .closePopUp()
                .getSearchFragment()
                .searchProduct("Apple iPhone 11");
        String productPrice = productListPage.getProductPriceByName(productName);
        productListPage.clickAddToCartProductByName(productName);

        productListPage.getCartFragment().getCart().shouldBe(Condition.visible);
        productListPage.getCartFragment().getNamesFromCart().shouldHaveSize(1);
        productListPage.getCartFragment().getNamesFromCart().get(0).shouldHave(Condition.text(productName));
        productListPage.getCartFragment().getPricesFromCart().get(0).shouldHave(Condition.text(productPrice));
        productListPage.getCartFragment().getTotalPriceFromCart().shouldHave(Condition.text(productPrice));
    }

    @Test
    public void addTwoProductToCartViaSearch() {
        homePage.waitForPageToLoad()
                .closePopUp()
                .getSearchFragment()
                .searchProduct("Apple iPhone");
        String firstProductPrice = productListPage.getProductPriceByPositionList(1);
        String firstProductName = productListPage.getProductNameByPositionList(1);
        productListPage.clickAddToCartProductByPositionList(1);
        productListPage.getCartFragment()
                .clickOnCloseCartButton();

        String secondProductPrice = productListPage.getProductPriceByPositionList(2);
        String secondProductName = productListPage.getProductNameByPositionList(2);
        productListPage.clickAddToCartProductByPositionList(2);
        productListPage.getCartFragment()
                .clickOnCloseCartButton();
        productListPage.getSearchFragment()
                .clickOnIconCart();

        productListPage.getCartFragment().getCart().shouldBe(Condition.visible);
        productListPage.getCartFragment().getNamesFromCart().shouldHaveSize(2);
        productListPage.getCartFragment().getNamesFromCart().get(0).shouldHave(Condition.text(firstProductName));
        productListPage.getCartFragment().getPricesFromCart().get(0).shouldHave(Condition.text(firstProductPrice));
        productListPage.getCartFragment().getNamesFromCart().get(1).shouldHave(Condition.text(secondProductName));
        productListPage.getCartFragment().getPricesFromCart().get(1).shouldHave(Condition.text(secondProductPrice));
        productListPage.getCartFragment().getTotalPriceFromCart().shouldHave(Condition.text(productListPage.countTotalPriceInCart()));
    }

    @Test
    public void addTwoProductToCartViaComparison() {
        homePage.waitForPageToLoad()
                .closePopUp()
                .getSearchFragment()
                .searchProduct("Apple iPhone");

        String firstProductPrice = productListPage.getProductPriceByPositionList(1);
        String firstProductName = productListPage.getProductNameByPositionList(1);
        productListPage.clickAddToCompareProductByPositionList(1);

        String secondProductPrice = productListPage.getProductPriceByPositionList(2);
        String secondProductName = productListPage.getProductNameByPositionList(2);
        productListPage.clickAddToCompareProductByPositionList(2);
        productListPage.getSearchFragment()
                .clickOnIconComparison();

        comparisonPage.clickOnSetBase();
        comparisonPage.clickAddToCartProduct(0);
        productListPage.getCartFragment()
                .clickOnCloseCartButton();
        comparisonPage.clickAddToCartProduct(2);
        productListPage.getCartFragment()
                .clickOnCloseCartButton();
        productListPage.getSearchFragment()
                .clickOnIconCart();

        productListPage.getCartFragment().getCart().shouldBe(Condition.visible);
        productListPage.getCartFragment().getNamesFromCart().shouldHaveSize(2);
        productListPage.getCartFragment().getNamesFromCart().get(0).shouldHave(Condition.text(firstProductName));
        productListPage.getCartFragment().getPricesFromCart().get(0).shouldHave(Condition.text(firstProductPrice));
        productListPage.getCartFragment().getNamesFromCart().get(1).shouldHave(Condition.text(secondProductName));
        productListPage.getCartFragment().getPricesFromCart().get(1).shouldHave(Condition.text(secondProductPrice));
        productListPage.getCartFragment().getTotalPriceFromCart().shouldHave(Condition.text(productListPage.countTotalPriceInCart()));

    }
}
