package solver;

import Wordle.WordlePageObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Solver solver = new Solver(new WordleWordChecker(),new DefaultWordValidator(),new FileWordProvider());
        List<char[]>solution = solver.solve();
        for(char[] s : solution) System.out.println(Arrays.toString(s));

    }
}
