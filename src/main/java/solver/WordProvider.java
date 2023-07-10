package solver;

import java.util.Iterator;

/**
 * Component that provides the possible words with an interator interface
 */
public interface WordProvider extends Iterator {

    /**
     * Gives the next word in the list of all possible guessable words
     * @return next possible word as char array of length 5
     */
    char[] next();

    /**
     * Tells if there is a next word in the list of all possible guessable words
     * @return true if there is another word in the list, false otherwise
     */
    boolean hasNext();

}
