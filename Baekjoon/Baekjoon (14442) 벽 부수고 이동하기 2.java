import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, res = -1;
	static boolean[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Wall {
		int x;
		int y;
		int cnt; // 지나간 칸 수
		int k; // 벽을 부순 횟수

		public Wall(int x, int y, int cnt, int k) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.k = k;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		K = Integer.parseInt(st.nextToken()); // 부술 수 있는 벽의 개수
		map = new boolean[N][M];

		// 입력받은 map의 정보 저장
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				if (s.charAt(j) - '0' == 0)
					map[i][j] = true; // 이동할 수 있는 곳
				else
					map[i][j] = false; // 이동할 수 없는 곳
			}
		}

		bfs();

		// 출력
		bw.write(String.valueOf(res));
		bw.flush();
	}

	private static void bfs() {
		boolean[][][] visited = new boolean[N][M][K + 1];
		Queue<Wall> queue = new LinkedList<>();

		queue.add(new Wall(0, 0, 1, 0)); // 출발지
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {
			Wall current = queue.poll();

			if (current.x == N - 1 && current.y == M - 1) { // 도착지에 도착한 경우
				res = current.cnt;
				return;
			}

			for (int d = 0; d < 4; d++) { // 4방 탐색
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];

				if (isValid(nx, ny)) { // 범위 체크
					if(map[nx][ny]) { // 이동할 수 있는 경우
						if(!visited[nx][ny][current.k]) { // 방문 체크
							visited[nx][ny][current.k] = true;
							queue.add(new Wall(nx, ny, current.cnt + 1, current.k));
						}
					} else { // 이동할 수 없는 경우
						if(current.k >= K) // 벽을 더이상 부술 수 없는 경우 pass
							continue;
						
						if(!visited[nx][ny][current.k + 1]) { // 벽을 부술 수 있다면 부수고 이동
							visited[nx][ny][current.k + 1] = true;
							queue.add(new Wall(nx, ny, current.cnt + 1, current.k + 1));
						}
					}
				}
			}
		}
	}

	/* 범위 체크 함수 */
	private static boolean isValid(int nx, int ny) {
		if (nx >= 0 && nx < N && ny >= 0 && ny < M)
			return true;
		else
			return false;
	}
}
