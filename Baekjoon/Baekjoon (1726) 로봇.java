import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C, startR, startC, startD, endR, endC, endD;
	static boolean[][] map;
	static boolean[][][] visited;
	static int[] dr = { 0, 0, 1, -1 }; // 동 서 남 북
	static int[] dc = { 1, -1, 0, 0 };
	
	static class Robot {
		int r;
		int c;
		int dir;
		int cnt;
		
		public Robot(int r, int c, int dir, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R][C]; // 공장 내 궤도 정보가 저장된 배열
		visited = new boolean[R][C][4]; // 방문체크 배열(r,c,방향)

		// 입력 받은 공장 내 궤도 정보 저장
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				if (Integer.parseInt(st.nextToken()) == 0)
					map[i][j] = true; // 로봇이 갈 수 있는 지점
				else
					map[i][j] = false; // 로봇이 갈 수 없는 지점
			}
		}

		// 입력받은 출발 지점의 위치, 방향 저장
		// 방향(1:동, 2:서, 3:남, 4:북)
		st = new StringTokenizer(br.readLine(), " ");
		startR = Integer.parseInt(st.nextToken()) - 1; 
		startC = Integer.parseInt(st.nextToken()) - 1;
		startD = Integer.parseInt(st.nextToken()) - 1; 

		// 입력받은 도착 지점의 위치, 방향 저장
		st = new StringTokenizer(br.readLine(), " ");
		endR = Integer.parseInt(st.nextToken()) - 1; 
		endC = Integer.parseInt(st.nextToken()) - 1;
		endD = Integer.parseInt(st.nextToken()) - 1;
		
		bfs();
	}

	private static void bfs() {
		Queue<Robot> queue = new LinkedList<>();
		visited[startR][startC][startD] = true;
		queue.add(new Robot(startR, startC, startD, 0));
		
		while(!queue.isEmpty()) {
			Robot robot = queue.poll();
			
			if(robot.r == endR && robot.c == endC && robot.dir == endD) { // 도착지에 도착한 경우
				System.out.println(robot.cnt);
				return;
			}
			
			// 1. 해당 방향대로 1칸부터 3칸까지 이동 가능
			for (int i = 1; i <= 3; i++) { 
				int nr = robot.r + (dr[robot.dir] * i);
				int nc = robot.c + (dc[robot.dir] * i);
				
				if(isValid(nr, nc)) { // 범위 검사
					if(map[nr][nc]) { // 갈 수 있는 곳인지 검사
						if(!visited[nr][nc][robot.dir]) { // 방문 검사
							visited[nr][nc][robot.dir] = true;
							queue.add(new Robot(nr, nc, robot.dir, robot.cnt + 1));
						}
					} else { // 갈 수 없는 곳이면 종료
						break;
					}
				}
			}
			
			// 2. 90도 방향 전환(최대 180도까지)
			for (int d = 0; d < 4; d++) { // d -> 가고자 하는 방향(현재 방향을 제외한 3방향 탐색)
				if(robot.dir != d && !visited[robot.r][robot.c][d]) {
					visited[robot.r][robot.c][d] = true;

					// 동 -> 서, 서 -> 동, 남 -> 북, 북 -> 남은 180도 회전!(count 2번)
					if((robot.dir==0 && d==1) || (robot.dir==1 && d==0) || (robot.dir==2 && d==3) || (robot.dir==3 && d==2)) {
						queue.add(new Robot(robot.r, robot.c, d, robot.cnt + 2));
                    } else { // 그 외의 경우 90도 회전!(count 1번)
                    	queue.add(new Robot(robot.r, robot.c, d, robot.cnt + 1));
                    }
				}
			}
		}
	}

	/* 범위 체크 함수 */
	private static boolean isValid(int nr, int nc) {
		if(nr >= 0 && nr < R && nc >= 0 && nc < C)
			return true;
		else
			return false;
	}
}
