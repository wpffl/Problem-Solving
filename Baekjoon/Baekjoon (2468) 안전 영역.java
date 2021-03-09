import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, res;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int res = 1; // 결과 변수(하나도 침수되지 않은 경우 안전영역: 1)

		// 입력 값 처리
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				// 최소 & 최대 값 찾기
				if (map[i][j] < min)
					min = map[i][j];
				if (map[i][j] > max)
					max = map[i][j];
			}
		}	
		
		// 강수량을 늘려가면서 안전영역 탐색 및 최대값 갱신
		for (int rain = min; rain < max; rain++) {
			int cnt = 0;
			
			/* visited 배열 초기화 */
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited[i][j] = false;
				}
			}
			
			/* 탐색 */
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] > rain && !visited[i][j]) {
						visited[i][j] = true;
						dfs(i, j, rain);
						cnt++;
					}
				}
			}
			
			res = Math.max(res, cnt);
		}

		System.out.println(res);
	}

	private static void dfs(int x, int y, int rain) {
		
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
      // 범위 체크
			if(nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;
			
      // 방문체크 & 침수 여부 체크
			if(visited[nx][ny] || map[nx][ny] <= rain)
				continue;
			
			visited[nx][ny] = true;
			dfs(nx, ny, rain);
		}
	}	
}
