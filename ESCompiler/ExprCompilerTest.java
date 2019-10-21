package ESCompiler;

public class ExprCompilerTest {

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        ExprCompiler ec = new ExprCompiler("1004 + 1204");
        ec.compile();
    }

    private static void test2() {
        ExprCompiler ec = new ExprCompiler("2 * 3");
        ec.compile();
    }
}
