package uk.co.compendiumdev.letsautomate.googlesearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 08/11/2016.
 */
public class GoogleSearchResults {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public GoogleSearchResults(WebDriver driver) {
        this.driver = driver;

        wait = new WebDriverWait(driver, 5);
    }

    public List<TitledUrl> returnUrls() {
        List<WebElement> resultEntries = driver.findElements(By.cssSelector("h3.r > a"));
        List<TitledUrl> resultUrls = new ArrayList<>();

        for(WebElement element : resultEntries){
            String href = element.getAttribute("href");
            String title = element.getText();

            TitledUrl titledUrl = new TitledUrl(href, title);
            resultUrls.add(titledUrl);

        }

        return resultUrls;
    }

    public void waitUntilAvailable() {
        String cssOfFooter = "div#navcnt";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssOfFooter)));

        // handle any page load div
        try{

            String flyrCssSelector = "div#flyr";
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(flyrCssSelector)));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(flyrCssSelector)));

        }catch(Exception e){
            // ignore any exceptions
        }
    }

    public boolean getMoreResults() {

        String cssOfNextPageButton = "a#pnnext";

        boolean haveMoreResults = false;

        try{
            WebElement nextLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfNextPageButton)));
            nextLink.click();
            haveMoreResults=true;}
        catch(Exception e){
            // ignore
        }

        return haveMoreResults;
    }
}
