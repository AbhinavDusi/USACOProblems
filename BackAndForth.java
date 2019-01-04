import java.io.*;
import java.util.*; 
public class BackAndForth {
	static Set<Integer> combinations = new HashSet<Integer>(); 
	public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("backforth.in"));
        PrintWriter pw = new PrintWriter(new File("backforth.out"));
        ArrayList<Integer> a = new ArrayList<Integer>(), b = new ArrayList<Integer>(); 
        for (int i = 0; i < 10; i ++)
        	a.add(s.nextInt()); 
        for (int i = 0; i < 10; i ++)
        	b.add(s.nextInt()); 
        getCombinations(a, b, 0, 1000, false); 
        pw.println(combinations.size()); 
        pw.close();
        s.close();
        System.exit(0);
	}
	public static void getCombinations(ArrayList<Integer> a, ArrayList<Integer> b, int day, int amount, boolean side) {
		if (day == 4) 
			combinations.add(amount);
		else {
			if (side) 
				for (int i = 0; i < b.size(); i ++) {
					int val = b.get(i); 
					a.add(val);
					b.remove(i); 
					getCombinations(a, b, day + 1, amount + val, !side); 
					b.add(i, val); 
					a.remove(a.size() - 1);
				}
			else 
				for (int i = 0; i < a.size(); i ++) {
					int val = a.get(i); 
					b.add(val);
					a.remove(i); 
					getCombinations(a, b, day + 1, amount - val, !side); 
					a.add(i, val);
					b.remove(b.size() - 1);
				}
		}
	}
}
