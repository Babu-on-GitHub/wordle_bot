package main;

import solver.DefaultWordValidator;
import solver.FileWordProvider;
import solver.Solver;
import solver.WordleWordChecker;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


public class Main {

    public static void main(String[] args) {

        try {
            Solver solver = new Solver(new WordleWordChecker(),new DefaultWordValidator(),new FileWordProvider());
            List<char[]>solution = solver.solve();
            for(char[] s : solution) System.out.println(Arrays.toString(s));
        }catch (FileNotFoundException e){
            System.out.println("Solver could not find the word dictionary. Check the resources");
        }


    }
}
