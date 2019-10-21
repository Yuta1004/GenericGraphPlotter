package ESCompiler;

public class ScriptCompilerTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        ScriptCompiler ec = new ScriptCompiler("1004 + 1204");
        ec.compile();
    }

}
