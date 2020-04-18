import Pages.RozetkaFiltersPage;
import Pages.RozetkaHomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class RozetkaFilters extends BaseTestClass {

    String homeUrl = "https://rozetka.com.ua/";

    @BeforeMethod
    public void navigateToHomeUrl() {
        driver.navigate().to(homeUrl);
    }

    @Test
    public void verifyManufacturerFilter() throws Exception {
        RozetkaHomePage rozetkaHomePage = new RozetkaHomePage(driver, wait);
        RozetkaFiltersPage rozetkaFiltersPage = new RozetkaFiltersPage(driver, wait);
        rozetkaHomePage.getResultSearchItems("samsung");
        rozetkaFiltersPage.waitLoadProductListPage();
        rozetkaFiltersPage.clickOnElseButtonMonitorAndNotebook();
        rozetkaFiltersPage.clickOnButtonTablet();
        rozetkaFiltersPage.clickOnCheckBoxAcer();
        rozetkaFiltersPage.clickOnCheckBoxAsus();
        rozetkaFiltersPage.clickOnAllProductsAddedByFilters();
        assertTrue(rozetkaFiltersPage.verifyFilterManufacturer("asus", "samsung", "acer"));
    }

    @Test
    public void verifyPriceFilter() throws Exception {
        RozetkaHomePage rozetkaHomePage = new RozetkaHomePage(driver, wait);
        RozetkaFiltersPage rozetkaFiltersPage = new RozetkaFiltersPage(driver, wait);
        rozetkaHomePage.getResultSearchItems("samsung");
        rozetkaFiltersPage.waitLoadProductListPage();
        rozetkaFiltersPage.clickOnElseButtonMonitorAndNotebook();
        rozetkaFiltersPage.clickOnButtonTablet();
        rozetkaFiltersPage.setMinPrice("5000");
        rozetkaFiltersPage.setMaxPrice("15000");
        rozetkaFiltersPage.clickOkPriceButton();
        assertTrue(rozetkaFiltersPage.verifyPriceFilter(5000, 15000));
    }

    @Test
    public void verifyThreeFilters() throws Exception {
        RozetkaHomePage rozetkaHomePage = new RozetkaHomePage(driver, wait);
        RozetkaFiltersPage rozetkaFiltersPage = new RozetkaFiltersPage(driver, wait);
        rozetkaHomePage.getResultSearchItems("samsung");
        rozetkaFiltersPage.waitLoadProductListPage();
        rozetkaFiltersPage.clickOnElseButtonMonitorAndNotebook();
        rozetkaFiltersPage.clickOnButtonTablet();
        rozetkaFiltersPage.setRamType();
        rozetkaFiltersPage.setScreenType();
        rozetkaFiltersPage.setAndroidType();
        rozetkaFiltersPage.hoverToProductAfterFilter();
        assertTrue(rozetkaFiltersPage.verifyThreeFilters());
    }
}


