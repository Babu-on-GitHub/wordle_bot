import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import solver.CharacterValidity;
import solver.KnownWordChecker;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;


public class KnownWordCheckerTest {

    @ParameterizedTest
    @MethodSource("source")
    void test(char[]correctWord ,char[] input, CharacterValidity[] expected){
        KnownWordChecker testChecker = new KnownWordChecker(correctWord);
        assertThat(testChecker.checkWord(input)).isEqualTo(expected);
    }

    static Stream<Arguments> source(){
        return Stream.of(
                Arguments.of(
                        chars('c','o','c','o','a'),
                        chars('c','o','c','o','a'),
                        validities(CharacterValidity.GREEN,
                                CharacterValidity.GREEN,
                                CharacterValidity.GREEN,
                                CharacterValidity.GREEN,
                                CharacterValidity.GREEN)),
                Arguments.of(
                        chars('c','o','c','o','a'),
                        chars('c','o','b','o','a'),
                        validities(CharacterValidity.GREEN,
                                CharacterValidity.GREEN,
                                CharacterValidity.GREY,
                                CharacterValidity.GREEN,
                                CharacterValidity.GREEN)),
                Arguments.of(
                        chars('c','o','c','o','a'),
                        chars('a','l','g','a','e'),
                        validities(CharacterValidity.YELLOW,
                                CharacterValidity.GREY,
                                CharacterValidity.GREY,
                                CharacterValidity.GREY,
                                CharacterValidity.GREY)),
                Arguments.of(
                        chars('c','o','c','o','a'),
                        chars('c','o','b','r','a'),
                        validities(CharacterValidity.GREEN,
                                CharacterValidity.GREEN,
                                CharacterValidity.GREY,
                                CharacterValidity.GREY,
                                CharacterValidity.GREEN)),
                Arguments.of(
                        chars('c','o','c','o','a'),
                        chars('b','r','i','n','g'),
                        validities(CharacterValidity.GREY,
                                CharacterValidity.GREY,
                                CharacterValidity.GREY,
                                CharacterValidity.GREY,
                                CharacterValidity.GREY))

        );
    }

    private static CharacterValidity[] validities(CharacterValidity...validities){
        return validities;
    }

    private static char[] chars(char...chars){
        return chars;
    }


}
