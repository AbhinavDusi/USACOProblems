import java.io.*; 
import java.util.*; 
public class Mootube
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("mootube.in")); 
        PrintWriter pw = new PrintWriter(new File("mootube.out"));
        List<int[]> nValues = new ArrayList<>(), qValues = new ArrayList<>();
        List<Integer> ans; 
        String line = br.readLine(); 
        int n = Integer.parseInt(line.split(" ")[0]), q = Integer.parseInt(line.split(" ")[1]); 
        for (int i = 0; i < n - 1; i ++)
        {
            String[] sValues = br.readLine().split(" "); 
            int[] values = new int[3];
            values[0] = Integer.parseInt(sValues[0]); 
            values[1] = Integer.parseInt(sValues[1]); 
            values[2] = Integer.parseInt(sValues[2]); 
            nValues.add(values); 
        }
        for (int i = 0; i < q; i ++)
        {
            String[] sValues = br.readLine().split(" "); 
            int[] values = new int[2];
            values[0] = Integer.parseInt(sValues[0]); 
            values[1] = Integer.parseInt(sValues[1]); 
            qValues.add(values); 
        }
        ans = answer(nValues, qValues); 
        for (int i : ans) pw.println(i); 
        br.close();
        pw.close(); 
        System.exit(0); 
    }
    static List<Integer> answer (List<int[]> nValues, List<int[]> qValues)
    {
        Graph graph = new Graph(nValues.size() + 1); 
        List<Integer> ans = new ArrayList<>(); 
        for (int[] i : nValues)
        {
            graph.addEdge(i[0], i[1], i[2]);
            graph.addEdge(i[1], i[0], i[2]);
        } 
        for (int[] i : qValues)
        {
            graph.setMax(i[0]); 
            graph.fillInEdges(i[1], new boolean[nValues.size() + 1], Integer.MAX_VALUE); 
       	    ans.add(graph.getCount()); 
       	    graph.resetTCount(); 
        }
        return ans; 
    }
}
class Graph
{
    private Map<Integer, List<int[]>> adjacencies;
    private int tempCount, max; 
    Graph (int verticies)
    {
        adjacencies = new HashMap<>(); 
        for (int i = 1; i <= verticies; i ++)
        		adjacencies.put(i, new ArrayList<>());
    }
    void addEdge(int v, int n, int w)
    {
        adjacencies.get(v).add(new int[] {n, w}); 
    }
    void fillInEdges(int v, boolean[] visited, int weight)
    {
        visited[v - 1] = true; 
        for (int[] i : adjacencies.get(v))
            if (!visited[i[0] - 1] && Math.min(weight, i[1]) >= max)
            {
            	tempCount ++; 
                fillInEdges(i[0], visited, Math.min(weight, i[1]));
            }
    }
    void setMax(int max)
    {
    	this.max = max; 
    }
    void resetTCount()
    {
    	tempCount = 0; 
    }
    int getCount()
    {
    	return tempCount; 
    }
}