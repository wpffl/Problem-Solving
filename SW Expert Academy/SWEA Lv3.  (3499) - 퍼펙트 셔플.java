import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            Queue<String> card1Q = new LinkedList<>();
            Queue<String> card2Q = new LinkedList<>();
 
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < (N + 1) / 2; i++) {
                card1Q.offer(st.nextToken());
            }
 
            for (int i = 0; i < N / 2; i++) {
                card2Q.offer(st.nextToken());
            }
 
            sb.append("#" + t + " ");
            while (!card2Q.isEmpty()) {
                sb.append(card1Q.poll() + " ");
                sb.append(card2Q.poll() + " ");
            }
            if(N % 2 != 0)
                sb.append(card1Q.poll() + "\n");
            else
                sb.append("\n");
 
        }
        System.out.println(sb.toString());
    }
}
