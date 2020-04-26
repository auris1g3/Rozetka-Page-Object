package fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CitrusCartFragment {

    SelenideElement cartPopUp = $x("//div[@class='el-dialog el-dialog--medium']");
    ElementsCollection productNames = $$(".ctrs-basket-product__name");
    SelenideElement cartTotalPrice = $(".ctrs-basket-footer__new-price");
    ElementsCollection productPrices = $$x("//span[@class='ctrs-main-price']");
    SelenideElement closeCartButton = $x("//button[@class='el-dialog__headerbtn']");

    public SelenideElement getCart() {
        return cartPopUp;
    }

    public ElementsCollection getNamesFromCart() {
        return productNames;
    }

    public SelenideElement getTotalPriceFromCart() {
        return cartTotalPrice;
    }

    public ElementsCollection getPricesFromCart() {
        return productPrices;
    }

    public void clickOnCloseCartButton() {
        closeCartButton.click();
    }
}
