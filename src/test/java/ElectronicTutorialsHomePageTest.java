import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pages.ElectronicTutorialsHomePage;
import pages.ElectronicTutorialsSearchHomePage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.TestUtils.verifySorted;

@ExtendWith(SeleniumExtension.class)
public class ElectronicTutorialsHomePageTest {

    private static final String TRANSISTORS = "Transistors";

    @Test
    void testCategoryListOnHomePageAreSortedInAscendingOrder(HtmlUnitDriver driver) {
        ElectronicTutorialsHomePage homePage = new ElectronicTutorialsHomePage(driver);

        List<String> categoriesNames = homePage.getCategoriesNames();

        verifySorted(categoriesNames);
    }

    @Test
    void testCategoryTutorialsCountMatches(ChromeDriver driver) {
        ElectronicTutorialsHomePage homePage = new ElectronicTutorialsHomePage(driver);

        List<Integer> categoriesTutorialsCount = homePage.getCategoriesTutorialsCount();
        List<Integer> categoriesTutorialsHoveredCount = homePage.getCategoriesTutorialsHoveredCount();

        assertEquals(categoriesTutorialsCount, categoriesTutorialsHoveredCount);
    }

    @Test
    void testClickOnShowMoreLessButton(HtmlUnitDriver driver) {
        ElectronicTutorialsHomePage homePage = new ElectronicTutorialsHomePage(driver);

        List<String> initialCategoriesNames = homePage.getCategoriesNames();

        homePage.clickOnShowMoreLessButton();

        List<String> currentCategoriesNames = homePage.getCategoriesNames();

        assertTrue(currentCategoriesNames.size() <= initialCategoriesNames.size());

        homePage.clickOnShowMoreLessButton();

        List<String> categoriesNamesAfterShowMore = homePage.getCategoriesNames();

        assertEquals(initialCategoriesNames, categoriesNamesAfterShowMore);
    }

    @Test
    void testSearch(HtmlUnitDriver driver) {
        ElectronicTutorialsHomePage homePage = new ElectronicTutorialsHomePage(driver);

        ElectronicTutorialsSearchHomePage searchHomePage = homePage.clickSearch();

        searchHomePage.fillSearchInput(TRANSISTORS);

        searchHomePage.pressSubmitButton();

        String expectedCurrentUrl = String.format("https://www.electronics-tutorials.ws/?s=%s", TRANSISTORS);

        assertEquals(expectedCurrentUrl, searchHomePage.getCurrentURL());
    }

}
