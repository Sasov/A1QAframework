package onliner.test.forms;

import org.apache.tools.ant.taskdefs.Javadoc;
import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;

import javax.swing.text.html.HTML;

/**
 * Форма карточки отдельного товара. Идентифицируем по наличию названия товара (оно берется из содержания ссылки в поисковой выдаче) в заголовке H2
 */

public class OnlinerProductForm extends BaseForm {

    Label productOnPage = new Label(By.className("catalog-masthead__title"));
    Label priceRange = new Label(By.xpath("//a[@class='b-offers-desc__info-price-value b-offers-desc__info-price-value_primary']"));
    Label productYear = new Label(By.xpath("//td[contains(text(),'Дата выхода на рынок')]/following-sibling::td/span"));
    Label productDiagonal = new Label(By.xpath("//td[contains(text(),'Диагональ экрана')]/following-sibling::td/span"));


    public OnlinerProductForm(String formlocator, String formTitle) {
        super(By.xpath(formlocator), formTitle);
    }

    public void checkYear(String year){
        String displayedYear = productYear.getText().substring(0,4);
        int yearInt = Integer.parseInt(year);
        int displayedYearInt = Integer.parseInt(displayedYear);
        boolean comparisonResult = displayedYearInt >= yearInt;
        doAssert(comparisonResult,"Date is in a predetermined range","Date is not within a predetermined range");
    }

    public void checkDiagonal(String minDiagonal, String maxDiagonal){
        String productDiagonalString = productDiagonal.getText();
        String productDiagonalStringTrim = productDiagonalString.replace("\"","");
        String minDiagonalTrim = minDiagonal.replace("\"","");
        String maxDiagonalTrim = maxDiagonal.replace("\"","");
        int productDiagonalStringInt = Integer.parseInt(productDiagonalStringTrim);
        int minDiagonalInt = Integer.parseInt(minDiagonalTrim);
        int maxDiagonalInt = Integer.parseInt(maxDiagonalTrim);
        boolean comparisonResult = (productDiagonalStringInt <= maxDiagonalInt) && (productDiagonalStringInt >= minDiagonalInt);
        doAssert(comparisonResult, "Diagonal is in a predetermined range", "Diagonal is not within a predetermined range");
    }

    public void checkProduct(String brand){
        String displayedProduct = productOnPage.getText().toLowerCase();
        boolean isContainsProductName = displayedProduct.contains(brand);
        doAssert(isContainsProductName, "Product name complies with the brand", "Product name does not complies with the brand");
    }

    public void checkPrice(String price){
        String displayedPriceString = priceRange.getText();
        int commaPosition = displayedPriceString.indexOf(",");
        String minDisplayedPrice = displayedPriceString.substring(0,commaPosition);
        int priceInt = Integer.parseInt(price);
        int minDisplayedPriceInt = Integer.parseInt(minDisplayedPrice);
        boolean comparisonResult = minDisplayedPriceInt <= priceInt;
        doAssert(comparisonResult,"Price is in a predetermined range","Price is not within a predetermined range");
    }


}
