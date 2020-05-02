package steps;

import com.codeborne.selenide.Condition;
import pages.citrus.ProductPage;

public class ProductPageSteps {

    ProductPage productPage = new ProductPage();

    public String rememberProductPrice() {
        return productPage.getProductPrice();
    }

    public void addProductToBasket() {
        productPage.clickAddToCartButton();
        productPage.getCartFragment()
                .clickOnCloseCartButton();
        productPage.getHeaderFragment()
                .clickOnIconCart();
    }

    public void verifyContentInCart(String productName, String productPrice) {
        productPage.getCartFragment().getCart().shouldBe(Condition.visible);
        productPage.getCartFragment().getNamesFromCart().shouldHaveSize(1);
        productPage.getCartFragment().getNamesFromCart().get(0).shouldHave(Condition.text(productName));
        productPage.getCartFragment().getPricesFromCart().get(0).shouldHave(Condition.text(productPrice));
        productPage.getCartFragment().getTotalPriceFromCart().shouldHave(Condition.text(productPrice));
    }

}
