import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map, dp;
	static int[] dx = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		// 입력 받은 가로 세로 저장
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로

		// 입력 받은 map의 정보 저장 & dp배열 초기화
		map = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		bw.write(String.valueOf(dfs(0, 0)));
		bw.flush();
	}

	private static int dfs(int x, int y) {
		if (x == N - 1 && y == M - 1) { // 도착지에 도착한 경우
			return 1;
		}

		if (dp[x][y] != -1) { // 이미 탐색한 경우
			return dp[x][y];
		}

		dp[x][y] = 0; // 방문 체크

		// 4방 탐색
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d]; // 다음 방문할 x 좌표
			int ny = y + dy[d]; // 다음 방문할 y 좌표

			if (isValid(nx, ny)) { // 범위 체크
				if (map[x][y] > map[nx][ny]) { // 내리막길인 경우
					dp[x][y] += dfs(nx, ny);
				}
			}
		}
		return dp[x][y];
	}

	/* 배열 범위 체크해주는 함수 */
	private static boolean isValid(int nx, int ny) {
		if (nx >= 0 && nx < N && ny >= 0 && ny < M)
			return true;
		else
			return false;
	}
}
