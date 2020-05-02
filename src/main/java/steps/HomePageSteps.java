package steps;

import pages.citrus.HomePage;

public class HomePageSteps {

    HomePage homePage = new HomePage();

    public void clickOnLinkInMenu(String linkText, String name) {
        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLne(linkText)
                .clickLinkMenu(name);
    }

    public void searchProductByName(String productName) {
        homePage.waitForPageToLoad()
                .closePopUp()
                .getHeaderFragment()
                .searchProduct("Apple iPhone 11");
    }
}
