import net.jqwik.api.*;
import org.junit.jupiter.api.Test;
import solver.DefaultWordValidator;
import solver.FileWordProvider;
import solver.KnownWordChecker;
import solver.Solver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SolverTest {

    @Test
    void testThrows() throws FileNotFoundException {
        char[] word = new char[]{'c','o','c','o','a'};
        Solver solver = new Solver(new KnownWordChecker(word),new DefaultWordValidator(),new FileWordProvider());
        List<char[]> solution = solver.solve(true);
        assertThatThrownBy(()->solver.solve(true)).isInstanceOf(IllegalStateException.class);
    }

    @Property
    void testInfiniteTries(@ForAll("wordProvider") char[] word) throws FileNotFoundException {
        Solver solver = new Solver(new KnownWordChecker(word),new DefaultWordValidator(),new FileWordProvider());
        List<char[]> solution = solver.solve(true);
        assertThat(solution.get(solution.size()-1)).isEqualTo(word);
    }

    @Property
    void testSixTries(@ForAll("wordProvider") char[] word) throws FileNotFoundException {
        Solver solver = new Solver(new KnownWordChecker(word),new DefaultWordValidator(),new FileWordProvider());
        List<char[]> solution = solver.solve(false);
        assertThat(solution.size()<=6).isTrue();
    }

    @Provide
    Arbitrary<char[]> wordProvider() throws FileNotFoundException {
        List<char[]> words = new ArrayList<>();
        FileWordProvider provider = new FileWordProvider();
        while (provider.hasNext()){
            words.add(provider.next());
        }
        return Arbitraries.of(words);
    }


}
