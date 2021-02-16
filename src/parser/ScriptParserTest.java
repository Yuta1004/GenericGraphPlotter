package parser;

class ScriptParserTest {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    private static void test1() {
        ScriptParser parser = new ScriptParser("var a, b, c, d");
        parser.parse();
    }

    private static void test2() {
        String script[] = {
            "var a, b",
            "plot << sin(a) + cos(b)",
        };
        ScriptParser parser = new ScriptParser(String.join("\n", script));
        parser.parse();
    }

    private static void test3() {
        String script[] = {
            "var a, b, c",
            "a = 10",
            "b = 20",
            "c = a + b",
            "plot << sin(a) + cos(b)",
        };
        ScriptParser parser = new ScriptParser(String.join("\n", script));
        parser.parse();
    }

    private static void test4() {
        String script[] = {
            "var cnt, x, y",
            "cnt = 0",
            "loop: cnt < 10",
            "   x = cnt * 10",
            "   y = cnt * 5",
            "   cnt = cnt + 1",
            "end",
        };
        ScriptParser parser = new ScriptParser(String.join("\n", script));
        parser.parse();
    }

    private static void test5() {
        String script[] = {
            "var cntA, cntB, x",
            "cntA = 0",
            "loop: cntA < 5",
            "   cntB = 0",
            "   loop: cntB < 3",
            "       x = cntA*10 + cntB",
            "       cntB = cntB + 1",
            "   end",
            "   cntA = cntA + 1",
            "end",
        };
        ScriptParser parser = new ScriptParser(String.join("\n", script));
        parser.parse();
    }

    private static void test6() {
        String script[] = {
            "plot << sinx",
        };
        ScriptParser parser = new ScriptParser(String.join("\n", script));
        parser.parse();

        double x[] = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
        double y[] = parser.calcGraph(0, x);
        for(int idx = 0; idx < x.length; ++ idx) {
            valid(y[idx], Math.sin(x[idx]));
        }
    }

    private static void valid(double a, double b) {
        if(a == b) {
            System.out.println("OK : " + a + " == " + b);
            return;
        }
        System.out.println("NG : " + a + " != " + b);
    }
}