package pages.citrus;

import com.codeborne.selenide.Condition;
import fragments.CartFragment;
import fragments.FilterFragment;
import fragments.HeaderFragment;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProductListPage extends BasePage {

    CartFragment cartFragment = new CartFragment();
    HeaderFragment headerFragment = new HeaderFragment();
    FilterFragment filterFragment = new FilterFragment();

    public ProductListPage clickOnProductByName(String productName) {
        $$x("//div[@class='product-card__name']/a/span").findBy(Condition.text(productName)).click();
        return this;
    }

    public String getProductPriceByName(String productName) {
        return $x("//h5[contains(text(), '" + productName + "')]/following::*/span[@class='price-number']").getText();
    }

    public void clickAddToCartProductByName(String productName) {
        $x("//h5[contains(text(), '" + productName + "')]/following::*/i[@class='icon-new-citrus-cart el-tooltip item']").click();
    }

    public String getProductPriceByPositionListFromSearch(int positionProduct) {
        return $x("//div[@class='product-card product-card--mini'][" + positionProduct + "]//span[@class='price-number']").getText();
    }

    public String getProductNameByPositionListFromSearch(int positionProduct) {
        return $x("//div[@class='product-card product-card--mini'][" + positionProduct + "]//h5").getText();
    }

    public ProductListPage clickAddToCartProductByPositionList(int positionProduct) {
        $x("//div[@class='product-card product-card--mini'][" + positionProduct + "]//i[@class='icon-new-citrus-cart el-tooltip item']").click();
        return this;
    }

    public CartFragment getCartFragment() {
        return cartFragment;
    }

    public HeaderFragment getHeaderFragment() {
        return headerFragment;
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

    public ProductListPage clickAddToCompareProductByPositionListFromSearch(int positionProduct) {
        $x("//div[@class='product-card product-card--mini'][" + positionProduct + "]//i[@class='icon-comparison2 el-tooltip item']").hover().click();
        return this;
    }

    public FilterFragment getFilterFragment() {
        return filterFragment;
    }

    public void clickAddToCompareProductByPositionListFromMenu(int position) {
        $x("//div[@itemscope='itemscope'][" + position + "]//div[@class='product-card__actions']").hover();
        $x("//div[@itemscope='itemscope'][" + position + "]//span[@class='icon icon-comparison2 el-tooltip item']").click();
    }

    public String getProductPriceByPositionListFromMenu(int positionProduct) {
        return $x("//div[@itemscope='itemscope'][" + positionProduct + "]//div[@class='prices__price']/span").getText();
    }

    public String getProductNameByPositionListFromMenu(int positionProduct) {
        return $x("//div[@itemscope='itemscope'][" + positionProduct + "]//div[@class='product-card__name']/a").getText().substring(0, 20);
    }
}

