package solver;

import java.util.*;

public class Solver {

    private static final char[] STARTER_WORD = {'l','e','a','s','t'};

    private static final CharacterValidity[] CORRECT_WORD_VALIDITIES = {CharacterValidity.GREEN,CharacterValidity.GREEN,CharacterValidity.GREEN,CharacterValidity.GREEN,CharacterValidity.GREEN};
    private WordChecker checker;
    private WordValidator validator;
    private WordProvider provider;

    CharacterValidity[] validities;
    List<char[]> guesses;

    public Solver(WordChecker checker, WordValidator validator, WordProvider provider) {
        this.checker = checker;
        this.validator = validator;
        this.provider = provider;
        validities = new CharacterValidity[5];
        guesses = new ArrayList<>();
    }

    /**
     * Method used to solve the wordle game
     * @return The list of guesses taken until the word is guessed.
     */
    public List<char[]> solve(){

        Random rng = new Random();
        List<char[]> wordList = getPotentialWordsBasedOnFirstGuess(STARTER_WORD);
        guesses.add(STARTER_WORD);
        if(Arrays.equals(validities,CORRECT_WORD_VALIDITIES)) return guesses;
        //let's actually do the rest
        char[] word = new char[5];
        do{
            word = wordList.get(Math.abs(rng.nextInt()%wordList.size()));
            guesses.add(word);
            //System.out.println(word);
            validities = checker.checkWord(word);
            updateValidator(validities,word);
            wordList = reducePotentialWords(wordList);
        }while(!Arrays.equals(validities,CORRECT_WORD_VALIDITIES));

        return guesses;
    }

    private List<char[]> reducePotentialWords(List<char[]> wordList) {
        List<char[]> newList = new ArrayList<>();
        for(char[] word: wordList){
            if(validator.validate(word)) newList.add(word);
        }
        return newList;
    }

    private List<char[]> getPotentialWordsBasedOnFirstGuess(char[] firstGuess){
        List<char[]> list = new ArrayList<>();
        validities = checker.checkWord(firstGuess);
        updateValidator(validities,firstGuess);
        while(provider.hasNext()){
            char[] word = provider.next();
            if(validator.validate(word)) list.add(word);
        }
        return list;
    }

    private void updateValidator(CharacterValidity[] characterValidities, char[] word){
        for(int i = 0; i < 5; i++){
            int count = 0;
            for(int j = 0; j < 5; j++){
                if(word[j] == word[i] && characterValidities[j] != CharacterValidity.GREY) count++;
            }
            if(characterValidities[i] == CharacterValidity.GREY){
                if(count == 0) {
                    validator.markCharacterAsInvalid(word[i]);
                }
            }else{
                //TODO maybe do something like a frequency vector to remove redundant loops
                validator.markCharacterFrequency(word[i],count);
                if(characterValidities[i] == CharacterValidity.GREEN){
                    validator.markCorrectCharacterForPosition(word[i],i);
                }else{
                    validator.markInvalidPositionForValidCharacter(word[i],i);
                }
            }
        }
    }


}
