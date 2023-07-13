package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Implementation of WordProvider that uses a dictionary file to provide words
 */
public class FileWordProvider implements WordProvider {

    private Scanner scanner;

    public FileWordProvider() throws FileNotFoundException {
            scanner = new Scanner(new File("src/main/resources/wordle-La.txt"));
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
