public class Tree {
    private static Node root;
    public static void main(String[] args) {
        int[] values = {8, 5, 3, 6, 7, 13};
        int a = 3, b = 7;
        constructTree(values);
        System.out.println(findParent(root, a, b));
    }
    public static void constructTree(int[] values) {
        for (int i : values)
            if (root == null)
                root = new Node(i);
            else {
                Node curr = root;
                while (true) {
                    if (i < curr.val) {
                        if (curr.lChild == null) {
                            curr.lChild = new Node(i);
                            break;
                        }
                        curr = curr.lChild;
                    }
                    else {
                        if (curr.rChild == null) {
                            curr.rChild = new Node(i);
                            break;
                        }
                        curr = curr.rChild;
                    }
                }
            }
    }
    public static int findParent(Node curr, int a, int b) {
        if (a <= curr.val && b >= curr.val)
            return curr.val;
        else if (a > curr.val && curr.rChild != null)
            return findParent(curr.rChild, a, b);
        else if (curr.lChild != null)
            return findParent(curr.lChild, a, b);
        return -1;
    }
}
class Node {
    public Node rChild, lChild;
    public int val;
    public Node(int val) {
        this.val = val;
    }
}
