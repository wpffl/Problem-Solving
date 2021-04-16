import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Location {
		int x;
		int y;
		
		public Location(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int R, C;
	static int roomNum, maxArea, removeWallMaxarea;
	static boolean[][][] wall;
	static boolean[][] visited;
	static Queue<Location> queue;
	
	static int dx[] = { 0, -1, 0, 1 }; // 좌상우하
	static int dy[] = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		C = Integer.parseInt(st.nextToken()); // 열
		R = Integer.parseInt(st.nextToken()); // 행
		
		wall = new boolean[R][C][4]; // 해당 방의 좌상우하에 벽의 존재여부
		visited = new boolean[R][C]; // 방문 체크
		queue = new LinkedList<>(); // BFS 탐색을 위한 큐(다음에 갈 위치를 저장)
		
		// 입력받은 벽에 대한 정보 저장
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				int dir = Integer.parseInt(st.nextToken());
				
				// 비트 마스킹
				// 좌: 1, 상: 2, 우: 4, 하: 8
				for (int d = 3; d >= 0; d--) {
					if((dir & 1 << d) >= 1)
						wall[i][j][d] = true;
				}
			}
		}
		
		sol();
		
		// 결과 출력
		bw.write(String.valueOf(roomNum)); // 방의  개수
		bw.newLine();
		bw.write(String.valueOf(maxArea)); // 가장 넓은 방의 넓이
		bw.newLine();
		bw.write(String.valueOf(removeWallMaxarea)); // 벽을 하나 제거하고 얻을 수 있는 가장 넓은 방의 넓이
		bw.flush();
	}

	private static void sol() {
		/* 초기 방의 개수와 가장 넓은 방 찾기 */
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(!visited[i][j]) { // 방문하지 않은 방이면
					visited[i][j] = true;
					++roomNum; // 방의 개수 증가
					
					queue.offer(new Location(i, j)); // 큐에 넣고 bfs() 출발
					int area = bfs();
					maxArea = Math.max(maxArea, area); // 더 넓은 방의 넓이로 업데이트
				}
			}
		}
		
		/* 모든 벽을 하나씩 없애면서 넓이 체크 */
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// visited 배열 초기화
				clear();
				
				for (int d = 0; d < 4; d++) {
					// 해당 방향에 벽이 있으면
					if(wall[i][j][d]) { 
						visited[i][j] = true;
						
						// 벽 없애고 넓이 탐색
						wall[i][j][d] = false;
						queue.offer(new Location(i, j));
						int area = bfs();
						removeWallMaxarea = Math.max(removeWallMaxarea, area);
						
						// 벽 다시 만들기
						wall[i][j][d] = true;
					}
				}
			}
		}
	}

	/* 방의 넓이 리턴 */
	private static int bfs() {
		int area = 1; // 방의 넓이
		
		while(!queue.isEmpty()) {
			Location loc = queue.poll();
			
			// 좌상우하 방향으로 탐색
			for (int d = 0; d < 4; d++) {
				if(!wall[loc.x][loc.y][d]) { // 해당 방향에 벽이 없는 경우
					// 해당 방향으로 다음 좌표
					int nx = loc.x + dx[d];
					int ny = loc.y + dy[d];
					
					if(nx >= 0 & nx < R && ny >= 0 && ny < C) { // 범위 체크
						if(!visited[nx][ny]) { // 방문 체크
							++area; // 방의 넓이 1 증가
							visited[nx][ny] = true;
							queue.offer(new Location(nx, ny));
						}
					}
				}
			}
		}
		return area;
	}
	
	private static void clear() {
		for (int i = 0; i < R; i++) {
			Arrays.fill(visited[i], false);
		}
	}
}
