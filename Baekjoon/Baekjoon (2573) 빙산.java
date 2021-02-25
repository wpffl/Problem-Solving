import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int map[][];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		sol();
	}

	private static void sol() {
		int year = 0; // 결과 변수(빙산이 분리되는 최초의 시간)

		while (true) {
			/* 빙산이 몇덩어리인지 체크 */
			int cnt = countIce();

			if (cnt == 0) { // 빙산이 다 녹은 경우
				System.out.println(0);
				break;
			} else if (cnt >= 2) { // 빙산이 분리 된 경우
				System.out.println(year);
				break;
			} else { // 빙산이 아직 한 덩어리인 경우
				melt(); // 빙산 녹이기
				year++;
			}
		}
	}

	private static int countIce() {
		boolean[][] visited = new boolean[N][M];
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] != 0) {
					dfs(i, j, visited);
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void dfs(int i, int j, boolean[][] visited) {
		visited[i][j] = true;

		int nx, ny;

		for (int d = 0; d < 4; d++) {
			nx = i + dx[d];
			ny = j + dy[d];

		    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (!visited[nx][ny] && map[nx][ny] != 0) {
					dfs(nx, ny, visited);
				
			}
		}
	}

	private static void melt() {
		boolean[][] checked = new boolean[N][M]; // 원래 빙산이 있던 자리 체크

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					checked[i][j] = true; // 원래 빙산이 있던 자리
					
					/* 사방에 바다 개수 탐색 */
					int nx, ny;
					int seaCnt = 0;
					for (int k = 0; k < 4; k++) {
						nx = i + dx[k];
						ny = j + dy[k];

						if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
							if (!checked[nx][ny] && map[nx][ny] == 0)
								seaCnt++;
						
					}

					// 빙산 녹이기
					map[i][j] -= seaCnt;
					if (map[i][j] < 0)
						map[i][j] = 0;
				}
			}
		}
	}
}
