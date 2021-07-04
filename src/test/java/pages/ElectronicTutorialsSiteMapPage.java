package pages;

import dto.Link;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ElectronicTutorialsSiteMapPage extends AbstractPageObject {

    private static final String SITEMAP_URL = "https://www.electronics-tutorials.ws/sitemap";

    @FindBy(xpath = "/html/body/div[7]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/form/input")
    private WebElement tutorialsUrlLinkInput;

    @FindBys(@FindBy(className = "sitemap-box"))
    private List<WebElement> siteMapBoxes;

    public ElectronicTutorialsSiteMapPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.get(SITEMAP_URL);
        PageFactory.initElements(webDriver, this);
    }

    public String getTutorialsUrlLink() {
        return tutorialsUrlLinkInput.getAttribute("value");
    }

    public void setTutorialsUrlLinkInputText(String text) {
        tutorialsUrlLinkInput.sendKeys(text);
    }

    public Map<String, List<Link>> getSiteMapBoxesArticles() {
        return siteMapBoxes.stream()
                .map(siteMapBox -> {
                    String siteMapBoxHeader = siteMapBox.findElement(By.cssSelector("h2 > a")).getText();

                    List<Link> links = siteMapBox
                            .findElements(By.cssSelector(".links > a"))
                            .stream()
                            .map(linkElement ->
                                    Link.builder()
                                            .text(linkElement.getText())
                                            .href(linkElement.getAttribute("href"))
                                            .build())
                            .collect(Collectors.toList());

                    return Pair.of(siteMapBoxHeader, links);
                })
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
    }

}
