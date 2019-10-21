package ESCompiler;

public class ExprCompiler {

    private Node root;
    private String expr;

    public ExprCompiler(String expr) {
        this.expr = expr;
    }

    public void compile() {
        Node tmp = new Node(null, null, strton(expr), NodeKind.UNDEFINED.val); 
        expr = expr.substring(strtonSize(this.expr), expr.length());
        System.out.println(tmp.value);
        System.out.println(skipSpace(expr));
    }


    /* expr = add | "(" expr ")" */
    private Node expr() {
        return null;        
    }

    /* add = mul ("+" mul | "-" mul)* */
    private Node add() {
        return null;
    }

    /* mul = num ("*" num | "-" num)* */
    private Node mul() {
        return null;
    }

    /* num = 数 */
    private Node num() {
        return null;
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

    /* skipSpace : 先頭にある空白を読み飛ばす*/
    private String skipSpace(String target) {
        while(target.length() >= 0 && target.charAt(0) == ' ') {
            target = target.substring(1, target.length());
        }
        return target;
    }
}
