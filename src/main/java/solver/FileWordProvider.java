package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileWordProvider implements WordProvider {

    Scanner scanner;

    public FileWordProvider() {
        try {
            scanner = new Scanner(new File("src/main/resources/wordle-La.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Bad file path oops");
        }

    }

    @Override
    public char[] next() {
        return scanner.nextLine().toCharArray();
    }

    @Override
    public boolean hasNext(){
        return scanner.hasNextLine();
    }
}
