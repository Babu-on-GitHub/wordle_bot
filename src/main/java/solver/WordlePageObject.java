package solver;

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

/**
 * Component used to interact with the Wordle official site
 */
public class WordlePageObject {

    private WebDriver driver;

    private final String pageURL = "https://www.nytimes.com/games/wordle/index.html";

    public WordlePageObject() {
        this.driver = new ChromeDriver();

    }

    /**
     * Initializes the page opening the browser
     */
    public void init(){
        driver.get(pageURL);
    }


    /**
     * Checks whether the start page is displayed
     * @return true if start page is displayed false otherwise
     */
    public boolean isStartPage(){
        WebElement mainContainer = driver.findElement(By.xpath("/html/body/div[1]/div"));
        List<WebElement> welcomeContent = mainContainer.findElements(By.className("Welcome-module_contentWelcome__TL17B"));
        return ! welcomeContent.isEmpty();
    }

    /**
     * Chechks whether the page with the game is displayed
     * @return true if game page is displayed, false otherwise
     */
    public boolean isGamePage(){
        WebElement mainContainer = driver.findElement(By.xpath("/html/body/div[1]/div"));
        List<WebElement> gameContent = mainContainer.findElements(By.className("App-module_gameContainer__K_CBh"));
        return ! gameContent.isEmpty();
    }

    /**
     * Method that presses the start game button on the start page
     */
    public void clickStart(){
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div[3]/button[2]")));
        button.click();
    }

    /**
     * Method that closes the gdpr cookies popup
     */
    public void closeCookiesPopUp(){
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("pz-gdpr-btn-closex")));
        button.click();
    }

    /**
     * Method that closes the 'how to play' popup
     */
    public void closeHowToPlayPopUp(){
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/dialog/div/button")));
        button.click();
    }

    /**
     * Method that types a word and submits it in the game page
     * @param word word to be typed and submited
     */
    public void typeWord(String word){
        Actions action = new Actions(driver);
        action.pause(Duration.ofSeconds(1));
        for(char c: word.toCharArray()){
            action.keyDown(Character.toString(c));
            //action.perform();
        }
        action.keyDown(Keys.RETURN);
        action.perform();
    }

    /**
     * Method that returns the character validities of a row
     * @param rowNumber the number of the row to check
     * @return the character validities of the row
     */
    public CharacterValidity[] getResultsForRow(int rowNumber){
        try {
            Thread.sleep(1500);
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
