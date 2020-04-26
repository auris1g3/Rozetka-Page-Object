package fragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HeaderFragment {

    SelenideElement searchInput = $("#search-input");
    SelenideElement iconCart = $x("//i[@class='icon-new-citrus-cart']");
    SelenideElement iconComparison = $x("//i[@class='icon-comparison2']");

    public HeaderFragment searchProduct(String productName) {
        searchInput.val(productName).pressEnter();
        return this;
    }

    public void clickOnIconCart() {
        iconCart.click();
    }

    public void clickOnIconComparison() {
        iconComparison.click();
    }
}
