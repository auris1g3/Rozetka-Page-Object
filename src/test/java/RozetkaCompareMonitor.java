import Pages.RozetkaComparePage;
import Pages.RozetkaHomePage;
import Pages.RozetkaProductDetailsPage;
import Pages.RozetkaProductListPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RozetkaCompareMonitor extends BaseTestClass {

    String homeUrl = "https://rozetka.com.ua/";
    int priceFirstMonitorInt;
    String priceFirstMonitor;
    String nameFirstMonitor;
    String priceSecondMonitor;
    String nameSecondMonitor;
    String nameFirstMonitorOnComparePage;
    String nameSecondMonitorOnComparePage;
    String priceFirstMonitorOnComparePage;
    String priceSecondMonitorOnComparePage;

    @BeforeMethod
    public void navigateToHomeUrl() {
        driver.navigate().to(homeUrl);
    }

    @Test
    public void compareTwoMonitors() {
        RozetkaHomePage rozetkaHomePage = new RozetkaHomePage(driver, wait);
        RozetkaProductListPage rozetkaProductListPage = new RozetkaProductListPage(driver, wait);
        RozetkaProductDetailsPage rozetkaProductDetailsPage = new RozetkaProductDetailsPage(driver, wait);
        RozetkaComparePage rozetkaComparePage = new RozetkaComparePage(driver, wait);

        rozetkaHomePage.hoverToProductsCategory();
        rozetkaHomePage.waitProductsFromCategory();
        rozetkaHomePage.clickOnMonitorsProducts();

        rozetkaProductListPage.waitLoadProductListPage();
        priceFirstMonitorInt = rozetkaProductListPage.clickOnMonitorWithPriceLessThen(3000);

        rozetkaProductDetailsPage.waitLoadProductDetailsPage();
        rozetkaProductDetailsPage.scrollToComparingForm();
        rozetkaProductDetailsPage.clickOnCompareButton();
        priceFirstMonitor = rozetkaProductDetailsPage.getPriceMonitor();
        nameFirstMonitor = rozetkaProductDetailsPage.getNameMonitor();
        rozetkaProductDetailsPage.scrollToCountCompareProducts();
        assertEquals(rozetkaProductDetailsPage.getCounterComparingProducts(), "1");
        rozetkaProductDetailsPage.navigateBack();

        rozetkaProductListPage.waitLoadProductListPage();
        rozetkaProductListPage.clickOnMonitorWithPriceLessThen(priceFirstMonitorInt);
        rozetkaProductDetailsPage.scrollToComparingForm();
        rozetkaProductDetailsPage.clickOnCompareButton();
        priceSecondMonitor = rozetkaProductDetailsPage.getPriceMonitor();
        nameSecondMonitor = rozetkaProductDetailsPage.getNameMonitor();
        rozetkaProductDetailsPage.scrollToCountCompareProducts();
        assertEquals(rozetkaProductDetailsPage.getCounterComparingProducts(), "2");
        rozetkaProductDetailsPage.clickOnBaseCompareButton();

        rozetkaComparePage.waitLoadComparePage();
        nameFirstMonitorOnComparePage = rozetkaComparePage.getProductNameOnComparePage(0);
        assertEquals(nameFirstMonitor, nameFirstMonitorOnComparePage);

        nameSecondMonitorOnComparePage = rozetkaComparePage.getProductNameOnComparePage(1);
        assertEquals(nameSecondMonitor, nameSecondMonitorOnComparePage);

        priceFirstMonitorOnComparePage = rozetkaComparePage.getProductPriceOnComparePage(0);
        assertEquals(priceFirstMonitor, priceFirstMonitorOnComparePage);

        priceSecondMonitorOnComparePage = rozetkaComparePage.getProductPriceOnComparePage(1);
        assertEquals(priceSecondMonitor, priceSecondMonitorOnComparePage);
    }
}


