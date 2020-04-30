package ui.citrus;

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

    @Test
    public void comparisonTwoPlusOneProduct(){
        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLne("Ноутбуки")
                .clickLinkMenu("Acer");

        productListPage.clickAddToCompareProductByPositionListFromMenu(1);
        String firstProductPrice = productListPage.getProductPriceByPositionListFromMenu(1);
        String firstProductName = productListPage.getProductNameByPositionListFromMenu(1);

        productListPage.clickAddToCompareProductByPositionListFromMenu(2);
        String secondProductPrice = productListPage.getProductPriceByPositionListFromMenu(2);
        String secondProductName = productListPage.getProductNameByPositionListFromMenu(2);

        productListPage.getHeaderFragment()
                .clickOnIconComparison();
        comparisonPage.clickOnSetBase();

        comparisonPage.getCountProducts().shouldHaveSize(2);
        comparisonPage.getProductNames().get(0).shouldHave(Condition.text(firstProductName));
        comparisonPage.getProductNames().get(2).shouldHave(Condition.text(secondProductName));

        comparisonPage.getProductPrice().get(0).shouldHave(Condition.text(firstProductPrice));
        comparisonPage.getProductPrice().get(2).shouldHave(Condition.text(secondProductPrice));

        comparisonPage.clickOnAddProductToComparisonButton();
        String addingProductPrice = comparisonPage.getProductPriceAddToComparison().get(0).getText();
        String addingProductName = comparisonPage.getProductNamesAddToComparison().get(0).getText();
        comparisonPage.addFirstProductToComparison();

        comparisonPage.getCountProducts().shouldHaveSize(3);
        comparisonPage.getProductPrice().get(0).shouldHave(Condition.text(firstProductPrice));
        comparisonPage.getProductPrice().get(2).shouldHave(Condition.text(secondProductPrice));
        comparisonPage.getProductPrice().get(4).shouldHave(Condition.text(addingProductPrice));

        comparisonPage.getProductNames().get(0).shouldHave(Condition.text(firstProductName));
        comparisonPage.getProductNames().get(2).shouldHave(Condition.text(secondProductName));
        comparisonPage.getProductNames().get(4).shouldHave(Condition.text(addingProductName));


    }
}
