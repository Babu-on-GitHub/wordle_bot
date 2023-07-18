package solver;

/**
 * A component meant to provide functionality for checking the validities of each letter in a word
 */
public interface WordChecker {

    /**
     * Method that gets a word as input and returns an array of CharacterValidity representing the validities of each letter in order
     * The validities represent whether the character is included in the correct word and if it is on the correct position
     * @param word char array of length 5 representing the word to check
     * @return array of CharacterValidity representing the validities for each letter in the checked word
     */
    CharacterValidity[] checkWord(char[] word);

    /**
     * Method used to do any setup the checker might need
     * This method has to be called before using any other methods of the WordChecker
     * Added primarily for implementations that interact with web pages
     */
    void init();

}
