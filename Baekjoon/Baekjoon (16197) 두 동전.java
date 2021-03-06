import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static int N, M, cnt;
	static int x1, y1, x2, y2;
	static int[] dx = { -1, 1, 0, 0, };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		/* map setting & 동전 위치 저장 */
		map = new char[N][M];
		boolean checked = false;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);

				if (map[i][j] == 'o') {
					if (!checked) {
						checked = true;
						x1 = i;
						y1 = j;
					} else {
						x2 = i;
						y2 = j;
					}
				}
			}
		}

		cnt = Integer.MAX_VALUE;
		bt(x1, y1, x2, y2, 0);

		if (cnt > 10) {
			System.out.println(-1);
		} else {
			System.out.println(cnt);
		}
	}

	private static void bt(int x1, int y1, int x2, int y2, int btCnt) {
		/* 버튼을 누른 횟수가 최소 회수보다 같거나 클 경우 pass, 버튼을 10번 누른 경우 pass */
		if (btCnt >= cnt || btCnt > 10) {
			return;
		}

		for (int i = 0; i < 4; i++) {
			int outCnt = 0;
			int nx1 = x1 + dx[i];
			int ny1 = y1 + dy[i];
			int nx2 = x2 + dx[i];
			int ny2 = y2 + dy[i];

			// 동전 1이 보드에서 떨어졌는지 체크
			if (nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= M)
				outCnt++;

			// 동전 2가 보드에서 떨어졌는지 체크
			if (nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= M)
				outCnt++;

			// 동전이 두 개 다 떨어지면 pass
			if (outCnt == 2)
				continue;

			// 동전이 하나만 떨어진 경우
			if (outCnt == 1) {
				// 버튼을 누른 최소 횟수 갱신
                cnt = Math.min(cnt, btCnt + 1);
				return;
			}

			/* 벽이면 다시 되돌아가기 */
			if (map[nx1][ny1] == '#') {
				nx1 = x1;
				ny1 = y1;
			}

			if (map[nx2][ny2] == '#') {
				nx2 = x2;
				ny2 = y2;
			}

			bt(nx1, ny1, nx2, ny2, btCnt + 1);
		}

	}
}
