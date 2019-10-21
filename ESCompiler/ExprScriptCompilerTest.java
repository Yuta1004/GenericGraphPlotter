package ESCompiler;

public class ExprScriptCompilerTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        ExprScriptCompiler ec = new ExprScriptCompiler("1004 + 1204");
        ec.compile();
    }

}
