package onliner.test.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.BaseForm;
import webdriver.elements.ElementCollection;
import webdriver.elements.Label;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Форма страницы результатов поиска. Идентификация по блоку результатов поиска с id = "schema-products"
 *
 * enum, паттерн композиция
 */

public class OnlinerProductsForm extends BaseForm {

    //List<WebElement> productLinks = browser.getDriver().findElements(By.xpath("//div[@class='schema-product__title']/a"));
    Label processing = new Label(By.className("schema-products schema-products_processing"));

    public OnlinerProductsForm() {
        super(By.id("schema-products"), "Product list");
    }

    /**
     * Метод проверки всех товаров в поисковой выдаче. Для этого заходим на каждую страницу, т. е. открывам форму OnlinerProductForm в цикле
     * @param brand
     * @param price
     * @param year
     * @param minDiagonal
     * @param maxDiagonal
     */

    public void checkAllProducts(String brand, String price, String year, String minDiagonal, String maxDiagonal) {
        ElementCollection productLinks = new ElementCollection(By.xpath("//div[@class='schema-product__title']/a"));
        int collectionSize = productLinks.getCollectionSize();
        info(">>>Search results:");
        for (int i=0; i<collectionSize; i++){
            WebElement link = productLinks.getElement(i);
            info(link.getText() + " " + link.getAttribute("href") + " ");
        }

        for (int i=0; i<collectionSize; i++){
            WebElement link = productLinks.getElement(i);
            String productName = link.getText();
            link.click();
            browser.waitForPageToLoad();
            info("--- Analize product #" + (i+1) + " ---");
            OnlinerProductForm productForm = new OnlinerProductForm("//h2[contains(text(),'"+ productName +"')]", "Product "+ productName);
            productForm.checkProduct(brand);
            productForm.checkPrice(price);
            productForm.checkYear(year);
            productForm.checkDiagonal(minDiagonal, maxDiagonal);
            browser.goBack();
        }
    }
}
