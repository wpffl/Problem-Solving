import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine()); // 스티커 구매 개수
			int[][] stickers = new int[2][N];

			// 입력받은 스티커의 점수 저장
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					stickers[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bw.write(sol(N, stickers) + "\n");
		}
		bw.flush();
	}

	/* 2n개의 스티커 중에서 두 변을 공유하지 않는 스티커 점수의 최댓값을 구하는 함수 */
	private static int sol(int N, int[][] stickers) {
		int[][] dp = new int[2][N]; // 해당 시점에서 스티커 점수의 최댓값

		// 초기 설정
		dp[0][0] = stickers[0][0];
		dp[1][0] = stickers[1][0];
		dp[0][1] = stickers[1][0] + stickers[0][1];
		dp[1][1] = stickers[0][0] + stickers[1][1];

		for (int i = 2; i < N; i++) {
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					// 가운데꺼 빼라
					// 왼쪽 아래, 2열 앞, 2열 앞 아래
					dp[j][i] = Math.max(dp[j + 1][i - 1], dp[j + 1][i - 2]);
					dp[j][i] += stickers[j][i];
				} else {
					// 왼쪽 위, 2열 앞, 2열 앞 위
					dp[j][i] = Math.max(dp[j - 1][i - 1], dp[j - 1][i - 2]);
					dp[j][i] += stickers[j][i];
				}
			}
		}

		return Math.max(dp[0][N - 1], dp[1][N - 1]);
	}
}
