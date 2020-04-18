package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RozetkaComparePage {

    WebDriver webDriver;
    WebDriverWait wait;

    By waitingLoadComparePage = By.xpath("//a[text()='Очистить все']");
    By nameProducts = By.xpath("//a[@class='comparison-g-title g-title novisited']");
    By priceProducts = By.xpath("//div[@class='g-price-uah']");

    public RozetkaComparePage(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public void waitLoadComparePage() {
        webDriver.findElement(waitingLoadComparePage);
    }

    public String getProductNameOnComparePage(int numberProductInList) {
        String productName;
        List<WebElement> listProduct = webDriver.findElements(nameProducts);
        productName = listProduct.get(numberProductInList).getText();
        return productName;
    }

    public String getProductPriceOnComparePage(int numberProductInList) {
        String productPrice;
        List<WebElement> listProduct = webDriver.findElements(priceProducts);
        productPrice = listProduct.get(numberProductInList).getText().substring(0, 4).trim();
        return productPrice;
    }
}
