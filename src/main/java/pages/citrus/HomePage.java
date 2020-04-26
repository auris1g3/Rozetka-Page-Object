package pages.citrus;

import com.codeborne.selenide.SelenideElement;
import fragments.HeaderFragment;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage {

    HeaderFragment headerFragment = new HeaderFragment();

    SelenideElement popUpCloseButton = $(".el-icon-close");

    public HomePage hoverMenuLne(String lineName) {
        $x("//span[contains(text(),'" + lineName + "')]").hover();
        return this;
    }

    public HomePage clickLinkMenu(String linkText) {
        $x("//li[@class='menu-aim__item menu-aim__item--active']//span[contains(text(),'" + linkText + "')]").click();
        return this;
    }

    public HomePage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public HomePage closePopUp() {
        if (popUpCloseButton.isDisplayed()) {
            popUpCloseButton.click();
        }
        return this;
    }

    public HeaderFragment getHeaderFragment() {
        return headerFragment;
    }
}
