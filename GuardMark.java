import java.io.*; 
import java.util.*; 
public class GuardMark {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("guard.in")); 
		PrintWriter pw = new PrintWriter(new File("guard.out"));
		int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); 
		int cows = line[0], height = line[1], res = 0; 
		int[] h = new int[cows], w = new int[cows], s = new int[cows]; 
		boolean reached = false; 
		for (int i = 0; i < cows; i ++) {
			line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); 
			h[i] = line[0];
			w[i] = line[1];
			s[i] = line[2]; 
		}
		int[] dp = new int[1 << cows], hdp = new int[1 << cows]; 
		Arrays.fill(dp, Integer.MAX_VALUE); 
		for (int i = 1; i < dp.length; i ++) {
			int best = 0;
			for (int j = 0; j < cows; j ++) 
				if ((i & (1 << j)) != 0 && Math.min(s[j], dp[i ^ (1 << j)] - w[j]) > best) {
					best = Math.min(s[j], dp[i ^ (1 << j)] - w[j]); 
					hdp[i] = hdp[i ^ (1 << j)] + h[j];
				} 
			dp[i] = best; 
			if (hdp[i] >= height) {
				res = Math.max(res, dp[i]); 
				reached = true; 
			}
		}
		if (reached)
			pw.println(res);
		else
			pw.println("Mark is too tall"); 
		br.close();
		pw.close();
		System.exit(0);
	}
}