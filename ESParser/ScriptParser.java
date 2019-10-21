package ESParser;

import java.util.HashMap;
import java.util.ArrayList;

public class ScriptParser {

    private String script;
    private Node root;
    private HashMap<String, Double> var;
    private ArrayList<ExprParser> expr;

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
                continue;
            }

            // グラフ追加
            if(line.startsWith("plot")) {
                line = line.substring(4, line.length());
                ExprParser ep = new ExprParser(line);
                ep = setVar(ep);
                ep.parse();
                expr.add(ep);
                continue;
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

    /* setVar : 宣言されている変数全てをExprParserに渡す */
    private ExprParser setVar(ExprParser ep) {
        for(String varName: var.keySet()) {
            ep.setVar(varName, var.get(varName));
        }
        return ep;
    }
}
