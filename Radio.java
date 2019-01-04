import java.io.*; 
import java.util.*; 
public class Radio {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("radio.in"));
        PrintWriter pw = new PrintWriter(new File("radio.out"));
        br.readLine(); 
        int[] fCoords = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] bCoords = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String fPath = br.readLine(), bPath = br.readLine(); 
        fPath = "X" + fPath; 
        bPath = "X" + bPath; 
        int[][] dp = new int[fPath.length()][bPath.length()]; 
        for (int i = 0; i < dp.length; i ++) {
        	int[] bc = bCoords; 
        	fCoords = changeCoords(fCoords, fPath.charAt(i)); 
        	for (int j = 0; j < dp[0].length; j ++) {
        		dp[0][0] = 0; 
        		bc = changeCoords(bc, bPath.charAt(j)); 
        		dp[i][j] = getDistance(fCoords, bc) + getSmallest(i, j, dp);
        	} 
        }
        pw.println(dp[fPath.length() - 1][bPath.length() - 1]); 
        br.close();
        pw.close();  
        System.exit(0);
    }
    private static int getDistance(int[] coord1, int[] coord2) {
        return (int) Math.pow(Math.abs(coord1[0] - coord2[0]), 2) + (int) Math.pow(Math.abs(coord1[1] - coord2[1]), 2); 
    }
    private static int getSmallest(int i, int j, int[][] grid) {
    	if (i == 0 && j == 0) return grid[i][j];
    	if (i == 0) return grid[i][j - 1];
    	if (j == 0) return grid[i - 1][j]; 
    	return Math.min(Math.min(grid[i - 1][j], grid[i][j - 1]), grid[i - 1][j - 1]); 
    }
    private static int[] changeCoords(int[] coords, char d) {
    	int[] ret = new int[] {coords[0], coords[1]}; 
    	if (d == 'N') ret[1] ++;
    	if (d == 'S') ret[1] --; 
    	if (d == 'E') ret[0] ++;
    	if (d == 'W') ret[0] --; 
    	return ret; 
    }
}