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
        this.expr = new ArrayList<ExprParser>();
    }

    public void parse() {
        ArrayList<Integer> loopStack = new ArrayList<Integer>();
        String splitedScript[] = script.split("\n");

        for(int idx = 0; idx < splitedScript.length; ++ idx) {
            String line = splitedScript[idx];
            line = skipSpace(line);

            // 変数定義
            if(line.startsWith("var")) {
                line = line.substring(3, line.length());
                for(String varName: line.split(",")) {
                    varName = varName.replace(" ", "");
                    if(checkVarName(varName)) {
                        var.put(varName, 0.0);
                    }
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

            // 値代入
            if(line.contains("=")) {
                String left = line.split("=")[0].replace(" ", "");
                String right = line.split("=")[1].replace(" ", "");
                if(checkVarName(left) && var.containsKey(left)) {
                    ExprParser ep = new ExprParser(right);
                    ep.parse();
                    ep = setVar(ep);
                    var.put(left, ep.calc());
                }
                continue;
            }

            // loop
            if(line.startsWith("loop")) {
                loopStack.add(idx);
                continue;
            }

            // end
            if(line.replace(" ", "").equals("end") && loopStack.size() > 0) {
                idx = loopStack.get(loopStack.size()-1) - 1;
                loopStack.remove(loopStack.size()-1);
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

    /* checkVarName : 変数名として正しいものになっているか検証する */
    private boolean checkVarName(String varName) {
        boolean result = true;
        for(char ch: varName.toCharArray()) {
            result = result && (('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z') || ch == '_');
        }
        return result;
    }
}
