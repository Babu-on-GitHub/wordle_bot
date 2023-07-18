package solver;

import java.util.*;

/**
 * Object used to solve the Wordle puzzles.
 * Each instance of the Solver can be used to solve only one word.
 */
public class Solver {

    protected static final char[] STARTER_WORD = {'l','e','a','s','t'};

    protected static final CharacterValidity[] CORRECT_WORD_VALIDITIES = {CharacterValidity.GREEN,CharacterValidity.GREEN,CharacterValidity.GREEN,CharacterValidity.GREEN,CharacterValidity.GREEN};
    private WordChecker checker;
    private WordValidator validator;
    private WordProvider provider;

    protected CharacterValidity[] validities;
    protected List<char[]> guesses;

    int numberOfCalls;

    public Solver(WordChecker checker, WordValidator validator, WordProvider provider) {
        this.checker = checker;
        this.validator = validator;
        this.provider = provider;
        validities = new CharacterValidity[5];
        guesses = new ArrayList<>();
        numberOfCalls = 0;
    }

    /**
     * Method used to solve the wordle game
     * Can be called only once per instance of the object since some of the dependencies cannot handle being used for more words
     * If multiple words need to be solved create new instances of the Solver class
     * @param infiniteGuesses boolean, if set to true the bot will try as many guesses as it needs to get to the correct answer, else it will stop after 6 guesses (might not have the correct answer)
     * @return The list of guesses taken
     * @throws IllegalStateException when trying to call the method a second time on the same object
     */
    public List<char[]> solve(boolean infiniteGuesses){
        if(numberOfCalls != 0){
            throw new IllegalStateException("Method called more than once per instance of Solver. Try creating a new instance Solver and make sure you use a new WordValidator");
        }else{
            numberOfCalls++;
        }
        initChecker();
        Random rng = new Random();
        List<char[]> wordList = getPotentialWordsBasedOnFirstGuess(STARTER_WORD);
        guesses.add(STARTER_WORD);
        char[] word = new char[5];
        while(!Arrays.equals(validities,CORRECT_WORD_VALIDITIES) && (guesses.size()<6 || infiniteGuesses)){
            word = wordList.get(Math.abs(rng.nextInt()%wordList.size()));
            guesses.add(word);
            //System.out.println(word);
            validities = checkWord(word);
            updateValidator(validities,word);
            wordList = reducePotentialWords(wordList);
        }

        return guesses;
    }

    protected List<char[]> reducePotentialWords(List<char[]> wordList) {
        List<char[]> newList = new ArrayList<>();
        for(char[] word: wordList){
            if(validator.validate(word)) newList.add(word);
        }
        return newList;
    }

    protected CharacterValidity[] checkWord(char[] word){
        return checker.checkWord(word);
    }

    protected void initChecker(){
        checker.init();
    }

    protected List<char[]> getPotentialWordsBasedOnFirstGuess(char[] firstGuess){
        List<char[]> list = new ArrayList<>();
        validities = checker.checkWord(firstGuess);
        updateValidator(validities,firstGuess);
        while(provider.hasNext()){
            char[] word = provider.next();
            if(validator.validate(word)) list.add(word);
        }
        return list;
    }

    protected void updateValidator(CharacterValidity[] characterValidities, char[] word){
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
