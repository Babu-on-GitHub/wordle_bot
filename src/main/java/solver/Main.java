package solver;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int[] distributionOfGuesses = new int[24];
        FileWordProvider provider = new FileWordProvider();
        List<char[]> words = new ArrayList<>();
        while (provider.hasNext()){
            words.add(provider.next());
        }
        Random random  = new Random();
        for(int i=0; i < 50000; i++){
            char[] word = words.get(Math.abs(random.nextInt()%words.size()));
            Solver solver = new Solver(new KnownWordChecker(word),new DefaultWordValidator(),new FileWordProvider());
            List<char[]>solution = solver.solve();
            distributionOfGuesses[solution.size()]++;
        }
        for(int i=0; i < 24; i++){
            System.out.println("Number of words solved in " + i + " guesses: " + distributionOfGuesses[i]);
        }
//        char[] word = {'i','l','i','a','c'};
//        Solver solver = new Solver(new KnownWordChecker(word),new DefaultWordValidator(),new FileWordProvider());
//        List<char[]>solution = solver.solve();
//        for(char[] s : solution) System.out.println(Arrays.toString(s));

    }
}
