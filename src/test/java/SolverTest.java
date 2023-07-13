import net.jqwik.api.*;
import solver.DefaultWordValidator;
import solver.FileWordProvider;
import solver.KnownWordChecker;
import solver.Solver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SolverTest {

    @Property
    void testManyTries(@ForAll("wordProvider") char[] word) throws FileNotFoundException {
        Solver solver = new Solver(new KnownWordChecker(word),new DefaultWordValidator(),new FileWordProvider());
        List<char[]> solution = solver.solve();
        assertThat(solution.get(solution.size()-1)).isEqualTo(word);
        //if(solution.size()>6) System.out.println(solution.size());
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
