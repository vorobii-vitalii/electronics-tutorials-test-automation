import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ElectronicTutorialsContactUsPage;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumExtension.class)
public class ElectronicTutorialsContactUsPageTest {

    private static final String CONTACT_US_PAGE_URL = "https://www.electronics-tutorials.ws/contact";
    private static final String HOME_PAGE_URL = "https://www.electronics-tutorials.ws/";

    private static final String EMAIL = "selenium@gmail.com";
    private static final String WRONG_EMAIL = "selenium";
    private static final String MESSAGE = "selenium auto tests";

    @Test
    void testSubmit_givenWholeUnfilledForm(ChromeDriver driver) {

        ElectronicTutorialsContactUsPage contactUsPage = new ElectronicTutorialsContactUsPage(driver);

        contactUsPage.pressSubmit();

        String currentURL = contactUsPage.getCurrentURL();

        assertEquals(CONTACT_US_PAGE_URL, currentURL);
    }

    @Test
    void testSubmit_givenUnfilledEmail(ChromeDriver driver) {

        ElectronicTutorialsContactUsPage contactUsPage = new ElectronicTutorialsContactUsPage(driver);

        contactUsPage.fillMessage(MESSAGE);

        contactUsPage.pressSubmit();

        String currentURL = contactUsPage.getCurrentURL();

        assertEquals(CONTACT_US_PAGE_URL, currentURL);
    }

    @Test
    void testSubmit_givenWronglySetEmail(ChromeDriver driver) {

        ElectronicTutorialsContactUsPage contactUsPage = new ElectronicTutorialsContactUsPage(driver);

        contactUsPage.fillEmail(WRONG_EMAIL);
        contactUsPage.fillMessage(MESSAGE);

        contactUsPage.pressSubmit();

        String currentURL = contactUsPage.getCurrentURL();

        assertEquals(CONTACT_US_PAGE_URL, currentURL);
    }


    @Test
    void testSubmit_givenCorrectlySubmittedForm(ChromeDriver driver) {

        ElectronicTutorialsContactUsPage contactUsPage = new ElectronicTutorialsContactUsPage(driver);

        contactUsPage.fillEmail(EMAIL);
        contactUsPage.fillMessage(MESSAGE);

        contactUsPage.pressSubmit();

        assertNotEquals(CONTACT_US_PAGE_URL, contactUsPage.getCurrentURL());

        String contactFormSubmissionContent = contactUsPage.getContactFormSubmissionContent();

        assertTrue(contactFormSubmissionContent.contains(EMAIL));
        assertTrue(contactFormSubmissionContent.contains(MESSAGE));
    }

    @Test
    void testPressOnHeaderLogo_redirectToHomePage(ChromeDriver driver) {

        ElectronicTutorialsContactUsPage contactUsPage = new ElectronicTutorialsContactUsPage(driver);

        contactUsPage.clickOnHeaderLogo();

        assertEquals(HOME_PAGE_URL, contactUsPage.getCurrentURL());
    }

}
