package ESParser;

class ExprParserTest {

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
        test10();
    }

    private static void test1() {
        ExprParser ep = new ExprParser("1004 + 1204");
        ep.parse();
        valid(ep.calc(), 1004 + 1204);
    }

    private static void test2() {
        ExprParser ep = new ExprParser("2 * 3");
        ep.parse();
        valid(ep.calc(), 2 * 3);
    }

    private static void test3() {
        ExprParser ep = new ExprParser("10 * 20 + 30 * 40");
        ep.parse();
        valid(ep.calc(), 10 * 20 + 30 * 40);
    }

    private static void test4() {
        ExprParser ep = new ExprParser("-10 + 20 * 10 / 4");
        ep.parse();
        valid(ep.calc(), -10 + 20 * 10 / 4);
    }

    private static void test5() {
        ExprParser ep = new ExprParser("(1 + 2 + 3 + 4) / 10 - 7");
        ep.parse();
        valid(ep.calc(), (1 + 2 + 3 + 4) / 10 - 7);
    }

    private static void test6() {
        int x = 10, y = 2, z = 6;
        ExprParser ep = new ExprParser("2 * x / y + z");
        ep.parse();
        ep.setVar("x", x);
        ep.setVar("y", y);
        ep.setVar("z", z);
        valid(ep.calc(), 2 * x / y + z);
    }

    private static void test7() {
        ExprParser ep = new ExprParser("sin(x) + cos(y) + 2");
        ep.parse();

        for(int idx = 0; idx < 10; ++ idx) {
            double radX = Math.toRadians(idx);
            double radY = Math.toRadians(idx + 30);
            ep.setVar("x", radX);
            ep.setVar("y", radY);
            valid(ep.calc(), Math.sin(radX) + Math.cos(radY) + 2);
        }
    }

    private static void test8() {
        ExprParser ep = new ExprParser("logE");
        ep.parse();
        valid(ep.calc(), Math.log(Math.E));
    }

    private static void test9() {
        ExprParser ep = new ExprParser("sin(PI) + abs(cos(PI))");
        ep.parse();
        valid(ep.calc(), Math.sin(Math.PI) + Math.abs(Math.cos(Math.PI)));
    }

    private static void test10() {
        ExprParser ep = new ExprParser("((1 > 0)*2) * ((3 <= 3)*3) * ((10 == 10)*5) * ((12 != 4)*7)");
        ep.parse();
        valid(ep.calc(), 210);
    }

    private static void valid(double a, double b) {
        if(a == b) {
            System.out.println("OK : " + a + " == " + b);
        } else {
            System.out.println("NG : " + a + " != " + b);
        }
    }

}
