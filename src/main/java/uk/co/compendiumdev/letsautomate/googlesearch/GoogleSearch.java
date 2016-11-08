package uk.co.compendiumdev.letsautomate.googlesearch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Alan on 08/11/2016.
 */
public class GoogleSearch {
    private final String domain;
    private final WebDriver driver;
    private String theSearchURL;
    private GoogleSearchResults googleSearchResults;

    public GoogleSearch(String siteDomain) {
        this.driver = new ChromeDriver();;
        this.domain = siteDomain;

        //driver.get(siteDomain);
    }

    public void searchFor(String searchTerm, String matchInUrl) {

        String searchForString = "";

        try {
            searchForString = URLEncoder.encode(searchTerm, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        this.theSearchURL = this.domain + "/search?q=" + searchForString;

        driver.get(this.theSearchURL);

        return;

/*
        WebDriverWait wait = new WebDriverWait(driver, 5);

        String cssOfInputField = "input[name='q']";

        WebElement inputFieldQ = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfInputField)));

        inputFieldQ.sendKeys(searchTerm);


        String cssOfSearchButton = "button[name='btnG']";

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfSearchButton)));
        searchButton.click();
*/
    }

    public GoogleSearchResults results() {
        if(googleSearchResults==null){
            googleSearchResults = new GoogleSearchResults(driver, 1, theSearchURL);
        }
        return googleSearchResults;
    }

    public void endSearch() {
        driver.close();
        driver.quit();
    }
}
