package ESCompiler;

public class Node {
    public Node left, right;
    public double value;
    public int kind;

    public Node(Node left, Node right, double value, int kind){
        this.left = left;
        this.right = right;
        this.value = value;
        this.kind = kind;
    }
}
