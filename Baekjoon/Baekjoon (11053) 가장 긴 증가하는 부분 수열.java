import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 수열의 크기
		int[] arr = new int[N]; // 수열의 원소들을 저장할 배열
		int[] LIS = new int[N]; // 각 원소를 마지막에 세웠을 때의 최장 길이
		int max; // 결과 변수(가장 긴 증가하는 부분 수열의 길이)
		
		// 입력 받은 수열의 원소 저장
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.fill(LIS, 1); // 자기 혼자 세웠을 때의 길이로 초기화
		
		max = 0;
		for (int i = 0; i < N; i++) {
			// 맨 앞부터 자신의 직전 원소들과 비교
			for (int j = 0; j < i; j++) {
				if(arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {
					LIS[i] = LIS[j] + 1;
				}
			}
			
			// 가장 긴 증가하는 부분 수열의 길이 저장
			if(max < LIS[i]) 
				max = LIS[i];
		}
		System.out.println(max);
	}
}
