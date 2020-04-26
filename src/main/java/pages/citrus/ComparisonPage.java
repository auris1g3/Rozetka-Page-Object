package pages.citrus;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class ComparisonPage {

    SelenideElement setBaseProduct = $x("//a[@class='set-as-base']");

    public void clickAddToCartProduct(int number) {
        $$(".icon-new-citrus-cart.el-tooltip.item").get(number).click();
    }

    public void clickOnSetBase() {
        setBaseProduct.click();
    }
}
