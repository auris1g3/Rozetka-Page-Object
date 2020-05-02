package pages.citrus;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$$x;

public class BasePage {

    private final WebDriver webDriver = WebDriverRunner.getWebDriver();

    public BasePage waitForPageToLoad() {
        new WebDriverWait(webDriver, 10000).until(
                webDriver1 -> ( (JavascriptExecutor) webDriver ).executeScript("return document.readyState").equals("complete"));
        return this;
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
}
