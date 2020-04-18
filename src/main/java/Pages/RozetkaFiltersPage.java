package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class RozetkaFiltersPage {

    WebDriver webDriver;
    WebDriverWait wait;

    By titleAllProducts = By.xpath("//span[@class='goods-tile__title']");
    By waitingForLoadProductList = By.xpath("//img[@title='Интернет-супермаркет ROZETKA']");
    By elseButtonMonitorAndNotebook = By.xpath("//a[@name='show_more_categories']/ancestor::ul/li[3]//span[text()='Еще']");
    By buttonTablet = By.xpath("//a[text()='Планшеты']");
    By checkBoxAcer = By.xpath("//label[@for='Acer']");
    By checkBoxAsus = By.xpath("//label[@for='Asus']");
    By allProductsAddedByFilters = By.xpath("//span[@class='catalog-more__text']");
    By minPrice = By.xpath("//input[@formcontrolname='min']");
    By maxPrice = By.xpath("//input[@formcontrolname='max']");
    By okButtonPrice = By.xpath("//button[@type='submit' and text()=' Ok ']");
    By allPriceValueByPriceFilter = By.xpath("//span[@class='goods-tile__price-value']");
    By ramFilter = By.xpath("//label[@for='4 ГБ']");
    By screenType = By.xpath("//label[@for='AMOLED']");
    By androidType = By.xpath("//label[@for='Android 9.x']");
    By descriptionProduct = By.xpath("//p[@class='goods-tile__description goods-tile__description_type_text']");
    By blockOfProduct = By.xpath("//li[@class='catalog-grid__cell catalog-grid__cell_type_slim']");

    public RozetkaFiltersPage(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public void clickOnElseButtonMonitorAndNotebook() {
        webDriver.findElement(elseButtonMonitorAndNotebook).click();
    }

    public void clickOnButtonTablet() {
        webDriver.findElement(buttonTablet).click();
    }

    public void clickOnCheckBoxAcer() {
        webDriver.findElement(checkBoxAcer).click();
    }

    public void clickOnCheckBoxAsus() {
        webDriver.findElement(checkBoxAsus).click();
    }

    public void clickOnAllProductsAddedByFilters() {
        webDriver.findElement(allProductsAddedByFilters).click();
    }

    public void waitLoadProductListPage() {
        wait.until(( presenceOfElementLocated(waitingForLoadProductList) ));
    }

    public boolean verifyFilterManufacturer(String firstTitle, String secondTitle, String thirdTitle) throws Exception {
        List<WebElement> allTitle = webDriver.findElements(titleAllProducts);
        for (WebElement element : allTitle) {
            String titleProduct = element.getText().toLowerCase();
            if (!( titleProduct.contains(firstTitle) || titleProduct.contains(secondTitle) || titleProduct.contains(thirdTitle) )) {
                throw new Exception("No Acer or Samsung or Asus");
            }
        }
        return true;
    }

    public boolean verifyPriceFilter(int minPriceValue, int maxPriceValue) throws Exception {
        List<WebElement> allPrice = webDriver.findElements(allPriceValueByPriceFilter);
        for (WebElement webElement : allPrice) {
            int price = Integer.parseInt(webElement.getText().replaceAll(" ", ""));
            if (!( price > minPriceValue && price < maxPriceValue )) {
                throw new Exception("Selected products do not fall within the range of selected prices");
            }
        }
        return true;
    }

    public boolean verifyThreeFilters() throws Exception {
        List<WebElement> productsWithThreeFilters = webDriver.findElements(descriptionProduct);
        for (WebElement productsWithThreeFilter : productsWithThreeFilters) {
            String text = productsWithThreeFilter.getText().toLowerCase();
            if (!text.contains("amoled android 9 4 гб")) {
                throw new Exception("Not selected Android 9 or AMOLED or RAM 4 Gb");
            }
        }
        return true;
    }

    public void setMinPrice(String price) {
        WebElement inputMinPrice = webDriver.findElement(minPrice);
        inputMinPrice.click();
        inputMinPrice.clear();
        inputMinPrice.sendKeys(price);
    }

    public void setMaxPrice(String price) {
        WebElement inputMaxPrice = webDriver.findElement(maxPrice);
        inputMaxPrice.click();
        inputMaxPrice.clear();
        inputMaxPrice.sendKeys(price);
    }

    public void clickOkPriceButton() {
        webDriver.findElement(okButtonPrice).click();
    }

    public void setRamType() {
        webDriver.findElement(ramFilter).click();
    }

    public void setScreenType() {
        webDriver.findElement(screenType).click();
    }

    public void setAndroidType() {
        webDriver.findElement(androidType).click();
    }

    public void hoverToProductAfterFilter() {
        Actions actions = new Actions(webDriver);
        WebElement product = webDriver.findElement(blockOfProduct);
        actions.moveToElement(product).perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
