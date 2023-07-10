package solver;

import java.util.*;

public class Solver {

    private WordChecker checker;
    private WordValidator validator;
    private WordProvider provider;

    public Solver(WordChecker checker, WordValidator validator, WordProvider provider) {
        this.checker = checker;
        this.validator = validator;
        this.provider = provider;
    }

    public char[] solve(){
        Random rng = new Random();
        return new char[0];
    }


}
