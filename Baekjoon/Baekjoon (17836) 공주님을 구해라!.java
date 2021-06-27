import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pos {
		int x;
		int y;
		int time;
		int sword;

		public Pos(int x, int y, int time, int sword) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
			this.sword = sword;
		}
	}

	static int N, M, T, res = Integer.MAX_VALUE;
	static int[][] map;
	static int dx[] = { -1, 1, 0, 0 }; // 상하좌우
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		T = Integer.parseInt(st.nextToken()); // 도착 max 시간

		map = new int[N][M];

		// 입력받은 map 정보 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();
		System.out.println(res != Integer.MAX_VALUE ? res : "Fail");
	}

	private static void bfs() {
		boolean[][][] visited = new boolean[N][M][2]; // visited[x][y][0]:해당 위치에서 검 가지고 있지 않음, visited[x][y][1]: 해당 위치에서 검 가지고 있음
		Queue<Pos> queue = new LinkedList<>();

		// 시작점 표시
		queue.add(new Pos(0, 0, 0, 0));

		while (!queue.isEmpty()) {
			Pos p = queue.poll();

			if (p.time > T) // 이미 T 시간을 초과한 경우 다음으로 pass
				continue;

			if (p.x == N - 1 && p.y == M - 1)  // 도착지에 도착한 경우, 최소값 저장
				res = Math.min(res, p.time);
	
			for (int d = 0; d < 4; d++) { // 사방 탐색
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];

				if (isValid(nx, ny)) { // 범위 체크
					if (!visited[nx][ny][p.sword]) { // 방문 체크
						if (p.sword == 0) { // 검이 없는 경우
							if (map[nx][ny] == 1) // 벽이면 pass
								continue;
							
							visited[nx][ny][0] = true;

							if (map[nx][ny] == 2) // 해당 지역에 검이 있는 경우
								queue.add(new Pos(nx, ny, p.time + 1, 1));
							else // 그 외의 경우
								queue.add(new Pos(nx, ny, p.time + 1, 0));
						} else { // 검이 있는 경우
							visited[nx][ny][1] = true;
							queue.add(new Pos(nx, ny, p.time + 1, 1));
						}
					}
				}
			}
		}
	}

    /* 배열 범위 체크해주는 함수 */
	private static boolean isValid(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < M)
			return true;
		else
			return false;
	}
}
