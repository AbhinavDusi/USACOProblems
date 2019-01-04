import java.io.*;
import java.util.*;
public class WeightedIntervalScheduling {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("scheduling.in"));
    PrintWriter pw = new PrintWriter(new File("scheduling.out"));
    ArrayList<Job> jobList = new ArrayList<Job>();
    int numJobs = Integer.parseInt(br.readLine()), max = 0;
    for (int i = 0; i < numJobs; i ++) {
      int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      jobList.add(new Job(line[0], line[1], line[2]));
    }
    Collections.sort(jobList, new CompareByFinish());
    int[] dp = new int[numJobs + 1], endTimes = new int[numJobs + 1];
    for (int i = 0; i < numJobs; i ++)
        endTimes[i + 1] = jobList.get(i).end;
    for (int i = 1; i < dp.length; i ++) {
        int index = Arrays.binarySearch(endTimes, jobList.get(i - 1).start);
        index = index <= -1 ? -index - 2 : index;
        dp[i] = Math.max(dp[i - 1], dp[index] + jobList.get(i - 1).weight);
        max = Math.max(max, dp[i]);
    }
    pw.println(max);
    br.close();
    pw.close();
    System.exit(0);
  }
}
class Job {
  public int start, end, weight;
  public Job(int start, int end, int weight) {
    this.start = start;
    this.end = end;
    this.weight = weight;
  }
}
class CompareByFinish implements Comparator<Job> {
  public int compare(Job a, Job b) {
    return a.end - b.end;
  }
}
