import java.io.*;
import java.util.*; 
public class MixingMilk {
	public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("mixmilk.in"));
        PrintWriter pw = new PrintWriter(new File("mixmilk.out")); 
        int[] capacities = new int[3], milks = new int[3]; 
        for (int i = 0; i < 3; i ++) {
        	capacities[i] = s.nextInt();
        	milks[i] = s.nextInt(); 
        }
        for (int i = 0; i < 100; i ++) 
        	pour(i % 3, (i + 1) % 3, capacities, milks); 
        for (int i : milks)
        	pw.println(i);
        pw.close();
        s.close();
        System.exit(0);
	}
	public static void pour(int i, int j, int[] capacities, int[] milks) {
		int amount = Math.min(milks[i], capacities[j] - milks[j]);
		milks[i] -= amount;
		milks[j] += amount;
	}
}
