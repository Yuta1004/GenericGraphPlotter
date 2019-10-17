package ESCompiler;

public class ExprScriptCompiler {

    private String script;
    private Node root;

    public ExprScriptCompiler() {
        this.setScript("");
    }

    public ExprScriptCompiler(String script) {
        this.setScript(script);
    }

    public void setScript(String script) {
        this.script = script;
    }

    public void compile() {
        this.root = new Node();
    }

    private void compilerExpr() {
        
    }

    /* strton : 文字列から数字を読み取る */
    private double strton(String target) {
        double num = 0;
        for(int idx = 0; idx < target.length(); ++ idx) {
            if('0' <= target.charAt(idx) && target.charAt(idx) <= '9') {
                num = num * 10 + target.charAt(idx) - '0';
            } else {
                break;
            }
        }
        return num;
    }

    /* strtonSize : 文字列から数字が続く長さを求める */
    private int strtonSize(String target) {
        int idx = 0;
        for( ; idx < target.length(); ++ idx) {
            if(!('0' <= target.charAt(idx) && target.charAt(idx) <= '9')) {
                return idx;
            }
        }
        return idx;
    }
}
