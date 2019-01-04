import java.util.*;
import java.io.*;
public class HaybaleFeast {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("haybales.in")); 
        PrintWriter pw = new PrintWriter(new File("haybales.out"));
        String line = br.readLine(); 
        List<long[]> haybales = new ArrayList<>(); 
        long[] pair; 
        long n, flavor;
        n = Long.parseLong(line.split(" ")[0]);
        flavor = Long.parseLong(line.split(" ")[1]); 
        for (int i = 0; i < n; i ++) {
                pair = new long[2];
                line = br.readLine(); 
                pair[0] = Long.parseLong(line.split(" ")[0]); 
                pair[1] = Long.parseLong(line.split(" ")[1]); 
                haybales.add(pair); 
        }
        pw.println(answer(haybales, flavor));
        br.close();
        pw.close(); 
    }
    public static long answer(List<long[]> haybales, long flavor) {
        TreeMap<Long, Long> tm = new TreeMap<>(); 
        long sum = haybales.get(0)[0], start = 0, end = 1, minSpice = 1000000000;
        updateMap(tm, haybales.get(0)[1], 1); 
        while (end < haybales.size()) {
            while (sum < flavor && end < haybales.size()) { 
                updateMap(tm, haybales.get((int)end)[1], 1);
                sum += haybales.get((int)end)[0]; 
                end ++; 
            }
            while (sum >= flavor && start < haybales.size()) {
                updateMap(tm, haybales.get((int)start)[1], -1);
                minSpice = Math.min(minSpice, tm.lastKey()); 
                sum -= haybales.get((int)start)[0];
                start ++; 
            }
        }
        return minSpice; 
    }
    public static void updateMap(TreeMap<Long, Long> tm, long key, long value) {
        if (!tm.containsKey(key))
            tm.put(key, (long)0);
        if (tm.get(key) + value == 0)
            tm.remove(key); 
        else 
            tm.put(key, tm.get(key) + value);
    }
}
