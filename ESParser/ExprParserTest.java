package ESParser;

public class ExprParserTest {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    private static void test1() {
        ExprParser ec = new ExprParser("1004 + 1204");
        ec.compile();
        valid(ec.calc(), 1004 + 1204);
    }

    private static void test2() {
        ExprParser ec = new ExprParser("2 * 3");
        ec.compile();
        valid(ec.calc(), 2 * 3);
    }

    private static void test3() {
        ExprParser ec = new ExprParser("10 * 20 + 30 * 40");
        ec.compile();
        valid(ec.calc(), 10 * 20 + 30 * 40);
    }

    private static void test4() {
        ExprParser ec = new ExprParser("-10 + 20 * 10 / 4");
        ec.compile();
        valid(ec.calc(), -10 + 20 * 10 / 4);
    }

    private static void test5() {
        ExprParser ec = new ExprParser("(1 + 2 + 3 + 4) / 10 - 7");
        ec.compile();
        valid(ec.calc(), (1 + 2 + 3 + 4) / 10 - 7);
    }

    private static void test6() {
        int x = 10, y = 2, z = 6;
        ExprParser ec = new ExprParser("2 * x / y + z");
        ec.setVar("x", x);
        ec.setVar("y", y);
        ec.setVar("z", z);
        ec.compile();
        valid(ec.calc(), 2 * x / y + z);
    }

    private static void valid(double a, double b) {
        if(a == b) {
            System.out.println("OK : " + a + " == " + b);
        } else {
            System.out.println("NG : " + a + " != " + b);
        }
    }

}
