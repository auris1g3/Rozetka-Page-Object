package pages.rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class RozetkaProductDetailsPage {

    WebDriver webDriver;
    WebDriverWait wait;

    By waitingLoadDetailsProductPage = By.xpath("//ul[@class='product-actions']");
    By comparingForm = By.xpath("//ul[@class='product-actions']");
    By compareButton = By.xpath("//button[@class='compare-button']");
    By priceMonitor = By.xpath("//p[@class='product-prices__big product-prices__big_color_red']");
    By nameMonitor = By.xpath("//h1[@class='product__title']");
    By loginLink = By.xpath("//a[@class='header-topline__user-link link-dashed']");
    By counterComparingProducts = By.xpath("//span[@class='header-actions__button-counter']");
    By baseCompareButton = By.xpath("//a[@class='header-actions__button header-actions__button_type_compare header-actions__button_state_active']");
    By comparingProducts = By.linkText("Мониторы (2)");

    public RozetkaProductDetailsPage(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public void waitLoadProductDetailsPage() {
        wait.until(( presenceOfElementLocated(waitingLoadDetailsProductPage) ));
    }

    public void scrollToComparingForm() {
        WebElement comparingFormElement = webDriver.findElement(comparingForm);
        scrollToElement(comparingFormElement);
    }

    public void clickOnCompareButton() {
        WebElement compareButtonElement = webDriver.findElement(compareButton);
        compareButtonElement.click();
    }

    public String getPriceMonitor() {
        return webDriver.findElement(priceMonitor).getText().substring(0, 4);
    }

    public String getNameMonitor() {
        return webDriver.findElement(nameMonitor).getText();
    }

    public String getCounterComparingProducts() {
        return webDriver.findElement(counterComparingProducts).getText();
    }

    public void navigateBack() {
        webDriver.navigate().back();
    }

    public void scrollToCountCompareProducts() {
        WebElement countCompareProductsElement = webDriver.findElement(loginLink);
        scrollToElement(countCompareProductsElement);
    }

    public void scrollToElement(WebElement element) {
        ( (JavascriptExecutor) webDriver ).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickOnBaseCompareButton() {
        Actions actions = new Actions(webDriver);
        WebElement LinkProductsCategory = webDriver.findElement(baseCompareButton);
        actions.moveToElement(LinkProductsCategory).perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriver.findElement(comparingProducts).click();
    }
}
