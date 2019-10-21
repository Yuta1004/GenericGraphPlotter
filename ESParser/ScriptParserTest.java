package ESParser;

class ScriptParserTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        ScriptParser parser = new ScriptParser("var a, b, c, d");
        parser.parse();
    }

}