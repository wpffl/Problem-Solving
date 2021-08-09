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

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] map;
	static int R, C, maxSize;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		// 입력받은 지도의 정보 저장(0: 빈칸, 1: 벽, 2: 바이러스)
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		sol(0);
		bw.write(String.valueOf(maxSize));
		bw.flush();
	}

	private static void sol(int cnt) {
		if (cnt == 3) { // 벽 3개 다 세운 경우, 바이러스 퍼트리기
			bfs();
			return;
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 0) { // 빈칸일 경우, 벽 세우기
					map[i][j] = 1;
					sol(cnt + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	/* 바이러스 퍼트리는 함수 */
	private static void bfs() {
		Queue<Pos> queue = new LinkedList<>();
		int[][] copyMap = new int[R][C];
		
		// 배열 복사 및 바이러스인 경우 queue에 넣기
		for (int i = 0; i < R; i++) { 
			for (int j = 0; j < C; j++) {
				copyMap[i][j] = map[i][j];
				
				if(copyMap[i][j] == 2)
					queue.add(new Pos(i,j));
			}
		}

		while (!queue.isEmpty()) {
			Pos p = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];

				if (isValid(nx, ny)) { // 범위 체크
					if (copyMap[nx][ny] == 0) { // 바이러스가 퍼질 수 있는 공간인지 확인
						// 바이러스 퍼트리기
						copyMap[nx][ny] = 2;
						queue.add(new Pos(nx, ny));
					}
				}
			}
		}

		countSafe(copyMap);
	}

	/* 안전구역 크기 세는 함수 */
	private static void countSafe(int[][] arr) {
		int cnt = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] == 0)
					++cnt;
			}
		}

		maxSize = Math.max(maxSize, cnt);
	}

	/* 배열 범위 체크해주는 함수 */
	private static boolean isValid(int nx, int ny) {
		if (nx >= 0 && nx < R && ny >= 0 && ny < C)
			return true;
		else
			return false;
	}

}
