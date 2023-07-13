import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import solver.DefaultWordValidator;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultWordValidatorTest {

    @ParameterizedTest
    @MethodSource("test1src")
    void test1(char[] word, boolean expected){
        //testing with following knowledge
        //invalid char: 'c','r','a','n','e','p','u','v';
        //invalid positions 'l',Set.of(0,1),'o',Set.of(2)
        //correct positions 4,'y'
        //character frequencies 'o',1,'y',1,'l','2'
        DefaultWordValidator validator = new DefaultWordValidator(
            Set.of('c','r','a','n','e','p','u','v'),
            Map.of(4,'y'),
            Map.of('l',Set.of(0,1),'o',Set.of(2)),
            Map.of('o',1,'y',1,'l',2)
        );
        assertThat(validator.validate(word)).isEqualTo(expected);
    }

    static Stream<Arguments> test1src(){
        return Stream.of(
                Arguments.of(
                        chars('c','r','a','n','e'),
                        false
                ),
                Arguments.of(
                        chars('c','r','i','m','e'),
                        false
                ),
                Arguments.of(
                        chars('c','r','a','v','e'),
                        false
                ),
                Arguments.of(
                        chars('p','l','u','m','p'),
                        false
                ),
                Arguments.of(
                        chars('l','o','b','b','y'),
                        false
                ),
                Arguments.of(
                        chars('d','o','l','l','y'),
                        true
                ),
                Arguments.of(
                        chars('d','o','l','l','s'),
                        false
                ),
                Arguments.of(
                        chars('f','o','l','l','y'),
                        true
                ),
                Arguments.of(
                        chars('o','i','l','l','y'),
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("test2src")
    void test2(char[] word, boolean expected){
        DefaultWordValidator validator = new DefaultWordValidator();
        assertThat(validator.validate(word)).isEqualTo(expected);
    }

    static Stream<Arguments> test2src(){
        return Stream.of(
                Arguments.of(
                        chars('c','r','a','n','e'),
                        true
                ),
                Arguments.of(
                        chars('c','r','i','m','e'),
                        true
                ),
                Arguments.of(
                        chars('c','r','a','v','e'),
                        true
                ),
                Arguments.of(
                        chars('p','l','u','m','p'),
                        true
                ),
                Arguments.of(
                        chars('l','o','b','b','y'),
                        true
                ),
                Arguments.of(
                        chars('d','o','l','l','y'),
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("test3src")
    void test3(char[] word, boolean expected){
        //testing with following knowledge
        //invalid char: 'c','r','a','n','e','p','u','v';
        //correct positions 4,'y'
        DefaultWordValidator validator = new DefaultWordValidator(
                Set.of('c','r','a','n','e','p','u','v'),
                Map.of(4,'y'),
                Map.of(),
                Map.of('y',1)
        );
        assertThat(validator.validate(word)).isEqualTo(expected);
    }

    static Stream<Arguments> test3src(){
        return Stream.of(
                Arguments.of(
                        chars('c','r','a','n','e'),
                        false
                ),
                Arguments.of(
                        chars('c','r','i','m','e'),
                        false
                ),
                Arguments.of(
                        chars('c','r','a','v','e'),
                        false
                ),
                Arguments.of(
                        chars('p','l','u','m','p'),
                        false
                ),
                Arguments.of(
                        chars('l','o','o','n','y'),
                        false
                ),
                Arguments.of(
                        chars('l','o','l','l','y'),
                        true
                ),
                Arguments.of(
                        chars('f','o','l','l','y'),
                        true
                ),
                Arguments.of(
                        chars('m','i','l','l','y'),
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("test4src")
    void test4(char[] word, boolean expected){
        //testing with following knowledge
        //invalid char: 'c','r','a','n','e','p','u','v';
        //invalid positions 'l',Set.of(0,1),'o',Set.of(2)
        //correct positions 4,'y'
        DefaultWordValidator validator = new DefaultWordValidator(
                Set.of('c','r','a','n','e','p','u','v'),
                Map.of(),
                Map.of('l',Set.of(0,1),'o',Set.of(2)),
                Map.of('l',2,'o',1)
        );
        assertThat(validator.validate(word)).isEqualTo(expected);
    }

    static Stream<Arguments> test4src(){
        return Stream.of(
                Arguments.of(
                        chars('c','r','a','n','e'),
                        false
                ),
                Arguments.of(
                        chars('c','r','i','m','e'),
                        false
                ),
                Arguments.of(
                        chars('c','r','a','v','e'),
                        false
                ),
                Arguments.of(
                        chars('p','l','u','m','p'),
                        false
                ),
                Arguments.of(
                        chars('l','o','b','b','y'),
                        false
                ),
                Arguments.of(
                        chars('d','o','l','l','s'),
                        true
                ),
                Arguments.of(
                        chars('f','o','l','l','y'),
                        true
                ),
                Arguments.of(
                        chars('o','i','l','l','y'),
                        true
                )
        );
    }

    private static char[] chars(char...chars){
        return chars;
    }

}
