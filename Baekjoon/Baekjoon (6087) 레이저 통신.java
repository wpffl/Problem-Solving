import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pos {
		int x;
		int y;
		int dir; // 방향
		int mirror; // 거울 몇개 사용했는지

		public Pos(int x, int y, int dir, int mirror) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.mirror = mirror;
		}
	}

	static int R, C, res = Integer.MAX_VALUE;
	static char[][] map;
	static int[][] visited;
	static Pos start, end;
	static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		C = Integer.parseInt(st.nextToken()); // 열
		R = Integer.parseInt(st.nextToken()); // 행

		map = new char[R][C];
		visited = new int[R][C];

		// 입력 받은 map 상태 저장
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				visited[i][j] = Integer.MAX_VALUE; // 최대값으로 초기화
				if (map[i][j] == 'C') { // 거울 2개 위치 저장
					if (start == null)
						start = new Pos(i, j, -1, 0);
					else
						end = new Pos(i, j, -1, 0);
				}
			}
		}

		bfs();
		bw.write(String.valueOf(res));
		bw.flush();
	}

	private static void bfs() {
		Queue<Pos> queue = new LinkedList<>();

		// 출발지 표시
		queue.add(start);
		visited[start.x][start.y] = 1;

		while (!queue.isEmpty()) {
			Pos p = queue.poll();

			if (p.x == end.x && p.y == end.y) { // 도착지에 도착한 경우
				res = Math.min(res, p.mirror); // 도착한 현재 상황이 무조건 최소라는 보장 없으므로 계속 탐색
				continue;
			}

			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				int nd = d;

				if (isValid(nx, ny)) { // 배열 범위 체크
					if (map[nx][ny] != '*') { // 벽이 아닌지 체크
						int mirror = p.mirror;
						if (p.dir != nd && p.dir != -1) { // 처음 시작이 아니고, 뱡향이 다른 경우 -> 거울 설치
							mirror++;
						}

						if (mirror <= visited[nx][ny]) { // 이미 방문했지만 거울의 개수가 더 적은 경우
							visited[nx][ny] = mirror; // 현재 거울 개수로 업데이트
							queue.add(new Pos(nx, ny, nd, mirror));
						}
					}
				}
			}
		}
		return;
	}

	/* 배열 범위 체크해주는 함수 */
	private static boolean isValid(int nx, int ny) {
		if (nx >= 0 && nx < R && ny >= 0 && ny < C)
			return true;
		else
			return false;
	}
}
