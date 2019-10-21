package ESCompiler;

public class ExprCompiler {

    private Node root;
    private String expr;

    public ExprCompiler(String expr) {
        this.expr = expr;
    }

    public void compile() {
        System.out.println(num().value);
        System.out.println(expr);
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
        skipSpace();
        return new Node(null, null, getNum(), NodeKind.NUM.val);
    }

    /* getNum : 式の先頭から字を読み取る */
    private double getNum() {
        double num = 0;
        int idx = 0;
        for(; idx < expr.length(); ++ idx) {
            if('0' <= expr.charAt(idx) && expr.charAt(idx) <= '9') {
                num = num * 10 + expr.charAt(idx) - '0';
            } else {
                break;
            }
        }
        expr = expr.substring(idx, expr.length());
        return num;
    }

    /* skipSpace : 式先頭にある空白を読み飛ばす*/
    private void skipSpace() {
        while(expr.length() >= 0 && expr.charAt(0) == ' ') {
            expr = expr.substring(1, expr.length());
        }
    }
}
