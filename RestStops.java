import java.io.*;
import java.util.*; 
public class RestStops {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("reststops.in"));
        PrintWriter pw = new PrintWriter(new File("reststops.out"));
        long[] numbers = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long length = numbers[0], numStops = numbers[1], fSpeed = numbers[2], bSpeed = numbers[3]; 
        TreeMap<Long, Long> stops = new TreeMap<Long, Long>();  
        for (int i = 0; i < numStops; i ++) {
        	numbers = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        	stops.put(numbers[1], numbers[0]); 
        }
        pw.println(answer(fSpeed, bSpeed, length, stops)); 
        br.close();
        pw.close();
        System.exit(0);
    }
    public static long answer(long fSpeed, long bSpeed, long length, TreeMap<Long, Long> stops) {
    	long bDistance = 0, fDistance = 0, total = 0, nextStop = stops.lastKey(); 
    	long[] values = new long[(int)length + 1]; 
    	for (long i : stops.keySet()) {
    		long temp = stops.get(i); 
    		values[(int)temp] = i; 
    	}
    	for (int i = 1; i < length + 1; i ++) {
    		bDistance += bSpeed;
    		fDistance += fSpeed; 
    		if (values[i] == nextStop) {
    			stops.remove(nextStop); 
    			total += (fDistance - bDistance) * nextStop; 
    			bDistance = fDistance = 0; 
    			if (!stops.isEmpty())
    				nextStop = stops.lastKey(); 
    		}
    		else if (values[i] != 0)
    			stops.remove(values[i]); 
    	}
    	return total; 
    }
}