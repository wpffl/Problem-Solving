import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, R, C, L;
	static int[][] map;
	static int[][] visited;
	static int[] dr = { -1, 0, 0, 1 }; // 상좌우하
	static int[] dc = { 0, -1, 1, 0 };
	static String[] type = { null, "0312", // 1번 구조물: 상하좌우
			"03", // 2번 구조물: 상하
			"12", // 3번 구조물: 좌우
			"02", // 4번 구조물: 상우
			"32", // 5번 구조물: 하우
			"31", // 6번 구조물: 하좌
			"01", // 7번 구조물: 상좌
	};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 세로
			M = Integer.parseInt(st.nextToken()); // 가로
			R = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑이 있는 세로 위치
			C = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑이 있는 가로 위치
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요된 시간
			map = new int[N][M];
			visited = new int[N][M]; // 방문 시간으로 관리

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					visited[i][j] = Integer.MAX_VALUE;
				}
			}

			dfs(R, C, 1);
			bw.write("#" + t + " " + getCount());
			bw.newLine();
		}
		bw.flush();
	}

	// 방문 관리 -> 시간
	private static void dfs(int r, int c, int time) {
		visited[r][c] = time;
		
		if(time == L)
			return;
		
		String info = type[map[r][c]]; // 현 구조물의 타입으로 이동 가능한 방향의 정보

		// 현재 지하 터널 구조물의 방향에 맞춰 검사
		for (int d = 0; d < info.length(); d++) {
			int dir = info.charAt(d) - '0';
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (nr >= 0 && nr < N && nc >= 0 && nc < M) { // 범위체크
				if (map[nr][nc] > 0 && visited[nr][nc] > time // 지하 터널 있는지 체크 & 방문 체크
						&& type[map[nr][nc]].contains(Integer.toString(3 - dir))) { // 나랑 연결된 터널이 있는지 확인
					dfs(nr, nc, time + 1);
				}
			}
		}
	}
	
	private static int getCount() {
		int count = 0; // 탈주범이 위치할 수 있는 장소의 개수
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(visited[r][c] != Integer.MAX_VALUE)
					++count;
			}
		}
		return count;
	}
}
