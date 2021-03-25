import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class D3_3307 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine()); // 수열의 길이
			int[] arr = new int[N]; // 수열을 저장할 배열
			int[] dp = new int[N]; // dp[i]: i번째 요소를 끝에 붙여 만들 수 있는 최장 길이
			
			// 입력 받은 수열의 원소 저장
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				dp[i] = 1; // 원소 혼자 세웠을 때의 길이로 초기화
			}
			
			int max = 0; // 부분 수열의 최장 길이를 저장할 변수
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < i; j++) {
					// 맨 앞부터 자신의 직전 원소들까지 비교
					if(arr[j] < arr[i] && dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;
					}
				}
				max = Math.max(max, dp[i]);
			}
			bw.write("#" + t + " " + max);
			bw.newLine();
		}
		br.close();
		bw.close();
	}
}
