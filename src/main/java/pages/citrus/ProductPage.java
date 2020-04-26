package pages.citrus;

import com.codeborne.selenide.SelenideElement;
import fragments.CitrusCartFragment;
import fragments.CitrusSearchFragment;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends BasePage {

    CitrusCartFragment cartFragment = new CitrusCartFragment();
    CitrusSearchFragment searchFragment = new CitrusSearchFragment();

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

    public CitrusCartFragment getCartFragment() {
        return cartFragment;
    }
}
