package pages.citrus;

import com.codeborne.selenide.SelenideElement;
import fragments.CitrusSearchFragment;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage {

    CitrusSearchFragment searchFragment = new CitrusSearchFragment();

    SelenideElement popUpCloseButton = $(".el-icon-close");

    public HomePage hoverMenuLne(String lineName) {
        $x("//span[contains(text(),'" + lineName + "')]").hover();
        return this;
    }

    public HomePage clickLinkMenu(String linkText) {
        $x("//a[@href='/smartfony/brand-apple/']/span[contains(text(),'" + linkText + "')]").click();
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

    public CitrusSearchFragment getSearchFragment() {
        return searchFragment;
    }
}
