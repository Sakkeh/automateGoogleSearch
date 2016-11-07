package uk.co.compendiumdev.letsautomate.googlesearch;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Alan on 07/11/2016.
 */
public class PageRankTest {

    @Test
    public void pageRankTool(){
        WebDriver driver = new ChromeDriver();

        driver.get("https://google.co.uk");

        String searchTerm="prolog programmer"; // "selenium training";
        String matchInUrl="compendiumdev.co.uk";

        // search page

        WebDriverWait wait = new WebDriverWait(driver, 10);

        String cssOfInputField = "input[name='q']";

        WebElement inputFieldQ = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfInputField)));

        inputFieldQ.sendKeys(searchTerm);


        String cssOfSearchButton = "button[name='btnG']";

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfSearchButton)));
        searchButton.click();

        // search results page

        int pageNumber=0;
        boolean foundMatch=false;
        String foundMatchingUrl="";

        while(!foundMatch){

            pageNumber++;

            String cssOfNextPageButton = "a#pnnext";

            WebElement nextLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfNextPageButton)));

            // handle any page load div
            try{

                String flyrCssSelector = "div#flyr";
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(flyrCssSelector)));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(flyrCssSelector)));

            }catch(Exception e){
                // ignore any exceptions
            }


            List<WebElement> resultEntries = driver.findElements(By.cssSelector("h3.r > a"));

            for(WebElement element : resultEntries){
                String href = element.getAttribute("href");
                String title = element.getText();

                System.out.println(href);
                System.out.println(title);

                if(href.contains(matchInUrl)){
                    System.out.println("****FOUND MATCH*** on page " + pageNumber);
                    foundMatchingUrl = href;
                    foundMatch=true;
                }

            }



            nextLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfNextPageButton)));
            nextLink.click();
        }


        System.out.println(String.format("Found match on Page Number %d - %s", pageNumber, foundMatchingUrl));

        driver.close();
        driver.quit();
    }
}
