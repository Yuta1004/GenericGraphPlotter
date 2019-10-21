package ESCompiler;

public class ExprCompilerTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        ExprCompiler ec = new ExprCompiler("1004 + 1204");
        ec.compile();
    }

}
