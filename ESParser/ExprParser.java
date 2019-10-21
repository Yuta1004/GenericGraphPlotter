package ESParser;

import java.util.HashMap;

public class ExprParser {

    private Node root;
    private String expr;
    private HashMap<String, Double> var;

    public ExprParser(String expr) {
        this.expr = expr;
        this.var = new HashMap<String, Double>();
    }

    public void compile() {
        root = expr();
    }

    public double calc() {
        return calcChild(root);
    }

    public void setVar(String name, double value) {
        var.put(name, value);
    }

    private double calcChild(Node node) {
        if(node == null) return 0;
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

        case SIN:
            return Math.sin(leftVal);

        case COS:
            return Math.cos(leftVal);

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
            if(checkPrefix("+")) {
                node = new Node(node, mul(), NodeKind.ADD);
                continue;
            }
            else if(checkPrefix("-")) {
                node = new Node(node, mul(), NodeKind.SUB);
                continue;
            }
            break;
        }
        return node;
    }

    /* mul = unary ("*" unary | "-" unary)* */
    private Node mul() {
        Node node = unary();

        while(true) {
            if(checkPrefix("*")) {
                node = new Node(node, num(), NodeKind.MUL);
                continue;
            }
            else if(checkPrefix("/")) {
                node = new Node(node, num(), NodeKind.DIV);
                continue;
            }
            break;
        }
        return node;
    }

    /* unary = ("+" | "-")* func */
    private Node unary() {
        checkPrefix("+");
        if(checkPrefix("-")) {
            return new Node(new Node(0), func(), NodeKind.SUB);
        }
        return func();
    }

    /* func = ("sin" | "cos")? num */
    private Node func() {
        if(checkPrefix("sin")) {
            return new Node(num(), null, NodeKind.SIN);
        }
        else if(checkPrefix("cos")) {
            return new Node(num(), null, NodeKind.COS);
        }
        return num();
    }

    /* num = 数 | "(" expr ")" */
    private Node num() {
        if(checkPrefix("(")) {
            Node node = expr();
            checkPrefix(")");
            return node;
        }

        String varName = getVarName();
        if(var.containsKey(varName)) {
            return new Node(var.get(varName));
        }

        return new Node(getNum());
    }

    /* getNum : 式の先頭から字を読み取る */
    private double getNum() {
        skipSpace();
        double num = 0;
        int idx = 0;
        for(; idx < expr.length(); ++ idx) {
            if('0' <= expr.charAt(idx) && expr.charAt(idx) <= '9') {
                num = num * 10 + expr.charAt(idx) - '0';
            }
            else {
                break;
            }
        }
        expr = expr.substring(idx, expr.length());
        return num;
    }

    /* getVarName ; 式の先頭から変数名を取り出す */
    private String getVarName() {
        skipSpace();
        int idx = 0;
        for(; idx < expr.length(); ++ idx) {
            char ch = expr.charAt(idx);
            if(('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'z') || ch == '_'){}
            else break;
        }

        String varName = expr.substring(0, idx);
        expr = expr.substring(idx, expr.length());
        return varName;
    }

    /* skipSpace : 式先頭にある空白を読み飛ばす*/
    private void skipSpace() {
        while(expr.length() > 0 && expr.charAt(0) == ' ') {
            expr = expr.substring(1, expr.length());
        }
    }

    /* checkPrefix : 式の先頭の文字列をチェックする */
    private boolean checkPrefix(String prefix) {
        skipSpace();
        if(expr.startsWith(prefix)) {
            expr = expr.substring(prefix.length(), expr.length());
            return true;
        }
        return false;
    }
}