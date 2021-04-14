import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 고슴도치와 물의 좌표를 저장하기 위한 클래스
	static class Loc { 
		int x; 
		int y; 

		public Loc(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int R, C, startX, startY, endX, endY;
	static int res;
	static char[][] map;
	static boolean[][] visited;
	static Queue<Loc> go, water;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열

		map = new char[R][C]; 
		go = new LinkedList<>(); // 고슴도치의 좌표를 저장하는 큐
		water = new LinkedList<>(); // 물의 좌표를 저장하는 큐
		visited = new boolean[R][C]; // 고슴도치가 방문한 땅인지 체크하는 배열

		// 입력 받은 map 정보 저장
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);

				if (map[i][j] == 'S') { // 고슴도치 굴
					startX = i;
					startY = j;
					go.add(new Loc(i, j));
					visited[i][j] = true;
				} else if (map[i][j] == 'D') { // 비버 굴
					endX = i;
					endY = j;
				} else if (map[i][j] == '*') { // 물인 곳
					water.offer(new Loc(i, j));
				}
			}
		}

		bfs();

		if (res == 0) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(res);
		}
	}

	/* 고슴도치 이동하는 함수 */
	private static void bfs() {
		while (!go.isEmpty()) {
			waterMove();
			res++; // 시간 카운트

			int cnt = go.size();
			for (int i = 0; i < cnt; i++) {
				Loc goseum = go.poll();

				for (int d = 0; d < 4; d++) {
					int nx = goseum.x + dx[d];
					int ny = goseum.y + dy[d];

					if (nx == endX && ny == endY) { // 비버굴(도착지)에 도착한 경우
						return;
					}

					if (nx >= 0 && nx < R && ny >= 0 && ny < C) { // 범위 체크
						if (!visited[nx][ny] && map[nx][ny] == '.') { // 방문하지 않았고, 갈 수 있는 땅인 경우
							visited[nx][ny] = true;
							go.add(new Loc(nx, ny));
						}
					}
				}
			}
		}
		res = 0; // 고슴도치가 비버집으로 못간 경우
	}

	/* 물인 곳 확장하는 함수 */
	private static void waterMove() {
		// 4방으로 물인 곳 확장
		int cnt = water.size();
		for (int i = 0; i < cnt; i++) {
			Loc Water = water.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nx = Water.x + dx[dir];
				int ny = Water.y + dy[dir];

				if (nx >= 0 && nx < R && ny >= 0 && ny < C) { // 범위 체크
					if (map[nx][ny] == '.') { // 물로 확장할 수 있는 땅이면
						map[nx][ny] = '*'; // 물로 확장
						water.offer(new Loc(nx, ny));
					}
				}
			}
		}
	}
}
