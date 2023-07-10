package solver;

/**
 * Component that provides functionality for validating if words are valid based on current knowledge
 */
public interface WordValidator {

    /**
     * Method takes a word as a char array of length 5 and returns if the word is a valid guess based on current knowledge
     * @param word word to be checked
     * @return WordValidity representing if the word is valid or invalid based on current knowledge
     */
    WordValidity validate(char[] word);

}
