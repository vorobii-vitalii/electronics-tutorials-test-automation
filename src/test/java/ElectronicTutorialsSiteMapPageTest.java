import dto.Link;
import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pages.ElectronicTutorialsSiteMapPage;
import utils.TestUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumExtension.class)
public class ElectronicTutorialsSiteMapPageTest {

    private static final String EXAMPLE_URL = "https://example.com";
    private static final String HOME_PAGE_URL = "https://www.electronics-tutorials.ws";

    @Test
    void testImmutabilityOfHomePageUrlInput(HtmlUnitDriver driver) {

        ElectronicTutorialsSiteMapPage siteMapPage = new ElectronicTutorialsSiteMapPage(driver);

        siteMapPage.setTutorialsUrlLinkInputText(EXAMPLE_URL);

        String actualUrl = siteMapPage.getTutorialsUrlLink();

        assertTrue(actualUrl.contains(HOME_PAGE_URL));
    }

    @Test
    void testSiteMapBoxesArticlesAreSorted(HtmlUnitDriver driver) {

        ElectronicTutorialsSiteMapPage siteMapPage = new ElectronicTutorialsSiteMapPage(driver);

        Map<String, List<Link>> siteMapBoxesArticles = siteMapPage.getSiteMapBoxesArticles();

        siteMapBoxesArticles
                .values()
                .stream()
                .map(links -> links.stream().map(Link::getText).collect(Collectors.toList()))
                .forEach(TestUtils::verifySorted);
    }

    @Test
    void testSiteMapBoxesArticlesBelongToSameCategoryPath(HtmlUnitDriver driver) {

        ElectronicTutorialsSiteMapPage siteMapPage = new ElectronicTutorialsSiteMapPage(driver);

        Map<String, List<Link>> siteMapBoxesArticles = siteMapPage.getSiteMapBoxesArticles();

        siteMapBoxesArticles.values()
                .forEach(articles -> {
                    Set<String> uniqueLinksWithRemovedLastNestedPaths = articles.stream()
                            .map(Link::getHref)
                            .map(this::removeLastNestedPathFromUrl)
                            .collect(Collectors.toSet());

                    assertTrue(uniqueLinksWithRemovedLastNestedPaths.size() <= 1);
                });
    }

    private String removeLastNestedPathFromUrl(String inputUrl) {
        int indexOfSlash = inputUrl.lastIndexOf("/");

        return inputUrl.substring(0, indexOfSlash);
    }

}
