package webdriver.elements;

import org.openqa.selenium.By;

/**
 * Created by mikola on 11.07.2016.
 */
public class CheckBox extends BaseElement {

    @Override
    protected String getElementType() {
        return getLoc("loc.checkBox");
    }

    public CheckBox(By locator) {
        super(locator);
    }

    public CheckBox(final By locator, final String name) {
        super(locator, name);
    }

    public CheckBox(String string, String name) {
        super(string, name);
    }


}
