import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Deque<Integer> deq = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			deq.add(i);
		}
		
		while(N > 1) {
			deq.pollFirst();
			int tmp = deq.pollFirst();
			deq.add(tmp);
			N--;
		}
		
		System.out.println(deq.poll());		
	}
}
