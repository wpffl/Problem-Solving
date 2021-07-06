import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pos implements Comparable<Pos>{
		int x;
		int y;
		int distance;

		public Pos(int x, int y, int distance) {
			super();
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		@Override
		public int compareTo(Pos o) {
			return this.distance - o.distance; // 거리 기준 오름차순 정렬
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int cnt = 0;

		while (true) {
			int N = Integer.parseInt(br.readLine()); // 동굴의 크기

			if (N == 0) // 입력 종료
				break;

			int[][] map = new int[N][N];
			++cnt;

			// 동굴의 각 칸에 있는 도둑 루피 크기 저장
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bw.write("Problem " + cnt + ": " + bfs(N, map) + "\n");
		}
		bw.flush();
	}

	private static int bfs(int N, int[][] map) {
		boolean[][] visited = new boolean[N][N];
		int minDistance = Integer.MAX_VALUE; // 최소 거리
		PriorityQueue<Pos> queue = new PriorityQueue<>();
		queue.add(new Pos(0, 0, map[0][0])); // map[0][0]에서 출발

		while (!queue.isEmpty()) {
			Pos p = queue.poll();

			if (p.x == N - 1 && p.y == N - 1) { // 도착지에 도착한 경우 최소 거리 업데이트
				minDistance = Math.min(minDistance, p.distance);
				continue;
			}

			visited[p.x][p.y] = true;

			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];

				if (isValid(nx, ny, N)) { // 범위 체크
					if (!visited[nx][ny])
						queue.add(new Pos(nx, ny, p.distance + map[nx][ny]));
				}
			}
		}

		return minDistance;
	}

	/* 배열 범위 체크해주는 함수 */
	private static boolean isValid(int nx, int ny, int N) {
		if (nx >= 0 && nx < N && ny >= 0 && ny < N)
			return true;
		else
			return false;
	}

}
