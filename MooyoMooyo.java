import java.io.*;
import java.util.*; 
public class MooyoMooyo {
    static int[][] matrix; 
    static int n; 
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
        PrintWriter pw = new PrintWriter(new File("mooyomooyo.out")); 
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = line[0], K = line[1]; 
        matrix = new int[N][10]; 
        for (int i = 0; i < N; i ++) 
        	matrix[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        mooyo(K); 
        for (int[] i : matrix) {
        	for (int j : i)
        		pw.print(j);
        	pw.println();
        }
        br.close();
        pw.close(); 
        System.exit(0);
	}
	static void mooyo(int k) {
		boolean hasChanged = true; 
		while (hasChanged) {
			hasChanged = false; 
			boolean[][] visited = new boolean[matrix.length][matrix[0].length]; 
			for (int i = 0; i < matrix.length; i ++) 
				for (int j = 0; j < matrix[0].length; j ++) 
					if (!visited[i][j] && matrix[i][j] != 0) {
						n = 0; 
						num(visited, i, j, matrix[i][j]);
						if (n >= k) {
							fillZero(i, j, matrix[i][j]);
							hasChanged = true; 
						}
					}
			drop();
		}
	}
	static void num(boolean[][] visited, int i, int j, int c) {
		n ++; 
		visited[i][j] = true; 
		if (i < matrix.length - 1)
			if (matrix[i + 1][j] != 0 && matrix[i + 1][j] == c && !visited[i + 1][j]) 
				num(visited, i + 1, j, c);
		if (i > 0)
			if (matrix[i - 1][j] != 0 && matrix[i - 1][j] == c && !visited[i - 1][j]) 
				num(visited, i - 1, j, c);
		if (j < matrix[0].length - 1)
			if (matrix[i][j + 1] != 0 && matrix[i][j + 1] == c && !visited[i][j + 1]) 
				num(visited, i, j + 1, c);
		if (j > 0)
			if (matrix[i][j - 1] != 0 && matrix[i][j - 1] == c && !visited[i][j - 1]) 
				num(visited, i, j - 1, c);
	}
	static void fillZero(int i, int j, int c) {
		matrix[i][j] = 0;
		if (i < matrix.length - 1)
			if (matrix[i + 1][j] != 0 && matrix[i + 1][j] == c) 
				fillZero(i + 1, j, c);
		if (i > 0)
			if (matrix[i - 1][j] != 0 && matrix[i - 1][j] == c) 
				fillZero(i - 1, j, c);
		if (j < matrix[0].length - 1)
			if (matrix[i][j + 1] != 0 && matrix[i][j + 1] == c) 
				fillZero(i, j + 1, c);
		if (j > 0)
			if (matrix[i][j - 1] != 0 && matrix[i][j - 1] == c) 
				fillZero(i, j - 1, c);
	}
	static void drop() {
		for (int j = 0; j < matrix[0].length; j ++) {
			boolean changed = true; 
			while (changed) {
				changed = false; 
				for (int i = matrix.length - 1; i >= 1; i --) 
					if (matrix[i][j] == 0 && matrix[i - 1][j] != 0) {
						changed = true; 
						matrix[i][j] = matrix[i - 1][j]; 
						matrix[i - 1][j] = 0; 
					}
			}
		}
	}
}
