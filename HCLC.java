import java.io.*; 
import java.util.*; 
public class HCLC {
        public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cardgame.in")); 
        PrintWriter pw = new PrintWriter(new File("cardgame.out"));
        int numCards = Integer.parseInt(br.readLine()); 
        int[] eCards = new int[numCards]; 
        for (int i = 0; i < numCards; i ++)
            eCards[i] = Integer.parseInt(br.readLine()); 
        pw.println(answer(eCards)); 
        br.close();
        pw.close(); 
    }
    public static int answer(int[] eCards) {
        TreeSet<Integer> listA = new TreeSet<>(), listB = new TreeSet<>(); 
        int wins = eCards.length; 
        int[] bCards = new int[eCards.length]; 
        boolean[] eHas = new boolean[eCards.length *  2 + 1];
        for (int i : eCards) 
            eHas[i] = true; 
        for (int i = 1, count = 0; i < eHas.length; i ++)
            if (!eHas[i])
                bCards[count ++] = i; 
        Arrays.sort(bCards); 
        for (int i = 0; i < bCards.length; i ++)
            if (i >= bCards.length / 2) 
                listB.add(bCards[i]);
            else
                listA.add(bCards[i]);
        for (int i = 0; i < eCards.length; i ++) 
            if (i < eCards.length / 2) 
                if (listB.higher(eCards[i]) != null) 
                    listB.remove(listB.higher(eCards[i])); 
                else {
                    listB.remove(listB.first()); 
                    wins --; 
                }
            else 
                if (listA.lower(eCards[i]) != null) 
                    listA.remove(listA.lower(eCards[i])); 
                else {
                    listA.remove(listA.last()); 
                    wins --; 
                }
        return wins; 
    }
}