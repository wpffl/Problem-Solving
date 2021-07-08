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
		int l;
		int r;
		int c;
		int time;

		public Pos(int l, int r, int c, int time) {
			super();
			this.l = l;
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

	static Pos start, end;
	static int[] dl = { 0, 0, 0, 0, -1, 1 }; // 남북동서상하
	static int[] dr = { -1, 1, 0, 0, 0, 0 };
	static int[] dc = { 0, 0, -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken()); // 층
			int R = Integer.parseInt(st.nextToken()); // 행
			int C = Integer.parseInt(st.nextToken()); // 열

			if (L == 0 && R == 0 && C == 0) // 입력이 종료된 경우
				break;

			char[][][] map = new char[L][R][C];

			// 입력받은 상범빌딩의 상태 저장
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					String s = br.readLine();
					for (int k = 0; k < C; k++) {
						map[i][j][k] = s.charAt(k);

						if (map[i][j][k] == 'S')
							start = new Pos(i, j, k, 0);
						else if (map[i][j][k] == 'E')
							end = new Pos(i, j, k, 0);
					}
				}
				br.readLine();
			}

			int time = bfs(L, R, C, map);

			// 출력
			if (time == Integer.MAX_VALUE)
				bw.write("Trapped!\n");
			else
				bw.write("Escaped in " + time + " minute(s).\n");

			bw.flush();
		}
	}

	private static int bfs(int L, int R, int C, char[][][] map) {
		int minTime = Integer.MAX_VALUE;
		boolean[][][] visited = new boolean[L][R][C];
		Queue<Pos> queue = new LinkedList<>();
		
		// 시작 표시
		queue.add(start); 
		visited[start.l][start.r][start.c]= true; 

		while (!queue.isEmpty()) {
			Pos p = queue.poll();

			if (p.l == end.l && p.r == end.r && p.c == end.c) { // 도착지에 도착한 경우
				minTime = p.time;
				break;
			}

			// 6방향 탐색(남북동서상하)
			for (int d = 0; d < 6; d++) {
				int nl = p.l + dl[d];
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];

				if (isValid(nl, nr, nc, L, R, C)) {
					if(map[nl][nr][nc] != '#' && !visited[nl][nr][nc]) {
						visited[nl][nr][nc] = true;
						queue.add(new Pos(nl, nr, nc, p.time + 1));
					}
				}
			}
		}

		return minTime;
	}

	private static boolean isValid(int l, int r, int c, int L, int R, int C) {
		if (l >= 0 && l < L && r >= 0 && r < R && c >= 0 && c < C)
			return true;
		else
			return false;
	}
}
