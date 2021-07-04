package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ElectronicTutorialsHomePage extends AbstractPageObject {
    private static final String HOME_PAGE_URL = "https://www.electronics-tutorials.ws/";
    private static final By CATEGORY_TITLE_FILTER_CRITERIA = By.tagName("h2");

    @FindBys(@FindBy(how = How.CLASS_NAME, using = "top-cat"))
    private List<WebElement> categories;

    @FindBy(xpath = "/html/body/div[7]/div[2]/div[2]/div/div/div[2]/div[3]/a")
    private WebElement showLessButton;

    @FindBy(xpath = "/html/body/div[6]/div[1]/div[3]/div")
    private WebElement searchButton;

    public ElectronicTutorialsHomePage(WebDriver driver) {
        super(driver);
        driver.get(HOME_PAGE_URL);

        PageFactory.initElements(driver, this);
    }

    private String getCategoryName(WebElement category) {
        return category.findElement(CATEGORY_TITLE_FILTER_CRITERIA).getText();
    }

    public List<String> getCategoriesNames() {
        return categories.stream()
                .map(this::getCategoryName)
                .collect(Collectors.toList());
    }

    public List<Integer> getCategoriesTutorialsCount() {
        return categories.stream()
                .map(this::getCategoryTutorialsCount)
                .collect(Collectors.toList());
    }

    public List<Integer> getCategoriesTutorialsHoveredCount() {
        return categories.stream()
                .map(this::getCategoryTutorialsHoveredCount)
                .collect(Collectors.toList());
    }

    public void clickOnShowMoreLessButton() {
        new Actions(driver).moveToElement(showLessButton).click().build().perform();
    }

    public ElectronicTutorialsSearchHomePage clickSearch() {
        searchButton.click();

        return new ElectronicTutorialsSearchHomePage(driver);
    }

    private Integer getCategoryTutorialsHoveredCount(WebElement categoryElement) {

        new Actions(driver).moveToElement(categoryElement, 0, 0).build().perform();

        String countText = categoryElement.findElement(By.className("counts")).getText();

        return Integer.parseInt(countText);
    }

    private Integer getCategoryTutorialsCount(WebElement categoryElement) {
        String countText = categoryElement.findElement(By.className("counts")).getText();

        return Integer.parseInt(countText);
    }

}
