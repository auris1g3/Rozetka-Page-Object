package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class RozetkaHomePage {

    WebDriver webDriver;
    WebDriverWait wait;

    By productsCategory = By.xpath("//a[@class='menu-categories__link']");
    By waitingForProductsFromCategory = By.linkText("Все категории");
    By monitorsProducts = By.xpath("//a[@class='menu__link' and text()=' Мониторы ']");
    By inputSearch = By.name("search");

    public RozetkaHomePage(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public void hoverToProductsCategory() {
        Actions actions = new Actions(webDriver);
        WebElement LinkProductsCategory = webDriver.findElement(productsCategory);
        actions.moveToElement(LinkProductsCategory).perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitProductsFromCategory() {
        wait.until(( presenceOfElementLocated(waitingForProductsFromCategory) ));
    }

    public void clickOnMonitorsProducts() {
        webDriver.findElement(monitorsProducts).click();
    }

    public void getResultSearchItems (String text) {
        webDriver.findElement(inputSearch).sendKeys(text + Keys.ENTER);
    }
}

