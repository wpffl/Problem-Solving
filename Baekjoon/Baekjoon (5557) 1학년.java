import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] num;
	static long[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 숫자의 개수

		// 입력받은 숫자 저장
		num = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		dp = new long[21]; // dp[1]: 1이 연산 결과 값일때 만들 수 있는 경우의 수
		dp[num[0]] = 1; // 첫번째 수 표시

		for (int i = 0; i < N - 2; i++) {
			dp = cal(i + 1); // i + 1: 연산할 수의 index
		}

		System.out.println(dp[num[N - 1]]); // 최종 연산 결과(num의 마지막 원소)의 경우의 수 출력
	}

	private static long[] cal(int idx) {
		long[] newdp = new long[21]; 
		
		for (int i = 0; i < 21; i++) {
			if (dp[i] != 0) {
				if (i + num[idx] >= 0 && i + num[idx] <= 20) { // 덧셈인 경우
					newdp[i + num[idx]] += dp[i];
				}

				if (i - num[idx] >= 0 && i - num[idx] <= 20) { // 뺄셈인 경우
					newdp[i - num[idx]] += dp[i];
				}
			}
		}

		return newdp;
	}
}
