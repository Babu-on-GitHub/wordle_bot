import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import solver.CharacterValidity;
import solver.DefaultWordValidator;
import solver.WordValidity;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultWordValidatorTest {

    @ParameterizedTest
    @MethodSource("test1src")
    void test1(char[] word, WordValidity expected){
        //testing with following knowledge
        //invalid char: 'c','r','a','n','e','p','u','v';
        //invalid positions 'l',Set.of(0,1),'o',Set.of(2)
        //correct positions 4,'y'
        DefaultWordValidator validator = new DefaultWordValidator(
            Set.of('c','r','a','n','e','p','u','v'),
            Map.of(4,'y'),
            Map.of('l',Set.of(0,1),'o',Set.of(2))
        );
        assertThat(validator.validate(word)).isEqualTo(expected);
    }

    static Stream<Arguments> test1src(){
        return Stream.of(
                Arguments.of(
                        chars('c','r','a','n','e'),
                        WordValidity.INVALID
                ),
                Arguments.of(
                        chars('c','r','i','m','e'),
                        WordValidity.INVALID
                ),
                Arguments.of(
                        chars('c','r','a','v','e'),
                        WordValidity.INVALID
                ),
                Arguments.of(
                        chars('p','l','u','m','p'),
                        WordValidity.INVALID
                ),
                Arguments.of(
                        chars('l','o','b','b','y'),
                        WordValidity.INVALID
                ),
                Arguments.of(
                        chars('d','o','l','l','y'),
                        WordValidity.VALID
                ),
                Arguments.of(
                        chars('f','o','l','l','y'),
                        WordValidity.VALID
                ),
                Arguments.of(
                        chars('m','i','l','o','y'),
                        WordValidity.VALID
                )
        );
    }

    private static char[] chars(char...chars){
        return chars;
    }

}
