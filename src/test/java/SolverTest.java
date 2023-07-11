import net.jqwik.api.*;
import net.jqwik.api.constraints.CharRange;
import solver.DefaultWordValidator;
import solver.FileWordProvider;
import solver.KnownWordChecker;
import solver.Solver;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SolverTest {

    @Property
    void testManyTries(@ForAll("wordProvider") char[] word){
        Solver solver = new Solver(new KnownWordChecker(word),new DefaultWordValidator(),new FileWordProvider());;
        assertThat(solver.solve()).isEqualTo(word);
    }

    @Provide
    Arbitrary<char[]> wordProvider(){
        FileWordProvider provider = new FileWordProvider();
        List<char[]> words = new ArrayList<>();
        while (provider.hasNext()){
            words.add(provider.next());
        }
        return Arbitraries.of(words);
    }


}
