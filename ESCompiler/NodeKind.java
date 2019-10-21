package ESCompiler;

public enum NodeKind {
    UNDEFINED(0),
    NUM(1),
    ADD(2),
    SUB(3),
    MUL(4),
    DIV(5),
    EXPR(6);

    public int val;

    NodeKind(int val) {
       this.val = val;
    } 
}
