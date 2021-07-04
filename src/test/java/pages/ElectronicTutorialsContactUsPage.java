package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ElectronicTutorialsContactUsPage extends AbstractPageObject {

    private static final String CONTACT_PAGE_URL = "https://www.electronics-tutorials.ws/contact";

    @FindBy(xpath = "//*[@id=\"g5-email\"]")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id=\"contact-form-comment-g5-message\"]")
    private WebElement messageField;

    @FindBy(xpath = "/html/body/div[7]/div[2]/div[2]/div/div[2]/div[2]/div/div/form/p/button")
    private WebElement summitButton;

    @FindBy(xpath = "/html/body/div[7]/div[2]/div[2]/div/div[2]/div[2]/div/div/blockquote")
    private WebElement contactFormSubmission;

    @FindBy(xpath = "/html/body/div[6]/div[1]/div[2]/a")
    private WebElement headerLogoLink;

    public ElectronicTutorialsContactUsPage(WebDriver webDriver) {
        super(webDriver);

        webDriver.get(CONTACT_PAGE_URL);

        PageFactory.initElements(webDriver, this);
    }

    public void fillEmail(String email) {
        emailField.sendKeys(email);
    }

    public void fillMessage(String message) {
        messageField.sendKeys(message);
    }

    public void pressSubmit() {
        summitButton.click();
    }

    public String getContactFormSubmissionContent() {
        return contactFormSubmission.getText();
    }

    public void clickOnHeaderLogo() {
        headerLogoLink.click();
    }

}
