package pages.citrus;

import com.codeborne.selenide.SelenideElement;
import fragments.CartFragment;
import fragments.HeaderFragment;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends BasePage {

    CartFragment cartFragment = new CartFragment();
    HeaderFragment headerFragment = new HeaderFragment();

    SelenideElement productPrice = $x("//div[@class='price']/span");
    SelenideElement byuButton = $(".normal .btn");

    public String getProductPrice() {
        String[] price = productPrice.getText().split(" ");
        String convertPrice = price[0] + " " + price[1] + "грн";
        return convertPrice;
    }

    public ProductPage clickAddToCartButton() {
        byuButton.click();
        return this;
    }

    public CartFragment getCartFragment() {
        return cartFragment;
    }

    public HeaderFragment getHeaderFragment() {
        return headerFragment;
    }
}
