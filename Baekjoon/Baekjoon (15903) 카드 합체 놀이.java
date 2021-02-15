import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken()); // 카드의 개수
		int m = Integer.parseInt(st.nextToken()); // 카드 합체 횟수
		long res = 0; // 최종 결과 변수
		PriorityQueue<Long> pque = new PriorityQueue<>(); // 우선순위 큐(오름차순 정렬, 카드의 상태를 표현)
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			pque.offer(Long.parseLong(st.nextToken()));
		}
		
		/* 카드 합체 횟수만큼 반복. 제일 위에 2개(최소값 2개)의 합을 2번 넣어줌 */
		for (int i = 0; i < m; i++) {
			long sum = pque.poll() + pque.poll();
			pque.offer(sum);
			pque.offer(sum);
		}
		
		while(!pque.isEmpty()) {
			res += pque.poll();
		}
		
		System.out.println(res);
	}
}
