package ESCompiler;

public class ExprCompiler {

    private Node root;
    private String expr;

    public ExprCompiler(String expr) {
        this.expr = expr;
    }

    public void compile() {
        System.out.println(mul().value);
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
        Node node = num();

        while(true) {
            if(checkChar('*')) {
                node = new Node(node, num(), 0, NodeKind.ADD.val);
                continue;
            }
       
            if(checkChar('/')) {
                node = new Node(node, num(), 0, NodeKind.SUB.val);
                continue;
            }
            break;
        }
        return node;
    }

    /* num = 数 */
    private Node num() {
        return new Node(null, null, getNum(), NodeKind.NUM.val);
    }

    /* getNum : 式の先頭から字を読み取る */
    private double getNum() {
        skipSpace();
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
        while(expr.length() > 0 && expr.charAt(0) == ' ') {
            expr = expr.substring(1, expr.length());
        }
    }

    /* checkChar : 式の先頭の文字をチェックする */
    private boolean checkChar(char validChar) {
        skipSpace();
        if(expr.length() > 0 && expr.charAt(0) == validChar) {
            expr = expr.substring(1, expr.length());
            return true;
        }
        return false;
    }
}
