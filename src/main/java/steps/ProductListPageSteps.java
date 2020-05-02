package steps;

import com.codeborne.selenide.Condition;
import pages.citrus.ComparisonPage;
import pages.citrus.ProductListPage;

public class ProductListPageSteps {

    ProductListPage productListPage = new ProductListPage();
    ComparisonPage comparisonPage = new ComparisonPage();

    public void clickOnProduct(String productName) {
        productListPage.clickOnProductByName(productName);
    }

    public String rememberProductPriceByName(String productName) {
        return productListPage.getProductPriceByName(productName);
    }

    public void addProductToCartByName(String productName) {
        productListPage.clickAddToCartProductByName(productName);
        productListPage.getCartFragment()
                .clickOnCloseCartButton();
        productListPage.getHeaderFragment()
                .clickOnIconCart();
    }

    public void verifyContentInCart(String productName, String productPrice) {
        productListPage.getCartFragment().getCart().shouldBe(Condition.visible);
        productListPage.getCartFragment().getNamesFromCart().shouldHaveSize(1);
        productListPage.getCartFragment().getNamesFromCart().get(0).shouldHave(Condition.text(productName));
        productListPage.getCartFragment().getPricesFromCart().get(0).shouldHave(Condition.text(productPrice));
        productListPage.getCartFragment().getTotalPriceFromCart().shouldHave(Condition.text(productPrice));
    }

    public String rememberFirstProductPriceByPositionFromSearch(int position) {
        return productListPage.getProductPriceByPositionListFromSearch(position);
    }

    public String rememberFirstProductNameByPositionFromSearch(int position) {
        return productListPage.getProductNameByPositionListFromSearch(position);
    }

    public void addProductToCartByPosition(int position) {
        productListPage.clickAddToCartProductByPositionList(position);
        productListPage.getCartFragment()
                .clickOnCloseCartButton();
    }

    public void verifyContentInCartTwoProduct(String firstProductName, String firstProductPrice, String secondProductName, String secondProductPrice) {
        productListPage.getCartFragment().getCart().shouldBe(Condition.visible);
        productListPage.getCartFragment().getNamesFromCart().shouldHaveSize(2);
        productListPage.getCartFragment().getNamesFromCart().get(0).shouldHave(Condition.text(firstProductName));
        productListPage.getCartFragment().getPricesFromCart().get(0).shouldHave(Condition.text(firstProductPrice));
        productListPage.getCartFragment().getNamesFromCart().get(1).shouldHave(Condition.text(secondProductName));
        productListPage.getCartFragment().getPricesFromCart().get(1).shouldHave(Condition.text(secondProductPrice));
        productListPage.getCartFragment().getTotalPriceFromCart().shouldHave(Condition.text(productListPage.countTotalPriceInCart()));
    }

    public void addToComparisonByPosition(int position) {
        productListPage.clickAddToCompareProductByPositionListFromSearch(position);
    }

    public void clickOnCart() {
        productListPage.getHeaderFragment()
                .clickOnIconCart();
    }

    public void clickOnComparison() {
        productListPage.getHeaderFragment()
                .clickOnIconComparison();
        comparisonPage.clickOnSetBase();
    }

    public void addProductToCartByPositionFromMenu(int position) {
        productListPage.clickAddToCompareProductByPositionListFromMenu(position);
    }

    public String rememberFirstProductPriceByPositionFromMenu(int position) {
        return productListPage.getProductPriceByPositionListFromSearch(position);
    }

    public String rememberFirstProductNameByPositionFromMenu(int position) {
        return productListPage.getProductNameByPositionListFromSearch(position);
    }

    public String rememberAddedProductPriceFromComparison(int position) {
        return comparisonPage.getProductPriceAddToComparison().get(position).getText();
    }

    public String rememberAddedProductNameFromComparison(int position) {
       return comparisonPage.getProductNamesAddToComparison().get(position).getText();
    }

    public void setPriceFilter(String minPrice, String maxPrice) {
        productListPage.getFilterFragment()
                .setMinPrice(minPrice)
                .setMaxPrice(maxPrice);
    }

    public void verifyPriceFilter(String expectedNameProduct, int expectedMinPrice, int expectedMaxPrice) throws Exception {
        productListPage.getFilterFragment()
                .verifyFilterByName(expectedNameProduct);
        productListPage.getFilterFragment()
                .verifyPriceFilter(expectedMinPrice, expectedMaxPrice);
    }

    public void setMemorySizeFilter(int firstMemorySize, int secondMemorySize) {
        productListPage.getFilterFragment()
                .setMemorySize(firstMemorySize)
                .setMemorySize(secondMemorySize);
    }

    public void verifyMemorySizeFilter(String expectedNameProduct, String expectedFirstMemorySize, String expectedSecondMemorySize) throws Exception {
        productListPage.getFilterFragment()
                .verifyFilterByName(expectedNameProduct);
        productListPage.getFilterFragment()
                .verifyMemorySizeFilter(expectedFirstMemorySize, expectedSecondMemorySize);
    }

    public void settBodyMaterialFilter(String bodyMaterial) throws InterruptedException {
        productListPage.getFilterFragment()
                .setBodyMaterialFilter(bodyMaterial);
    }

    public void verifyBodyMaterialFilter(String expectedNameProduct, String expectedBodyMaterial) throws Exception {
        productListPage.getFilterFragment()
                .verifyFilterByName(expectedNameProduct);
        productListPage.getFilterFragment()
                .verifyBodyMaterialFilter(expectedBodyMaterial);
    }
}
