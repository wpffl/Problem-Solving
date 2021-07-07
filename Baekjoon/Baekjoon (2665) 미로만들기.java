import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
	static class Pos implements Comparable<Pos> {
		int x;
		int y;
		int changeCnt;

		public Pos(int x, int y, int changeCnt) {
			super();
			this.x = x;
			this.y = y;
			this.changeCnt = changeCnt;
		}

		@Override
		public int compareTo(Pos o) {
			return this.changeCnt - o.changeCnt; // 거리 기준 오름차순 정렬
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine()); // 방의 수
		int[][] map = new int[n][n];

		// 입력받은 방의 색 저장(0: 검은방, 1: 흰방)
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		bw.write(String.valueOf(bfs(n, map)));
		bw.flush();
	}

	private static int bfs(int n, int[][] map) {
		int minChange = Integer.MAX_VALUE;
		PriorityQueue<Pos> pqueue = new PriorityQueue<>();
		pqueue.add(new Pos(0, 0, 0)); // 시작점 표시

		while (!pqueue.isEmpty()) {
			Pos p = pqueue.poll();

			// 도착점에 도착한 경우
			if (p.x == n - 1 && p.y == n - 1) {
				minChange = Math.min(minChange, p.changeCnt);
				continue;
			}
			
			map[p.x][p.y] = 2; // 방문 체크 

			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];

				if (isValid(nx, ny, n)) {
					if(map[nx][ny] == 1) { // 흰 방인 경우, 방 변화 없이 이동
						pqueue.add(new Pos(nx, ny, p.changeCnt));
					} else if(map[nx][ny] == 0) { // 검은 방인 경우, 검은 방을 흰방으로 바꾸고 이동
						pqueue.add(new Pos(nx, ny, p.changeCnt + 1));
					}
				}
			}
		}

		return minChange;
	}

	private static boolean isValid(int nx, int ny, int n) {
		if (nx >= 0 && nx < n && ny >= 0 && ny < n)
			return true;
		else
			return false;
	}

}
