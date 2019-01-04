import java.io.*;
import java.util.*;
public class Superbull {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("superbull.in"));
        PrintWriter pw = new PrintWriter(new File("superbull.out"));
        int num = Integer.parseInt(br.readLine());
        long[] teams = new long[num];
        for (int i = 0; i < num; i ++)
            teams[i] = Integer.parseInt(br.readLine());
        long[][] weights = new long[num][num];
        for (int i = 0; i < num; i ++)
            for (int j = i + 1; j < num; j ++) {
                weights[i][j] = (teams[i] ^ teams[j]) * -1;
                weights[j][i] = (teams[i] ^ teams[j]) * -1;
            }
        pw.println(prims(weights));
        br.close();
        pw.close();
        System.exit(0);
    }
    static long prims(long[][] graph) {
        int[] parent = new int[graph.length];
        long[] key = new long[graph.length];
        boolean[] mstSet = new boolean[graph.length];
        long total = 0;
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;
        for (int i = 0; i < graph.length - 1; i ++) {
            int index = -1;
            long min = Long.MAX_VALUE;
            for (int j = 0; j < graph.length; j ++)
                if (!mstSet[j] && key[j] < min) {
                    min = key[j];
                    index = j; 
                }
            mstSet[index] = true;
            for (int j = 0; j < graph.length; j ++)
                if (graph[index][j] != 0 && !mstSet[j] && graph[index][j] < key[j]) {
                    parent[j] = index;
                    key[j] = graph[index][j];
                }
        }
        for (int i = 1; i < graph.length; i++)
            total += graph[i][parent[i]];
        return total * -1;
    }
}
