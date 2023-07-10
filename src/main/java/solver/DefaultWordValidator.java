package solver;

import java.util.*;

public class DefaultWordValidator implements WordValidator{

    Set<Character> invalidCharacters;
    Map<Integer, Character> correctCharactersForPositions;
    Map<Character, Set<Integer>> incorrectPositionsForCharacters;

    public DefaultWordValidator(){
        this.invalidCharacters = new HashSet<>();
        this.correctCharactersForPositions = new HashMap<>();
        this.incorrectPositionsForCharacters = new HashMap<>();
    }

    public DefaultWordValidator(Set<Character> invalidCharacters,
                                Map<Integer, Character> correctPositionsForCharacters,
                                Map<Character, Set<Integer>> incorrectPositionsForCharacters) {
        this.invalidCharacters = invalidCharacters;
        this.correctCharactersForPositions = correctPositionsForCharacters;
        this.incorrectPositionsForCharacters = incorrectPositionsForCharacters;
    }

    /**
     * Method takes a word as a char array of length 5 and returns if the word is a valid guess based on current knowledge
     * @param word
     * @return WordValidity representing if the word is valid or invalid based on current knowledge
     */
    @Override
    public WordValidity validate(char[] word){
        if(word == null || word.length != 5){
            throw new IllegalArgumentException();
        }

        for(int i=0; i< 5; i++){
            if(invalidCharacters.contains(word[i])){
                return WordValidity.INVALID;
            }
            Character characterOnThisPos = correctCharactersForPositions.get(i);
            if(characterOnThisPos!=null && characterOnThisPos != word[i]){
                return WordValidity.INVALID;
            }
            Set<Integer> invalidPositionsForCharacter = incorrectPositionsForCharacters.get(word[i]);
            if(invalidPositionsForCharacter!=null && invalidPositionsForCharacter.contains(i)){
                return WordValidity.INVALID;
            }
        }

        for(char c: incorrectPositionsForCharacters.keySet()){
            boolean contains = false;
            for (char l: word){
                if(c == l) contains = true;
            }
            if(!contains) return WordValidity.INVALID;
        }

        return WordValidity.VALID;
    }

    public void markCharacterAsInvalid(char c){
        this.invalidCharacters.add(c);
    }

    public void markCorrectCharacterForPosition(char c, int p){
        correctCharactersForPositions.put(p,c);
    }

    public void markInvalidPositionForValidCharacter(char c, int p){
        Set<Integer> invalidPositionsForCharacter = incorrectPositionsForCharacters.get(c);
        if(invalidPositionsForCharacter == null) {
            invalidPositionsForCharacter = new TreeSet<>();
        }
        invalidPositionsForCharacter.add(p);
    }


}
