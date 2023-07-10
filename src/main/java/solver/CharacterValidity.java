package solver;

/**
 * Enum used to represent the validity of the character in the word, same collors used by the Wordle game
 * Grey = letter is not included in word
 * Yellow = letter is included in word, but the position is wrong
 * Green = letter is included in word and the position is correct
 */
public enum CharacterValidity {
    GREY,
    YELLOW,
    GREEN
}
