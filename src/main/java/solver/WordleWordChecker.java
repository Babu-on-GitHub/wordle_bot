package solver;

/**
 * Implementation of WordChecker that uses Selenium to interact with the official Wordle website to check words
 */
public class WordleWordChecker implements WordChecker{

    WordlePageObject page;
    int currentGuessNumber;

    boolean setupDone;

    public WordleWordChecker(){
        currentGuessNumber=0;
        page = new WordlePageObject();
    }

    /**
     * Method that gets a word as input and returns an array of CharacterValidity representing the validities of each letter in order
     * The validities represent whether the character is included in the correct word and if it is on the correct position
     * The method uses the WordlePageObject to interact with the official Wordle website and check the word
     * @param word char array of length 5 representing the word to check
     * @return array of CharacterValidity representing the validities for each letter in the checked word
     */
    @Override
    public CharacterValidity[] checkWord(char[] word) {
        if(word == null || word.length != 5){
            throw new IllegalArgumentException();
        }
        page.typeWord(new String(word));
        return page.getResultsForRow(currentGuessNumber++);
    }

    /**
     * Method that does the setup for using the checkWord method
     * Opens up the browser and navigates to the game page
     */
    @Override
    public void init(){
        page.init();
//        page.closeUpdatePopUp();
//        page.closeCookiesPopUp();
        page.isStartPage();
        page.clickStart();
        page.isGamePage();
        page.closeHowToPlayPopUp();
    }
}
