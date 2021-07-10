import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Pos implements Comparable<Pos> {
		int r;
		int c;
		int bomb;

		public Pos(int r, int c, int bomb) {
			super();
			this.r = r;
			this.c = c;
			this.bomb = bomb;
		}

		@Override
		public int compareTo(Pos o) {
			return this.bomb - o.bomb; 
		}
	}
	
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		
		// 입력받은 미로의 정보 저장(0:빈방, 1:벽)
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		bw.write(String.valueOf(bfs(R, C, map)));
		bw.flush();
	}

	private static int bfs(int R, int C, int[][] map) {
		int minBomb = 0;
		boolean[][] visited = new boolean[R][C];
		PriorityQueue<Pos> pqueue = new PriorityQueue<>();
		pqueue.add(new Pos(0, 0, 0)); // 출발지 표시
		
		while(!pqueue.isEmpty()) {
			Pos p = pqueue.poll();
			
			if(p.r == R - 1 && p.c == C - 1) { // 도착지에 도착한 경우
				minBomb = p.bomb;
				break;
			}
			
			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if(isValid(nr, nc, R, C)) { // 배열 범위 체크
					if(!visited[nr][nc]) { // 방문 체크
						visited[nr][nc] = true;
						
						if(map[nr][nc] == 0) // 빈 방이면 그냥 Go
							pqueue.add(new Pos(nr, nc, p.bomb));
						else // 벽이면 부수고 이동
							pqueue.add(new Pos(nr, nc, p.bomb + 1));
					}
				}
			}
		}
		return minBomb;
	}
	
	private static boolean isValid(int nr, int nc, int R, int C) {
		if (nr >= 0 && nr < R && nc >= 0 && nc < C)
			return true;
		else
			return false;
	}
}
