package fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;

public class FilterFragment {

    SelenideElement minPrice = $$(".el-input__inner").get(0);
    SelenideElement maxPrice = $$(".el-input__inner").get(1);

    public FilterFragment setMinPrice(String min) {
        minPrice.click();
        minPrice.clear();
        minPrice.val(min).pressEnter();
        return this;
    }

    public FilterFragment setMaxPrice(String max) {
        maxPrice.click();
        maxPrice.clear();
        maxPrice.val(max).pressEnter();
        return this;
    }

    public boolean verifyPriceFilter(int minPriceValue, int maxPriceValue) throws Exception {
        ElementsCollection allPrice = $$(".prices__price .price");
        for (int i = 0; i < allPrice.size(); i++) {
            int price = Integer.parseInt(allPrice.get(i).getText().replaceAll(" ", ""));
            if (!( price > minPriceValue && price < maxPriceValue )) {
                throw new Exception("Selected products do not fall within the range of selected prices");
            }
        }
        return true;
    }

    public boolean verifyFilterByName(String nameProduct) throws Exception {
        ElementsCollection allNames = $$x("//div[@class='product-card__name']//span");
        for (int i = 0; i < allNames.size(); i++) {
            if (!( allNames.get(i).getText().contains(nameProduct) )) {
                throw new Exception("This product was not chosen");
            }
        }
        return true;
    }

    public FilterFragment setMemorySize(int memorySize) {
        SelenideElement checkBoxMemorySize = $x("//span[@class='el-checkbox__label']/a[text()='" + memorySize + " Гб']").waitUntil(appear, 10000);
        checkBoxMemorySize.click();
        return this;
    }

    public boolean verifyMemorySizeFilter(String firstMemorySize, String secondMemorySize) throws Exception {
        ElementsCollection allNames = $$x("//div[@class='product-card__name']//span");
        for (int i = 0; i < allNames.size(); i++) {
            String memorySizeProduct = allNames.get(i).getText();
            if (!( memorySizeProduct.contains(firstMemorySize) || memorySizeProduct.contains(secondMemorySize) )) {
                throw new Exception("Selected products do not fall within the range memory size which you selected");
            }
        }
        return true;
    }

    public FilterFragment setBodyMaterialFilter(String BodyMaterial) throws InterruptedException {
        SelenideElement checkBoxMemorySize = $x("//span[@class='el-checkbox__label']/a[text()='" + BodyMaterial + "']").waitUntil(appear, 10000);
        checkBoxMemorySize.click();
        return this;
    }

    public boolean verifyBodyMaterialFilter(String BodyMaterial) throws Exception {
        ElementsCollection allNames = $$x("//div[@class='product-card__name']//span");
        for (int i = 0; i < allNames.size(); i++) {
            allNames.get(i).waitUntil(appear, 10000).hover();
            SelenideElement descriptionProduct = $x("//span[contains(text(), '" + BodyMaterial + "')]");
            if (!( descriptionProduct.getText().contains(BodyMaterial) )) {
                throw new Exception("The selected products are not made from material which you selected");
            }
        }
        return true;
    }
}
