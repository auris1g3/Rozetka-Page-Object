package pages.citrus;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import fragments.CartFragment;
import fragments.HeaderFragment;

import static com.codeborne.selenide.Selenide.*;

public class ComparisonPage {

    CartFragment cartFragment = new CartFragment();
    HeaderFragment headerFragment = new HeaderFragment();

    SelenideElement setBaseProduct = $x("//a[@class='set-as-base']");
    ElementsCollection countProducts = $$x("//div[@class='relative']");
    ElementsCollection productNames = $$x("//div[@class='md-description']//h5");
    ElementsCollection productPrice = $$x("//div[@class='base-price']/span");
    SelenideElement addProductToComparisonButton = $x("//span[@class='flex-column icon']");
    SelenideElement firstProductAddToComparison = $x("//p[@class='product-name']");
    ElementsCollection productPriceAddToComparison = $$x("//div[@class='price-container']//span[@class='price-new']/span");
    ElementsCollection productNamesAddToComparison = $$x("//p[@class='product-name']");
    SelenideElement addButton = $x("//button[@class='el-button el-button--primary']");

    public void clickAddToCartProduct(int number) {
        $$(".icon-new-citrus-cart.el-tooltip.item").get(number).click();
    }

    public void clickOnSetBase() {
        setBaseProduct.waitUntil(Condition.appear, 1000).click();
    }

    public CartFragment getCartFragment() {
        return cartFragment;
    }

    public HeaderFragment getHeaderFragment() {
        return headerFragment;
    }

    public ElementsCollection getCountProducts(){
        return countProducts;
    }

    public ElementsCollection getProductNames(){
        return productNames;
    }

    public ElementsCollection getProductPrice(){
        return productPrice;
    }

    public void clickOnAddProductToComparisonButton() {
        addProductToComparisonButton.click();
    }

    public void addFirstProductToComparison() {
        firstProductAddToComparison.click();
        addButton.click();
    }

    public ElementsCollection getProductPriceAddToComparison() {
        return productPriceAddToComparison;
    }

    public ElementsCollection getProductNamesAddToComparison() {
        return productNamesAddToComparison;
    }
}
