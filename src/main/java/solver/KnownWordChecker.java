package solver;

import java.util.Arrays;

/**
 * Implementation of WordChecker that uses a given known word to check other guesses
 */
public class KnownWordChecker implements WordChecker{

    char[] correctWord;

    /**
     * Constructor for the WordChecker
     * @param correctWord The known word used for checking words
     */
    public KnownWordChecker(char[] correctWord) {
        this.correctWord = correctWord;
    }

    /**
     * Does nothing for the KnownWordChecker
     * Useful for other implementations of the WordChecker interface
     */
    @Override
    public void init(){

    }

    /**
     * Method that gets a word as input and returns an array of CharacterValidity representing the validities of each letter in order
     * The validities represent whether the character is included in the correct word and if it is on the correct position
     * In the KnownWordChecker the method compares the word given as a parameter to a previously given correct word
     * The correct word is given in the constructor of the KnownWordChecker
     * @param word char array of length 5 representing the word to check
     * @return array of CharacterValidity representing the validities for each letter in the checked word
     */
    @Override
    public CharacterValidity[] checkWord(char[] word) {
        if(word == null || word.length != 5){
            throw new IllegalArgumentException();
        }

        CharacterValidity[] result = new CharacterValidity[5];
        Arrays.fill(result,CharacterValidity.GREY);

        char[] correctCopy = Arrays.copyOf(correctWord,5);

        for(int i = 0; i < 5; i++){
            if(word[i] == correctCopy[i]) {
                result[i] = CharacterValidity.GREEN;
                correctCopy[i] = 0;
            }
        }

        for(int i = 0; i < 5; i++){
            if(result[i] == CharacterValidity.GREEN) continue;
            for(int j = 0; j < 5; j++){
                if(word[i] == correctCopy[j]){
                    result[i] = CharacterValidity.YELLOW;
                    correctCopy[j] = 0;
                    break;
                }
            }
        }


        return result;
    }
}
