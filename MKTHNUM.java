import java.io.*; 
import java.util.*; 
import java.util.stream.*;
public class MKTHNUM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/abhinavdusi/Desktop/in.in"));
        PrintWriter pw = new PrintWriter(new File("/Users/abhinavdusi/Desktop/out.out"));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], q = line[1]; 
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> rarr = IntStream.of(nums).boxed().collect(Collectors.toList());
        Node root = new Node(new ArrayList(rarr)); 
        root.constructTree(); 
        for (int i = 0; i < q; i ++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int l = line[0], r = line[1], k = line[2]; 
            pw.println(root.query(l, r, k)); 
        }
        br.close();
        pw.close();  
        System.exit(0); 
    }
}
class Node {
    public ArrayList<Integer> array, lBefore;
    public Node l, r; 
    public Node(ArrayList<Integer> array) {
        this.array = array;
        lBefore = new ArrayList<Integer>(); 
    }
    public void constructTree() {
        int small = Integer.MAX_VALUE, large = 0, totalL = 0, mid;
        ArrayList<Integer> lChild = new ArrayList<>(), rChild = new ArrayList<>(); 
        for (int i : array) {
            small = Math.min(small, i); 
            large = Math.max(large, i); 
        }
        if (small == large) return; 
        lBefore.add(0); 
        for (int i : array) 
            if (i <= (small + large) / 2) {
                lChild.add(i); 
                lBefore.add(++ totalL); 
            }
            else {
                rChild.add(i); 
                lBefore.add(totalL); 
            }
        l = new Node(lChild);
        r = new Node(rChild); 
        l.constructTree();
        r.constructTree(); 
    }
    public int query(int i, int j, int k) {
        if (l == null) return array.get(0); 
        int numLeft = lBefore.get(j) - lBefore.get(i - 1); 
        if (k <= numLeft) return l.query(lBefore.get(i - 1) + 1, lBefore.get(j), k); 
        else return r.query(i - lBefore.get(i - 1), j - lBefore.get(j), k - numLeft); 
    }
} 