import java.io.*; 
import java.util.*; 
public class TheBucketList {
	public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("blist.in"));
        PrintWriter pw = new PrintWriter(new File("blist.out"));
        int N = s.nextInt(), total = 0;
        int[] times = new int[1001];
        for (int i = 0; i < N; i ++) {
        	int start = s.nextInt(), end = s.nextInt(), buckets = s.nextInt(); 
        	for (int j = start; j < end; j ++)
        		times[j] += buckets;
        }
        for (int i : times)
        	total = Math.max(total, i);
        pw.println(total);
        pw.close();
        s.close();
        System.exit(0);
	}
}
