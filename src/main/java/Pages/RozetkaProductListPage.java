package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Integer.parseInt;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class RozetkaProductListPage {

    WebDriver webDriver;
    WebDriverWait wait;

    By waitingForLoadProductList = By.xpath("//img[@title='Интернет-супермаркет ROZETKA']");
    By priceAllMonitors = By.xpath("//span[@class='goods-tile__price-value']");
    By linkFirstMonitor = By.xpath("//a[@class='goods-tile__picture']");

    public RozetkaProductListPage(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public void waitLoadProductListPage() {
        wait.until(( presenceOfElementLocated(waitingForLoadProductList) ));
    }

    public int clickOnMonitorWithPriceLessThen(int price) {
        int savePrice = 0;
        List<WebElement> listPriceAllMonitors = webDriver.findElements(priceAllMonitors);
        for (int i = 0; i < listPriceAllMonitors.size(); i++) {
            int tempPrice = parseInt(listPriceAllMonitors.get(i).getText().replaceAll(" ", ""));
            if (tempPrice < price) {
                savePrice = tempPrice;
                WebElement element = webDriver.findElements(linkFirstMonitor).get(i);
                element.click();
                break;
            }
        }
        return savePrice;
    }
}
