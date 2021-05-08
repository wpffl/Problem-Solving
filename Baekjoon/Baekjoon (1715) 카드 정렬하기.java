import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 카드 묶음 개수
		long res = 0; // 결과 변수

		// 입력 받은 카드 묶음 크기 저장
		PriorityQueue<Long> pqueue = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			pqueue.offer(Long.parseLong(br.readLine()));
		}

		// 비교 횟수 계산
		for (int i = 0; i < N - 1; i++) { // N - 1번 비교
			long sum = pqueue.poll() + pqueue.poll(); // 가장 크기가 작은 카드 두 묶음 빼서 비교 횟수 계산
			pqueue.offer(sum);
			res += sum;
		}
		System.out.println(res);
	}
}
