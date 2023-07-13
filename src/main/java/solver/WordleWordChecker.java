package solver;

import Wordle.WordlePageObject;

public class WordleWordChecker implements WordChecker{

    WordlePageObject page;
    int currentGuessNumber;

    boolean setupDone;

    public WordleWordChecker(){
        currentGuessNumber=0;
        page = new WordlePageObject();
        page.closeCookiesPopUp();
        page.clickStart();
        page.isGamePage();
        page.closeHowToPlayPopUp();
    }

    @Override
    public CharacterValidity[] checkWord(char[] word) {
        page.typeWord(new String(word));
        return page.getResults(currentGuessNumber++);
    }
}
