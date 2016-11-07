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
public class ReconTest {

    @Test
    public void doesWebDriverWork(){
        WebDriver driver = new ChromeDriver();

        driver.get("https://google.co.uk");

        // search page

        WebDriverWait wait = new WebDriverWait(driver, 10);

        String cssOfInputField = "input[name='q']";

        WebElement inputFieldQ = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfInputField)));

        inputFieldQ.sendKeys("test");


        String cssOfSearchButton = "button[name='btnG']";

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfSearchButton)));
        searchButton.click();

        // search results page

        String cssOfNextPageButton = "a#pnnext";

        WebElement nextLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfNextPageButton)));

        List<WebElement> resultEntries = driver.findElements(By.cssSelector("h3.r > a"));

        for(WebElement element : resultEntries){
            System.out.println(element.getAttribute("href"));
            System.out.println(element.getText());
        }

        nextLink.click();

        System.out.println("wait here");
        driver.close();
        driver.quit();
    }
}
