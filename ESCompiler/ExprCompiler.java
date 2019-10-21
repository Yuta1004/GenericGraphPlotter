package ESCompiler;

public class ExprCompiler {

    private Node root;
    private String expr;

    public ExprCompiler(String expr) {
        this.expr = expr;
    }

    public void compile() {
        root = expr();
    }

    public double calc() {
        return calcChild(root); 
    }

    private double calcChild(Node node) {
        if(node.kind == NodeKind.NUM) return node.value;

        double leftVal = calcChild(node.left);
        double rightVal = calcChild(node.right);
   
        switch(node.kind){
        case ADD:
            return leftVal + rightVal;

        case SUB:
            return leftVal - rightVal;

        case MUL:
            return leftVal * rightVal;
 
        case DIV:
            return leftVal / rightVal;

        default:
            return 0;
        } 
    }

    /* expr = add */
    private Node expr() {
        return add();
    }

    /* add = mul ("+" mul | "-" mul)* */
    private Node add() {
        Node node = mul();

        while(true) {
            if(checkChar('+')) {
                node = new Node(node, mul(), 0, NodeKind.ADD);
                continue;
            }
       
            if(checkChar('-')) {
                node = new Node(node, mul(), 0, NodeKind.SUB);
                continue;
            }
            break;
        }
        return node;
    }

    /* mul = num ("*" num | "-" num)* */
    private Node mul() {
        Node node = num();

        while(true) {
            if(checkChar('*')) {
                node = new Node(node, num(), 0, NodeKind.MUL);
                continue;
            }
       
            if(checkChar('/')) {
                node = new Node(node, num(), 0, NodeKind.DIV);
                continue;
            }
            break;
        }
        return node;
    }

    /* num = 数 */
    private Node num() {
        return new Node(null, null, getNum(), NodeKind.NUM);
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
