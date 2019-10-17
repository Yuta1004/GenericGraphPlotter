import ESCompiler.ExprScriptCompiler;

class ExprScriptCompilerTest {

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        ExprScriptCompiler ec = new ExprScriptCompiler();
    }

    private static void test2() {
        ExprScriptCompiler ec = new ExprScriptCompiler("script");
    }

}
