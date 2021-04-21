import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static class Pos {
		int r; // 행
		int c; // 열

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static class CCTV {
		String dir; // CCTV의 방향
		int r; // 행
		int c; // 열

		public CCTV(String dir, int r, int c) {
			super();
			this.dir = dir;
			this.r = r;
			this.c = c;
		}
	}

	static int R, C, cctvNum, res = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] copymap;
	static LinkedList<Pos> cctvList;
	static LinkedList<CCTV> dirList;
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		map = new int[R][C]; // 사무실
		copymap = new int[R][C]; // 사무실
		cctvList = new LinkedList<>(); // CCTV의 좌표를 저장할 리스트
		dirList = new LinkedList<>(); // CCTV별 방향을 저장할 리스트

		// 입력 받은 사무실의 정보 저장
		// 0: 빈칸, 1~5: CCTV 번호, 6: 벽
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = copymap[i][j] = Integer.parseInt(st.nextToken());

				if (0 < map[i][j] && map[i][j] < 6) { // CCTV면 리스트에 넣기
					cctvList.add(new Pos(i, j));
					++cctvNum; // CCTV 개수 카운트
				}
			}
		}

		dfs(0);
		System.out.println(res);
	}

	private static void dfs(int depth) {
		if (depth == cctvNum) { // CCTV 개수만큼 다 본 경우
			for (int i = 0; i < cctvNum; i++) {
				CCTV cctv = dirList.get(i);
				// CCTV의 방향별로 map Setting
				for (int j = 0; j < cctv.dir.length(); j++) {
					print(cctv.dir.charAt(j) - '0', cctv.r, cctv.c);
				}
			}

			int count = count(); // CCTV 사각지대 개수 카운트
			res = Math.min(res, count); // 최소값 업데이트
			resetCopymap(); // 배열 초기화

			return;
		}

		Pos pos = cctvList.get(depth); // 현재 CCTV 위치
		int type = map[pos.r][pos.c];

		switch (type) {
		case 1:
			// 한 방향만 감시 가능
			String[] dir1 = { "0", "1", "2", "3" };

			// 해당 방향으로 CCTV가 감시할 영역 표시
			for (int i = 0; i < dir1.length; i++) {
				dirList.add(new CCTV(dir1[i], pos.r, pos.c)); // 현재 방향 넣기
				dfs(depth + 1);
				dirList.removeLast(); // 현재 방향 지우기(다음 방향 탐색을 위해서
			}

			break;

		case 2:
			// 두 방향만 감시 가능(방향이 서로 반대여야 함)
			String[] dir2 = { "01", "23" };

			// 해당 방향으로 CCTV가 감시할 영역 표시
			for (int i = 0; i < dir2.length; i++) {
				dirList.add(new CCTV(dir2[i], pos.r, pos.c)); // 현재 방향 넣기
				dfs(depth + 1);
				dirList.remove(depth); // 현재 방향 지우기(다음 방향 탐색을 위해서)
			}

			break;
		case 3:
			// 두 방향만 감시 가능(방향이 직각이어야 함)
			String[] dir3 = { "02", "03", "12", "13" };

			// 해당 방향으로 CCTV가 감시할 영역 표시
			for (int i = 0; i < dir3.length; i++) {
				dirList.add(new CCTV(dir3[i], pos.r, pos.c)); // 현재 방향 넣기
				dfs(depth + 1);
				dirList.remove(depth); // 현재 방향 지우기(다음 방향 탐색을 위해서)
			}

			break;
		case 4:
			// 세 방향만 감시 가능
			String[] dir4 = { "123", "023", "013", "012" };

			// 해당 방향으로 CCTV가 감시할 영역 표시
			for (int i = 0; i < dir4.length; i++) {
				dirList.add(new CCTV(dir4[i], pos.r, pos.c)); // 현재 방향 넣기
				dfs(depth + 1);
				dirList.remove(depth); // 현재 방향 지우기(다음 방향 탐색을 위해서)
			}

			break;
		case 5:
			// 사방으로 CCTV가 감시 가능
			String[] dir5 = { "0123" };
			
			// 해당 방향으로 CCTV가 감시할 영역 표시
			dirList.add(new CCTV(dir5[0], pos.r, pos.c));
			dfs(depth + 1);
			dirList.remove(depth); // 현재 방향 지우기(다음 방향 탐색을 위해서)

			break;
		}
	}

	/* 방향대로 CCTV가 감시하는 영역 표시해주는 함수 */
	private static void print(int dir, int posR, int posC) {
		int r = posR;
		int c = posC;
		while (true) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (!isValid(nr, nc)) // 범위 벗어나면 while문 종료
				break;

			if (map[nr][nc] == 6) // 벽이면 while문 종료
				break;

			if (!isCCTV(nr, nc)) // CCTV가 있는지 체크, CCTV가 아니면
				map[nr][nc] = 7; // CCTV가 감시할 수 있는 영역인거 표시

			r = nr;
			c = nc;
		}
	}

	/* CCTV 사각지대 개수 카운트 해주는 함수 */
	private static int count() {
		int cnt = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 0)
					++cnt;
			}
		}

		return cnt;
	}

	/* map을 초기 map으로 초기화하는 함수 */
	private static void resetCopymap() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = copymap[i][j];
			}
		}
	}

	/* 현재 위치에 CCTV가 있는지 알려주는 함수 */
	private static boolean isCCTV(int nr, int nc) {
		if (0 < map[nr][nc] && map[nr][nc] < 6)
			return true;
		else
			return false;
	}

	/* 범위 체크 함수 */
	private static boolean isValid(int nr, int nc) {
		if (nr >= 0 && nr < R && nc >= 0 && nc < C)
			return true;
		else
			return false;
	}
}
