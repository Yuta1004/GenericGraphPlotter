package ESParser;

class ScriptParserTest {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }

    private static void test1() {
        ScriptParser parser = new ScriptParser("var a, b, c, d");
        parser.parse();
    }

    private static void test2() {
        String script[] = {
            "var a, b",
            "plot sin(a) + cos(b)",
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
            "plot sin(a) + cos(b)",
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
}