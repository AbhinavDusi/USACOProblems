import java.io.*; 
import java.util.*; 
public class Lifeguards {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lifeguards.in"));
        PrintWriter pw = new PrintWriter(new File("lifeguards.out"));
        ArrayList<int[]> lifeguards = new ArrayList<int[]>(); 
        int guards = Integer.parseInt(br.readLine()); 
        for (int i = 0; i < guards; i ++) {
        	int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        	lifeguards.add(new int[] {line[0], line[1]}); 
        }
        pw.println(answer(lifeguards, guards)); 
        br.close();
        pw.close();  
    }
    static int answer(ArrayList<int[]> lifeguards, int numGuards) {
    	Collections.sort(lifeguards, new sortByStart());
    	int[] maxA = new int[numGuards], uniqueA = new int[numGuards];
    	maxA[0] = lifeguards.get(0)[1]; 
    	uniqueA[0] = lifeguards.get(0)[1] - lifeguards.get(0)[0]; 
    	for (int i = 1; i < numGuards; i ++) {
    		int[] currLG = lifeguards.get(i); 
    		if (currLG[0] > maxA[i - 1]) {
    			maxA[i] = currLG[1]; 
    			uniqueA[i] = currLG[1] - currLG[0]; 
    		}
    		else { 
    			uniqueA[i] = currLG[1] - maxA[i - 1] > 0 ? currLG[1] - maxA[i - 1] : 0; 
    			maxA[i] = Math.max(maxA[i - 1], currLG[1]); 
    		}
    	}
    	int[] maxB = new int[numGuards], uniqueB = new int[numGuards]; 
    	Collections.reverse(lifeguards);
    	maxB[0] = lifeguards.get(0)[0]; 
    	uniqueB[0] = lifeguards.get(0)[1] - lifeguards.get(0)[0]; 
    	for (int i = 1; i < numGuards; i ++) {
    		int[] currLG = lifeguards.get(i); 
    		if (currLG[1] < maxB[i - 1]) {
    			maxB[i] = currLG[0]; 
    			uniqueB[i] = currLG[1] - currLG[0]; 
    		}
    		else {
    			uniqueB[i] = maxB[i - 1] - currLG[0] > 0 ? maxB[i - 1] - currLG[0] : 0; 
    			maxB[i] = Math.min(maxB[i - 1], currLG[0]); 
    		}
    	}
    	int[] uniquePerGuard = new int[numGuards]; 
    	for (int i = 0; i < numGuards; i ++) {
    		int[] currLG = lifeguards.get(i); 
    		int time = currLG[1] - currLG[0], a = uniqueB[i], b = uniqueA[uniqueA.length - i - 1]; 
    		uniquePerGuard[i] = time - (time - a + time - b); 
    	}
    	int sum = 0, smallest = Integer.MAX_VALUE; 
    	for (int i = 0; i < uniquePerGuard.length; i ++) {
    		sum += uniqueA[i]; 
    		smallest = Math.min(smallest, uniquePerGuard[i]); 
    	}
    	smallest = smallest < 0 ? 0 : smallest; 
    	return sum - smallest;   
    }
}
class sortByStart implements Comparator<int[]> {
	public int compare (int[] a, int[] b) {
		return a[0] - b[0]; 
	}
}
