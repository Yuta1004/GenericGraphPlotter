package ESCompiler;

public class ExprCompilerTest {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static void test1() {
        ExprCompiler ec = new ExprCompiler("1004 + 1204");
        ec.compile();
        valid(ec.calc(), 1004 + 1204);
    }

    private static void test2() {
        ExprCompiler ec = new ExprCompiler("2 * 3");
        ec.compile();
        valid(ec.calc(), 2 * 3);
    }

    private static void test3() {
        ExprCompiler ec = new ExprCompiler("10 * 20 + 30 * 40");
        ec.compile();
        valid(ec.calc(), 10 * 20 + 30 * 40);
    }

    private static void valid(double a, double b) {
        if(a == b) {
            System.out.println("OK : " + a + " == " + b);
        } else {
            System.out.println("NG : " + a + " != " + b);
        }
    }

}
