import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 수열의 크기
		
		// 입력받은 높이 저장
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 오르막길인지 탐색(가장 크기가 큰 오르막길 저장) */
		int max = 0; // 오르막길의 최대 높이
		int sum = 0; // 오르막길의 높이
		for (int i = 0; i < N - 1; i++) {
			if(arr[i] < arr[i + 1]) { // 오르막길인 경우
				sum += arr[i + 1] - arr[i]; // 현재의 차이를 오르막길의 높이에 더해줌
			} else { // 오르막길이 끝난 경우
				sum = 0; // 오르막길 높이 초기화
			}
			max = Integer.max(max, sum); 
		}
		System.out.println(max);
	}
}
