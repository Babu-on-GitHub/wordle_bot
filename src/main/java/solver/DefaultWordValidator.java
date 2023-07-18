package solver;

import java.util.*;

/**
 * Default implementation for WordValidator
 * DefaultWordValidator can handle validating only one word at a time.
 * For validating multiple words separate instances of the class must be created
 */
public class DefaultWordValidator implements WordValidator{

    private Set<Character> invalidCharacters;
    private Map<Integer, Character> correctCharactersForPositions;
    private Map<Character, Set<Integer>> incorrectPositionsForCharacters;
    private Map<Character, Integer> characterFrequencies;

    /**
     * Default constructor
     * Initializes the invalidCharacters, correctCharactersForPositions, incorrectPositionsForCharacters fields with empty instantiations
     */
    public DefaultWordValidator(){
        this.invalidCharacters = new HashSet<>();
        this.correctCharactersForPositions = new HashMap<>();
        this.incorrectPositionsForCharacters = new HashMap<>();
        this.characterFrequencies = new HashMap<>();
    }

    /**
     * Constructor with parameters
     * @param invalidCharacters Set of invalid characters
     * @param correctPositionsForCharacters Map of positions and correct characters for them
     * @param incorrectPositionsForCharacters Map of characters and the incorrect positions
     */
    public DefaultWordValidator(Set<Character> invalidCharacters,
                                Map<Integer, Character> correctPositionsForCharacters,
                                Map<Character, Set<Integer>> incorrectPositionsForCharacters,
                                Map<Character, Integer> characterFrequencies) {
        this.invalidCharacters = invalidCharacters;
        this.correctCharactersForPositions = correctPositionsForCharacters;
        this.incorrectPositionsForCharacters = incorrectPositionsForCharacters;
        this.characterFrequencies = characterFrequencies;
    }

    /**
     * Method takes a word as a char array of length 5 and returns if the word is a valid guess based on current knowledge
     * Throws IllegalArgumentException if the word is null or the length is not 5
     * @param word word to be checked
     * @return boolean true if word is valid and false otherwise
     */
    @Override
    public boolean validate(char[] word){
        if(word == null || word.length != 5){
            throw new IllegalArgumentException();
        }

        for(int i=0; i< 5; i++){
            if(invalidCharacters.contains(word[i])){
                return false;
            }
            Character characterOnThisPos = correctCharactersForPositions.get(i);
            if(characterOnThisPos!=null && characterOnThisPos != word[i]){
                return false;
            }
            Set<Integer> invalidPositionsForCharacter = incorrectPositionsForCharacters.get(word[i]);
            if(invalidPositionsForCharacter!=null && invalidPositionsForCharacter.contains(i)){
                return false;
            }
        }

        for(char c: incorrectPositionsForCharacters.keySet()){
            boolean contains = false;
            for (char l: word){
                if(c == l) {
                    contains = true;
                    break;
                }
            }
            if(!contains) return false;
        }

        for(char c: characterFrequencies.keySet()){
            int frequency = 0;
            for (char l: word){
                if(c == l) {
                    frequency++;
                }
            }
            if(characterFrequencies.get(c) > frequency) return false;
        }

        return true;
    }

    /**
     * Mark a character as invalid
     * Words containing invalid characters are going to be checked as invalid
     * @param c character to be marked as invalid
     */
    public void markCharacterAsInvalid(char c){
        this.invalidCharacters.add(c);
    }

    /**
     * Mark the correct character for a given position
     * Words that do not have the character on the marked position are going to be invalidated
     * @param c character to be marked
     * @param p position to be marked
     */
    public void markCorrectCharacterForPosition(char c, int p){
        correctCharactersForPositions.put(p,c);
    }

    /**
     *  Mark a position invalid for a character
     * @param c character with invalid position
     * @param p position to be marked
     */
    public void markInvalidPositionForValidCharacter(char c, int p){
        Set<Integer> invalidPositionsForCharacter = incorrectPositionsForCharacters.get(c);
        if(invalidPositionsForCharacter == null) {
            invalidPositionsForCharacter = new TreeSet<>();
        }
        invalidPositionsForCharacter.add(p);
        incorrectPositionsForCharacters.put(c,invalidPositionsForCharacter);
    }

    /**
     * Mark the number of times a character appears in the word
     * @param c character to be marked
     * @param f the number of times it appears in the word
     */
    public void markCharacterFrequency(char c, int f){
        characterFrequencies.put(c,f);
    }

    @Override
    public String toString(){
        return "Validator{"+
                "invalid characters" + invalidCharacters.toString() +
                "correct characters for postitions" + correctCharactersForPositions.toString() +
                "incorrect positions for characters" + incorrectPositionsForCharacters.toString() +
                "character frequencies" + characterFrequencies.toString() +
                "}";
    }

}
