import java.io.*;
import java.util.*; 
public class SnowBoots {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("snowboots.in"));
        PrintWriter pw = new PrintWriter(new File("snowboots.out"));
        ArrayList<int[]> boots = new ArrayList<int[]>(); 
        int numBoots = Integer.parseInt(br.readLine().split(" ")[1]); 
        int[] tiles = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < numBoots; i ++)
        		boots.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()); 
        pw.println(answer(boots, tiles)); 
        br.close();
        pw.close();
        System.exit(0);
    }
    public static int answer(ArrayList<int[]> boots, int[] tiles) {
    	int[] minDiscard = new int[tiles.length]; 
    	Arrays.fill(minDiscard, Integer.MAX_VALUE);
    	minDiscard[0] = 0; 
    	int discarded = 0; 
    	for (int[] boot : boots) {
    		for (int i = 0; i < tiles.length; i ++) 
    			for (int j = 1; j <= boot[1]; j ++) 
    				if (i - j >= 0 && minDiscard[i - j] != Integer.MAX_VALUE && tiles[i] <= boot[0] && tiles[i - j] <= boot[0]) 
    					minDiscard[i] = discarded; 
    		if (minDiscard[minDiscard.length - 1] != Integer.MAX_VALUE)
    			return minDiscard[minDiscard.length - 1]; 
    		discarded ++; 
    	}
    	return -1;
    }
}