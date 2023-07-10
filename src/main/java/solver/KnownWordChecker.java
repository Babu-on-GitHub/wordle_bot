package solver;

import java.util.Arrays;

public class KnownWordChecker implements WordChecker{

    char[] correctWord;

    public KnownWordChecker(char[] correctWord) {
        this.correctWord = correctWord;
    }

    /**
     * Method that gets a word as input and compares it to a known previously given word
     * Method returns an array of character validities that represent whether the character is included in the correct word and if it is on the correct position
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
