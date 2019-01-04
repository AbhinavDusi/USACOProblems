import java.io.*; 
import java.util.*; 
public class SubsequencesSummingToSevens {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("div7.in"));
		PrintWriter pw = new PrintWriter(new File("div7.out"));
		int n = Integer.parseInt(br.readLine()), m = 0, sum = 0, ret = 0; 
		Map<Integer, Integer> map = new HashMap<Integer, Integer>(); 
		for (int i = 0; i < n; i ++) {
			sum += Integer.parseInt(br.readLine()) % 7; 
			if (sum - m >= 7) 
				m += 7; 
			if (!map.containsKey(sum))
				map.put(sum, i); 
			if (map.containsKey(sum - m)) 
				ret = Math.max(ret, i - map.get(sum - m)); 
		}
		pw.println(ret);
		br.close();
		pw.close();
		System.exit(0); 
	}
}