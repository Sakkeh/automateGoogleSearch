package uk.co.compendiumdev.letsautomate.googlesearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Alan on 08/11/2016.
 */
public class GoogleSearch {
    private final String domain;
    private final WebDriver driver;

    public GoogleSearch(String siteDomain) {
        this.driver = new ChromeDriver();;
        this.domain = siteDomain;

        driver.get("https://google.com");
    }

    public void searchFor(String searchTerm, String matchInUrl) {

        WebDriverWait wait = new WebDriverWait(driver, 5);

        String cssOfInputField = "input[name='q']";

        WebElement inputFieldQ = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfInputField)));

        inputFieldQ.sendKeys(searchTerm);


        String cssOfSearchButton = "button[name='btnG']";

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfSearchButton)));
        searchButton.click();
    }

    public GoogleSearchResults results() {
        return new GoogleSearchResults(driver);
    }

    public void endSearch() {
        driver.close();
        driver.quit();
    }
}
