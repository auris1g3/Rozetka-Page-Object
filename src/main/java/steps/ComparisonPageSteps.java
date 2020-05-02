package steps;

import com.codeborne.selenide.Condition;
import pages.citrus.ComparisonPage;

public class ComparisonPageSteps {

    ComparisonPage comparisonPage = new ComparisonPage();

    public void addProductToCartByPosition(int position) {
        comparisonPage.clickAddToCartProduct(position);
        comparisonPage.getCartFragment()
                .clickOnCloseCartButton();
    }

    public void clickOnCart() {
        comparisonPage.getHeaderFragment()
                .clickOnIconCart();
    }

    public void verifyContentInCartTwoProduct(String firstProductName, String firstProductPrice, String secondProductName, String secondProductPrice) {
        comparisonPage.getCartFragment().getCart().shouldBe(Condition.visible);
        comparisonPage.getCartFragment().getNamesFromCart().shouldHaveSize(2);
        comparisonPage.getCartFragment().getNamesFromCart().get(0).shouldHave(Condition.text(firstProductName));
        comparisonPage.getCartFragment().getPricesFromCart().get(0).shouldHave(Condition.text(firstProductPrice));
        comparisonPage.getCartFragment().getNamesFromCart().get(1).shouldHave(Condition.text(secondProductName));
        comparisonPage.getCartFragment().getPricesFromCart().get(1).shouldHave(Condition.text(secondProductPrice));
        comparisonPage.getCartFragment().getTotalPriceFromCart().shouldHave(Condition.text(comparisonPage.countTotalPriceInCart()));
    }

    public void verifyContentInComparisonProduct(String firstProductName, String firstProductPrice, String secondProductName, String secondProductPrice) {
        comparisonPage.getCountProducts().shouldHaveSize(2);
        comparisonPage.getProductNames().get(0).shouldHave(Condition.text(firstProductName));
        comparisonPage.getProductPrice().get(0).shouldHave(Condition.text(firstProductPrice));
        comparisonPage.getProductNames().get(2).shouldHave(Condition.text(secondProductName));
        comparisonPage.getProductPrice().get(2).shouldHave(Condition.text(secondProductPrice));
    }

    public void clickOnAddToCompareButton() {
        comparisonPage.clickOnAddProductToComparisonButton();
    }

    public void addThirdProductToComparison() {
        comparisonPage.addFirstProductToComparison();
    }

    public void verifyContentInComparisonProduct(String firstProductName, String firstProductPrice, String secondProductName, String secondProductPrice, String addedProductName, String addedProductPrice) {
        comparisonPage.getCountProducts().shouldHaveSize(3);
        comparisonPage.getProductPrice().get(0).shouldHave(Condition.text(firstProductPrice));
        comparisonPage.getProductPrice().get(2).shouldHave(Condition.text(secondProductPrice));
        comparisonPage.getProductPrice().get(4).shouldHave(Condition.text(addedProductPrice));

        comparisonPage.getProductNames().get(0).shouldHave(Condition.text(firstProductName));
        comparisonPage.getProductNames().get(2).shouldHave(Condition.text(secondProductName));
        comparisonPage.getProductNames().get(4).shouldHave(Condition.text(addedProductName));
    }
}