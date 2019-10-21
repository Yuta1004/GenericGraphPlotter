package ESParser;

public class Node {
    public Node left, right;
    public double value;
    public NodeKind kind;

    public Node(Node left, Node right, double value, NodeKind kind){
        this.left = left;
        this.right = right;
        this.value = value;
        this.kind = kind;
    }
}
