import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/* 다익스트라 */
public class  {
	static int N, INF = Integer.MAX_VALUE;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // 지도의 크기
			map = new int[N][N];

			// 입력 받은 지도 정보 저장
			for (int i = 0; i < N; i++) {
				char[] arr = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = arr[j] - '0';
				}
			}
			
			// 출력
			bw.write("#" + t + " " + dijkstra(0, 0));
			bw.newLine();
		}
		bw.flush();
	}

	private static int dijkstra(int startX, int startY) {
		boolean[][] visited = new boolean[N][N];
		int[][] minTime = new int[N][N];

		// minTime 배열 다 max로 채우기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minTime[i][j] = INF;
			}
		}

		minTime[startX][startY] = 0; // 출발지를 0으로 둠

		int x = 0, y = 0, cost = 0, nx, ny;

		while (true) {
			cost = INF;

			// 방문하지 않은 정점 중 출발지에서 자신으로 오는 비용이 최소인 정점 선택 (시간복잡도: 정점수^2)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && minTime[i][j] < cost) {
						cost = minTime[i][j];
						x = i;
						y = j;
					}
				}
			}

			visited[x][y] = true;

			// 도착점에 도착한 경우
			if (x == N - 1 && y == N - 1)
				return cost;

			// 현재 정점 위치 기준으로 4방의 인접 정점을 처리
			for (int d = 0; d < 4; d++) {
				nx = x + dx[d];
				ny = y + dy[d];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) { // 범위 & 방문 체크
					// 선택된 정점 기준으로 방문하지 않은 나머지 정점들 자신과의 경유시의 비용과 기존 최소 비용 비교하여 최소값 업데이트
					if (cost + map[nx][ny] < minTime[nx][ny]) {
						minTime[nx][ny] = cost + map[nx][ny];
					}
				}
			}
		}
	}
}
