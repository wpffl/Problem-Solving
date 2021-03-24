package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1149 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 집의 수
		int[][] dp = new int[N + 1][3]; // 집을 빨강, 초록, 파랑으로 칠하는 비용
		
		// 입력받은 데이터 저장(각 집마다 빨강, 초록, 파랑으로 칠하는 비용)
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			dp[i][0] = Integer.parseInt(st.nextToken()); // 빨강
			dp[i][1] = Integer.parseInt(st.nextToken()); // 초록
			dp[i][2] = Integer.parseInt(st.nextToken()); // 파랑
		
		}
		
		for (int i = 1; i <= N; i++) {
			dp[i][0] += Math.min(dp[i-1][1], dp[i-1][2]);
			dp[i][1] += Math.min(dp[i-1][0], dp[i-1][2]);
			dp[i][2] += Math.min(dp[i-1][0], dp[i-1][1]);
		}
		
		System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
	}

}
