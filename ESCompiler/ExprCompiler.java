package ESCompiler;

public class ExprCompiler {

    private Node root;
    private String expr;

    public ExprCompiler(String expr) {
        this.expr = expr;
    }

    public void compile() {
        Node tmp = new Node(null, null, strton(expr), NodeKind.Undefined.val); 
        expr = expr.substring(strtonSize(this.expr), expr.length());
        System.out.println(tmp.value);
        System.out.println(expr);
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
