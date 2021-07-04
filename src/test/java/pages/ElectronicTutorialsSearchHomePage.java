package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ElectronicTutorialsSearchHomePage extends AbstractPageObject {

    @FindBy(xpath = "//*[@id=\"s\"]")
    private WebElement searchInput;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div/div/form/button")
    private WebElement submitButton;

    public ElectronicTutorialsSearchHomePage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    public void fillSearchInput(String text) {
        searchInput.sendKeys(text);
    }

    public void pressSubmitButton() {
        submitButton.click();
    }

}
