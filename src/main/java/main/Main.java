package main;

import solver.DefaultWordValidator;
import solver.FileWordProvider;
import solver.Solver;
import solver.WordleWordChecker;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Main class to run the program
 */
public class Main {

    /**
     * Main method
     * Runs an instance of solver that opens a browser window and solves today's Wordle
     * @param args console arguments
     */
    public static void main(String[] args) {

        try {
            Solver solver = new Solver(new WordleWordChecker(),new DefaultWordValidator(),new FileWordProvider());
            List<char[]>solution = solver.solve(false);
            for(char[] s : solution) System.out.println(Arrays.toString(s));
        }catch (FileNotFoundException e){
            System.out.println("Solver could not find the word dictionary. Check the resources");
        }


    }
}
