package solver;

import java.util.Set;
import java.util.TreeSet;

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

    /**
     * Mark a character as invalid
     * Words containing invalid characters are going to be checked as invalid
     * @param c character to be marked as invalid
     */
    void markCharacterAsInvalid(char c);

    /**
     * Mark the correct character for a given position
     * Words that do not have the character on the marked position are going to be invalidated
     * @param c character to be marked
     * @param p position to be marked
     */
    void markCorrectCharacterForPosition(char c, int p);

    /**
     * Mark a position invalid for a character
     * @param c character with invalid position
     * @param p position to be marked
     */
    void markInvalidPositionForValidCharacter(char c, int p);

    /**
     * Mark the number of times a character appears in the word
     * @param c character to mark
     * @param f the number of times it appears in word
     */
    void markCharacterFrequency(char c, int f);



}
