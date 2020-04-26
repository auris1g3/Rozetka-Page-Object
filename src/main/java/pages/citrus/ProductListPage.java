package pages.citrus;

import com.codeborne.selenide.Condition;
import fragments.CitrusCartFragment;
import fragments.CitrusSearchFragment;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProductListPage extends BasePage {

    CitrusCartFragment cartFragment = new CitrusCartFragment();
    CitrusSearchFragment searchFragment = new CitrusSearchFragment();

    public ProductListPage clickOnProductByName(String productName) {
        $$x("//div[@class='product-card__name']/a/span").findBy(Condition.text(productName)).click();
        return this;
    }

    public String getProductPriceByName(String productName) {
        return $x("//h5[contains(text(), '" + productName + "')]/following::*/span[@class='price-number']").getText();
    }

    public ProductListPage clickAddToCartProductByName(String productName) {
        $x("//h5[contains(text(), '" + productName + "')]/following::*/i[@class='icon-new-citrus-cart el-tooltip item']").click();
        return this;
    }

    public String getProductPriceByPositionList(int positionProduct) {
        return $x("//div[@class='product-card product-card--mini'][" + positionProduct + "]//span[@class='price-number']").getText();
    }

    public String getProductNameByPositionList(int positionProduct) {
        return $x("//div[@class='product-card product-card--mini'][" + positionProduct + "]//h5").getText();
    }

    public ProductListPage clickAddToCartProductByPositionList(int positionProduct) {
        $x("//div[@class='product-card product-card--mini'][" + positionProduct + "]//i[@class='icon-new-citrus-cart el-tooltip item']").click();
        return this;
    }

    public CitrusCartFragment getCartFragment() {
        return cartFragment;
    }

    public CitrusSearchFragment getSearchFragment() {
        return searchFragment;
    }

    public String countTotalPriceInCart() {
        int firstPrice = Integer.parseInt($$x("//span[@class='ctrs-main-price']").get(0).getText().replace(" ", "").replace("грн", ""));
        int secondPrice = Integer.parseInt($$x("//span[@class='ctrs-main-price']").get(1).getText().replace(" ", "").replace("грн", ""));
        int totalSum = firstPrice + secondPrice;
        String s = Integer.toString(totalSum) + "грн";
        StringBuilder sb = new StringBuilder(s);
        sb.insert(2, " ");
        return sb.toString();
    }

    public ProductListPage clickAddToCompareProductByPositionList(int positionProduct) {
        $x("//div[@class='product-card product-card--mini'][" + positionProduct + "]//i[@class='icon-comparison2 el-tooltip item']").click();
        return this;
    }
}
