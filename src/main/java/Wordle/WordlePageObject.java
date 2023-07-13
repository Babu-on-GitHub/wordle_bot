package Wordle;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import solver.CharacterValidity;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;

public class WordlePageObject {

    private WebDriver driver;

    private final String pageURL = "https://www.nytimes.com/games/wordle/index.html";

    public WordlePageObject() {
        this.driver = new ChromeDriver();
    }

    public void initPage(){
        driver.get(pageURL);
    }

    public boolean isStartPage(){
        WebElement mainContainer = driver.findElement(By.xpath("/html/body/div[1]/div"));
        List<WebElement> welcomeContent = mainContainer.findElements(By.className("Welcome-module_contentWelcome__TL17B"));
        return ! welcomeContent.isEmpty();
    }

    public boolean isGamePage(){
        WebElement mainContainer = driver.findElement(By.xpath("/html/body/div[1]/div"));
        List<WebElement> gameContent = mainContainer.findElements(By.className("App-module_gameContainer__K_CBh"));
        return ! gameContent.isEmpty();
    }

    public void clickStart(){
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div[3]/button[2]")));
        new Actions(driver)
                .moveToElement(button)
                //.pause(Duration.ofMillis(1000))
                .click().perform();
    }

    public void closeCookiesPopUp(){
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("pz-gdpr-btn-closex")));
        new Actions(driver)
                .moveToElement(button)
                //.pause(Duration.ofMillis(1000))
                .click().perform();
    }


    public void closeHowToPlayPopUp(){
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/dialog/div/button")));
        new Actions(driver)
                .moveToElement(button)
                //.pause(Duration.ofMillis(1000))
                .click().perform();
    }

    public void typeWord(String word){
        Actions action = new Actions(driver);
        action.pause(Duration.ofSeconds(1));
        for(char c: word.toCharArray()){
            action.keyDown(Character.toString(c));
            action.perform();
        }
        action.keyDown(Keys.RETURN);
        action.perform();
    }

    public CharacterValidity[] getResults(int rowNumber){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> rows = driver.findElements(By.className("Row-module_row__pwpBq"));
        WebElement row = rows.get(rowNumber);
        List<WebElement> cells = row.findElements(By.className("Tile-module_tile__UWEHN"));
        CharacterValidity[] validities = new CharacterValidity[5];
        for(int i=0; i<5;i++){
            String dataState = cells.get(i).getAttribute("data-state");
            switch (dataState){
                case "correct":
                    validities[i] = CharacterValidity.GREEN;
                    break;
                case "present":
                    validities[i] = CharacterValidity.YELLOW;
                    break;
                default:
                    validities[i] = CharacterValidity.GREY;
            }
        }
        return validities;
    }


}
