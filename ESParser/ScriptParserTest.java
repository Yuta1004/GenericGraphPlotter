package ESParser;

class ScriptParserTest {

    public static void main(String[] args) {
        test1();
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

}