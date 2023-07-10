package solver;

public class Main {

    public static void main(String[] args) {
        Solver solver = new Solver(new KnownWordChecker(new char[]{'c','o','c','o','a'}),new DefaultWordValidator(),new FileWordProvider());
        System.out.println(solver.solve());
    }
}
