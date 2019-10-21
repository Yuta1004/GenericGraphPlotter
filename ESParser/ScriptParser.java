package ESParser;

import java.util.HashMap;

public class ScriptParser {

    private String script;
    private Node root;
    private HashMap<String, Double> var;

    public ScriptCompiler(String script) {
        this.script = script;
        this.var = new HashMap<String, Double>();
    }

    public void compile() { }
}
