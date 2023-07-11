package solver;

public class Main {

    public static void main(String[] args) {
        System.out.println("WORD TO BE GUESSED: cocoa");
        Solver solver = new Solver(new KnownWordChecker(new char[]{'c','o','c','o','a'}),new DefaultWordValidator(),new FileWordProvider());
        solver.solve();
    }
}
