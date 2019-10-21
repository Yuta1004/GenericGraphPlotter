package ESParser;

public class ExprParserTest {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
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
        ec.compile();
        ec.setVar("x", x);
        ec.setVar("y", y);
        ec.setVar("z", z);
        valid(ec.calc(), 2 * x / y + z);
    }

    private static void test7() {
        ExprParser ec = new ExprParser("sin(x) + cos(y) + 2");
        ec.compile();

        for(int idx = 0; idx < 10; ++ idx) {
            double radX = Math.toRadians(idx);
            double radY = Math.toRadians(idx + 30);
            ec.setVar("x", radX);
            ec.setVar("y", radY);
            valid(ec.calc(), Math.sin(radX) + Math.cos(radY) + 2);
        }
    }

    private static void test8() {
        ExprParser ec = new ExprParser("logE");
        ec.compile();
        valid(ec.calc(), Math.log(Math.E));
    }

    private static void test9() {
        ExprParser ec = new ExprParser("sin(PI) + abs(cos(PI))");
        ec.compile();
        valid(ec.calc(), Math.sin(Math.PI) + Math.abs(Math.cos(Math.PI)));
    }

    private static void valid(double a, double b) {
        if(a == b) {
            System.out.println("OK : " + a + " == " + b);
        } else {
            System.out.println("NG : " + a + " != " + b);
        }
    }

}
