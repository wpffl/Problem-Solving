import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 문제 입력 값에 선행되는 작업의 목록이 주어져서 DP로도 풀 수 있다.
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine()); // 수행할 작업 개수
		int[] dp = new int[N]; // 각각의 작업을 수행하는데 걸리는 시간
		int res = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int time = Integer.parseInt(st.nextToken()); // 작업하는데 걸리는 시간
			int cnt = Integer.parseInt(st.nextToken()); // 선행 작업의 개수

			dp[i] = time;
			
			for (int j = 0; j < cnt; j++) {
				int preJob = Integer.parseInt(st.nextToken()) - 1;
				
				dp[i] = Math.max(dp[i], dp[preJob] + time); // 가장 긴 작업 시간으로 설정(점화식)
			}
			
			res = Math.max(res, dp[i]);
		}
		
		bw.write(String.valueOf(res));
		bw.flush();
	}
}
