package ESParser;

import java.util.HashMap;

public class ScriptParser {

    private String script;
    private Node root;
    private HashMap<String, Double> var;

    public ScriptParser(String script) {
        this.script = script;
        this.var = new HashMap<String, Double>();
    }

    public void parse() {
        for(String line: script.split("\n")) {
            line = skipSpace(line);

            // 変数定義
            if(line.startsWith("var")) {
                line = line.substring(3, line.length());
                for(String varName: line.split(",")) {
                    var.put(varName.replace(" ", ""), 0.0);
                }
            }
        }
    }

    /* skipSpace : 式先頭にある空白を読み飛ばす*/
    private String skipSpace(String target) {
        while(target.length() > 0 && target.charAt(0) == ' ') {
            target = target.substring(1, target.length());
        }
        return target;
    }
}
