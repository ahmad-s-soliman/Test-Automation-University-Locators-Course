package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void initWebDriver(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void searchDuckDuckGo(){
        //load page
        driver.get("https://duckduckgo.com/");

        //enter search phrase
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("giant panda");


        //click search button
        WebElement searchButton = driver.findElement(By.cssSelector("[class*='searchbox_searchButton__F5Bwq']"));
        searchButton.click();

        //wait for results to appear
        wait.until(ExpectedConditions.titleContains("giant panda"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='EKtkFWMYpwzMKOYr0GYm']")));


        //make sure each result contains the word panda
        List<WebElement> resultLinks = driver.findElements(By.cssSelector("[class*='EKtkFWMYpwzMKOYr0GYm']"));
        for(WebElement link: resultLinks){
            assertTrue(link.getText().matches("(?i).*panda.*"));
        }
    }

    @AfterEach
    public void quitWebDriver(){
        driver.quit();
    }
}
