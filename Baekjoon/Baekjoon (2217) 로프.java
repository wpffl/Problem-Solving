import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()); // 로프의 개수
		int[] weight = new int[N]; // 로프의 최대 중량 저장하는 배열
		
		// 입력받은 로프의 최대 중량 저장
		for (int i = 0; i < N; i++) {
			weight[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(weight); // 오름차순으로 정렬
		
		/* 최대 중량이 큰 로프 순으로 꺼내면서 연결 */
		int maxWeight = 0;
		
		for (int i = N -1; i >= 0; i--) {
			weight[i] = weight[i] * (N - i);
			maxWeight = Math.max(maxWeight, weight[i]);
		}
		
		bw.write(String.valueOf(maxWeight));
		bw.flush();
	}
}
