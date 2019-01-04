import java.io.*;
import java.util.*; 
public class Marathon {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("marathon.in"));
        PrintWriter pw = new PrintWriter(new File("marathon.out"));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], uq = line[1]; 
        Tree t = new Tree(n); 
        int[][] points = new int[n][2]; 
        for (int i = 0; i < n; i ++) {
        	line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        	points[i] = new int[] {line[0], line[1]}; 
        }
        for (int i = 1; i < points.length; i ++)  
        	t.us(0, n-1, i, gd(points[i-1], points[i]), 0);
        for (int i = 1; i < points.length - 1; i ++) 
        	t.um(0, n-1, i, t.gs(0, n-1, i, i+1, 0)-gd(points[i-1], points[i+1]), 0);
        for (int i = 0; i < uq; i ++) {
        	String[] in = br.readLine().split(" ");
        	if (in[0].equals("Q")) {
        		line = Arrays.stream(Arrays.copyOfRange(in, 1, 3)).mapToInt(Integer::parseInt).toArray();
        		pw.println(t.gs(0, n-1, line[0], line[1]-1, 0)-t.gm(0, n-1, line[0], line[1]-2, 0));
        	} 
        	else {
        		line = Arrays.stream(Arrays.copyOfRange(in, 1, 4)).mapToInt(Integer::parseInt).toArray();
            	points[line[0]-1] = new int[] {line[1], line[2]}; 
        		for (int j = line[0]-1; j < line[0]+1; j ++) 
        			if (j > 0 && j <= n-1) 
        				t.us(0, n-1, j, gd(points[j-1], points[j]), 0);
        		for (int j = line[0]-2; j < line[0]+1; j ++) 
        			if (j > 0 && j <= n-2) 
        				t.um(0, n-1, j, t.gs(0, n-1, j, j+1, 0)-gd(points[j-1], points[j+1]), 0);
        	}
        }
        br.close();
        pw.close();  
        System.exit(0);
	}
	private static int gd(int[] a, int[] b) { 
		return Math.abs(a[0]-b[0])+Math.abs(a[1]-b[1]); 
	}
}
class Tree {
	private int[] sum, max; 
	public Tree(int n) {
		max = new int[1<<((int)Math.ceil(Math.log(n)/Math.log(2))+1)]; 
		sum = new int[1<<((int)Math.ceil(Math.log(n)/Math.log(2))+1)]; 
	}
	public void um(int l, int r, int i, int val, int pos) {
		if (i < l || i > r) 
			return; 
		max[pos] = val; 
		if (r != l) {
			um(l, (r+l)/2, i, val, 2*pos+1); 
			um((r+l)/2+1, r, i, val, 2*pos+2); 
			max[pos] = Math.max(max[2*pos+1], max[2*pos+2]); 
		} 
	}
	public void us(int l, int r, int i, int val, int pos) {
		if (i < l || i > r) 
			return; 
		sum[pos] = val; 
		if (r != l) {
			us(l, (r+l)/2, i, val, 2*pos+1); 
			us((r+l)/2+1, r, i, val, 2*pos+2); 
			sum[pos] = sum[2*pos+1] + sum[2*pos+2]; 
		} 
	}
	public int gs(int l, int r, int ql, int qr, int pos) {
		if (ql <= l && qr >= r) 
			return sum[pos];
		if (r < ql || l > qr) 
			return 0; 
		return gs(l, (r+l)/2, ql, qr, 2*pos+1)+gs((r+l)/2+1, r, ql, qr, 2*pos+2);
	}
	public int gm(int l, int r, int ql, int qr, int pos) {
		if (ql <= l && qr >= r) 
			return max[pos];
		if (r < ql || l > qr) 
			return Integer.MIN_VALUE; 
		return Math.max(gm(l, (r+l)/2, ql, qr, 2*pos+1), gm((r+l)/2+1, r, ql, qr, 2*pos+2));
	}
}