import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static class Point {
		int r; // 행
		int c; // 열
		int cnt; // 벽돌의 파급효과

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	private static int N, R, C, min;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 구슬을 쏠 수 있는 횟수
			C = Integer.parseInt(st.nextToken()); // 열
			R = Integer.parseInt(st.nextToken()); // 행

			int[][] map = new int[R][C];

			// 입력받은 벽돌의 정보 저장
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			min = Integer.MAX_VALUE;
			go(0, map);
			bw.write("#" + t + " " + min);
			bw.newLine();
		}
		bw.flush();
	}

	/* 중복 순열로 구슬 떨어뜨리는 함수 */
	// cnt: 구슬을 떨어뜨린 횟수
	// map: cnt-1 구슬까지의 상태맵
	// boolean: true - 모두 깨뜨린 상황
	private static boolean go(int cnt, int[][] map) {
		int result = getRemain(map); // 남아있는 벽돌 개수 세기
		if(result == 0) { // 모두 빈칸(깨뜨릴 벽돌이 없다)
			min = 0;
			return true;
		}
		
		// 구슬 다 던진 경우
		if(cnt == N) {
			min = Math.min(min, result);
			return false;
		}
		
		int[][] newMap = new int[R][C];

		// 매열마다 구슬 떨어뜨리는 시도
		for (int c = 0; c < C; c++) {
			// 해당 열에 구슬을 떨어뜨려 맞는 벽돌 찾기
			int r = 0;
			while (r < R && map[r][c] == 0) {
				++r;
			}

			if (r == R) { // 맞는 벽돌 없음(모두 빈칸)
				continue; // 다음 열로 구슬 떨어뜨리기
			} else {
				// 기존 cnt-1 구슬까지의 상태로 초기화
				copy(map, newMap); // 배열 복사(배열의 레퍼런스를 파라미터로 줬기때문에 따로 리턴 없이 구현 가능)

				// 벽돌 깨뜨리기
				boom(newMap, r, c);

				// 벽돌 내리기(깨지고 난 빈 공간 처리)
				down(newMap);

				// 다음 구슬 던지기
				if(go(cnt + 1, newMap))
					return true;
			}
		}
		return false;
	}

	/* 배열 복사하는 함수 */
	private static void copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}

	/* 벽돌 깨뜨리는 함수 */
	private static void boom(int[][] map, int r, int c) {
		Queue<Point> queue = new LinkedList<>();

		if (map[r][c] > 1) {
			queue.offer(new Point(r, c, map[r][c]));
		}

		map[r][c] = 0; // 벽돌 제거 처리(방문처리 효과) -> 큐에 담아놨기 때문에 제거처리해도 상관없음

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			// 4방으로 파급효과 적용
			for (int d = 0; d < 4; d++) {
				int nr = p.r;
				int nc = p.c;

				for (int k = 0; k < p.cnt - 1; k++) {
					nr += dr[d];
					nc += dc[d];

					// 경계 체크 && 벽돌이 있는지 체크
					if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != 0) {
						if (map[nr][nc] > 1) {
							queue.offer(new Point(nr, nc, map[nr][nc]));
						}
						map[nr][nc] = 0;
					}
				}
			}
		}
	}
	
	/* 빈칸이 없도록 벽돌 내리는 함수 */
	private static void down(int[][] map) {
		for (int c = 0; c < C; c++) {
			int r = R - 1;
			
			// 현재 행이 빈칸이면 위로 간다
			while(r >= 0 && map[r][c] == 0)
				--r;
			
			if(r < 0)  // 모두 빈칸인 경우 내릴 벽돌이 없으므로 옆에 열 내리기
				continue;
			
			// 밑으로 내릴 벽돌이 있는 경우
			r = R - 1;
			while(r > 0) { // 자신의 위치에 내리기
				if(map[r][c] == 0) { // 현재 자리가 빈칸이면 
					// 직전부터 위쪽 탐색하며 가장 가까운 벽돌 찾기
					int nr = r - 1;
					
					while(nr > 0 && map[nr][c] == 0) // map[0][c]는 벽돌이든 빈칸이든 무조건 swap
						--nr;
					
					// 두 개 swap
					map[r][c] = map[nr][c];
					map[nr][c] = 0;
				}
				--r;
			}
		}
	}
	

	/* 남아있는 벽돌 세는 함수 */
	private static int getRemain(int[][] map) {
		int count = 0;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] > 0)
					++count;
			}
		}
		
		return count;
	}
}
