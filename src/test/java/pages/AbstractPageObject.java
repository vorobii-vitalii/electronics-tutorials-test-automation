package pages;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPageObject {
    protected final WebDriver driver;

    public AbstractPageObject(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

}
