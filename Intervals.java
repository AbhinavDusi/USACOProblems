import java.io.*;
import java.util.*;
import java.text.*;
public class Intervals {
    private static Set<Interval> retSet;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(" ");
            double start = Double.parseDouble(data[0]), end = Double.parseDouble(data[1]);
            int num = Integer.parseInt(br.readLine());
            ArrayList<Interval> intervals = new ArrayList<>();
            for (int i = 0; i < num; i ++) {
                data = br.readLine().split(" ");
                double dstart = Double.parseDouble(data[0]), dend = Double.parseDouble(data[1]);
                dstart = Math.max(dstart, start);
                dend = Math.min(dend, end);
                intervals.add(new Interval(dstart, dend));
            }
            Map<Interval, Integer> map = new HashMap<>();
            for (int i = 0; i < intervals.size(); i ++) map.put(intervals.get(i), i);
            Collections.sort(intervals, new SortByEnd());
            double[] endTimes = new double[intervals.size()];
            for (int i = 0; i < intervals.size(); i ++) endTimes[i] = intervals.get(i).end;
            int result = num(start, end, intervals, endTimes);
            if (result != -1) {
                pw.println(result);
                TreeSet<Integer> indicies = new TreeSet<>();
                for (Interval i : retSet) indicies.add(map.get(i));
                for (int i : indicies) pw.print(i + " ");
                pw.println();
            } else pw.println("impossible");
        }
        br.close();
        pw.close();
        System.exit(0);
    }
    public static int num(double start, double end, ArrayList<Interval> intervals, double[] endTimes) {
        DecimalFormat df = new DecimalFormat("##.000");
        int n = intervals.size(), res = Integer.MAX_VALUE;
        int[] dp = new int[n];
        dp[0] = 1;
        double[] values = new double[n];
        Set[] intList = new Set[n];
        for (int i = 0; i < intList.length; i ++) {
            intList[i] = new HashSet<Interval>();
            intList[i].add(intervals.get(i));
        }
        for (int i = 0; i < n; i ++) {
            double thisStart = intervals.get(i).start, thisEnd = intervals.get(i).end;
            int index = Arrays.binarySearch(endTimes, thisStart);
            index = index <= -1 ? -index - 1 : index;
            if (intervals.get(index).start >= thisStart) {
                dp[i] = dp[index];
                intList[i].addAll(intList[index]);
                if (i != index) intList[i].remove(intervals.get(index));
                values[i] = thisEnd - Math.min(thisStart, intervals.get(index).start);
            } else {
                dp[i] = dp[index] + 1;
                intList[i].addAll(intList[index]);
                values[i] = values[index] + thisEnd - Math.max(intervals.get(index).end, thisStart);
            }
            values[i] = Double.parseDouble(df.format(values[i]));
        }
        double result = Double.parseDouble(df.format(end - start));
        for (int i = 0; i < values.length; i ++)
            if (values[i] == result && dp[i] < res) {
                res = dp[i];
                retSet = intList[i];
            }
        return res != Integer.MAX_VALUE ? res : -1;
    }
}
class Interval {
    public double start, end;
    public Interval(double start, double end) {
        this.start = start;
        this.end = end;
    }
}
class SortByEnd implements Comparator<Interval> {
    public int compare(Interval a, Interval b) {
        if (a.end > b.end) return 1;
        else if (b.end < a.end) return -1;
        else return a.start > b.start ? 1 : -1;
    }
}
