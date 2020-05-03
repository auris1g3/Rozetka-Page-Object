package steps;

import io.qameta.allure.Step;
import pages.citrus.HomePage;

public class HomePageSteps {

    HomePage homePage = new HomePage();

    @Step
    public void clickOnLinkInMenu(String linkText, String name) {
        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLne(linkText)
                .clickLinkMenu(name);
    }

    @Step
    public void searchProductByName(String productName) {
        homePage.waitForPageToLoad()
                .closePopUp()
                .getHeaderFragment()
                .searchProduct("Apple iPhone 11");
    }
}
