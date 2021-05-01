import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int res; // 결과 변수(구역의 수)
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine()); // map의 가로, 세로 길이
		map = new char[N][N];
		visited = new boolean[N][N];

		// 입력받은 구역의 정보 저장
		// R(빨강), G(초록), B(파랑)
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		// 적록색약이 아닌 사람이 보는 구역의 수 카운트
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					++res;
					dfs(i, j);
				}
			}
		}
		bw.write(res + " ");

		/* 다음 탐색을 위한 준비 */
		for (int i = 0; i < N; i++) { // 1. visited 배열 초기화
			Arrays.fill(visited[i], false);
		}
		check(); // 2. 빨강과 초록을 같은 구역으로 표시
		res = 0; // 3. 결과 변수 초기화

		// 적록색약이 아닌 사람이 보는 구역의 수 카운트
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					++res;
					dfs(i, j);
				}
			}
		}
		bw.write(String.valueOf(res));
		bw.flush();
	}

	private static void dfs(int x, int y) {
		char color = map[x][y];

		if (visited[x][y])
			return;

		visited[x][y] = true;

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (isValid(nx, ny) && map[nx][ny] == color) { // 범위 체크 & 같은 색인지 체크
				dfs(nx, ny);
			}
		}
	}

	private static boolean isValid(int nx, int ny) {
		if (nx >= 0 && nx < N && ny >= 0 && ny < N)
			return true;
		else
			return false;
	}
	
	private static void check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 'G') // 초록색이면 빨간색으로 표시
					map[i][j] = 'R';
			}
		}
	}
}
