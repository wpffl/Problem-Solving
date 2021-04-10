import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Order {
		int robotIdx; // 명령을 받을 로봇 번호
		char order; // 명령어
		int repeat; // 명령 반복 횟수

		public Order(int robotIdx, char order, int repeat) {
			super();
			this.robotIdx = robotIdx;
			this.order = order;
			this.repeat = repeat;
		}
	}

	static class Robot {
		int x;
		int y;

		public Robot(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int Y = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		// 0: 아무것도 없음, 1: 로봇이 오른쪽 방향으로 있음, 2: 위, 3: 왼쪽, 4: 아래
		int[][] map = new int[X][Y];

		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 로봇의 개수
		int M = Integer.parseInt(st.nextToken()); // 명령어의 개수
		Robot[] robot = new Robot[N]; // 1번째 행: 1번 로봇의 x, y 좌표

		// 입력받은 로봇의 위치와 방향 맵에 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			char dir = st.nextToken().charAt(0);

			// 로봇 위치 저장
			robot[i] = new Robot(x, y);

			// map에 방향 저장(상하 반전)
			if (dir == 'E')
				map[x][y] = 1;
			else if (dir == 'S')
				map[x][y] = 2;
			else if (dir == 'W')
				map[x][y] = 3;
			else if (dir == 'N')
				map[x][y] = 4;
		}

		// 입력받은 명령어 저장
		Queue<Order> queue = new LinkedList<>(); // 명령어를 저장할 큐
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int robotIdx = Integer.parseInt(st.nextToken()) - 1;
			char order = st.nextToken().charAt(0);
			int repeat = Integer.parseInt(st.nextToken());

			queue.offer(new Order(robotIdx, order, repeat));
		}

		sol(map, robot, queue, X, Y);
		bw.flush();
	}

	private static void sol(int[][] map, Robot[] robot, Queue<Order> queue, int X, int Y) throws IOException {

		/* 방향 세팅 */
		int[] dx = { 0, -1, 0, 1 }; // 우상좌하
		int[] dy = { 1, 0, -1, 0 };

		/* 명령 수행 */
		while (!queue.isEmpty()) {
			Order o = queue.poll();

			int x = robot[o.robotIdx].x;
			int y = robot[o.robotIdx].y;
			int d = map[x][y]; // 로봇의 방향

			switch (o.order) {
			case 'L': // 로봇이 향하고 있는 방향을 기준으로 왼쪽으로 90도 회전
				o.repeat %= 4;
				for (int i = 0; i < o.repeat; i++) {
					if (--d < 1)
						d = 4;
				}

				map[x][y] = d;
				break;
			case 'R': // 로봇이 향하고 있는 방향을 기준으로 오른쪽으로 90도 회전
				o.repeat %= 4;
				for (int i = 0; i < o.repeat; i++) {
					if (++d > 4)
						d = 1;
				}

				map[x][y] = d;
				break;
			case 'F': // 로봇이 향하고 있는 방향을 기준으로 앞으로 한 칸 전진
				for (int i = 0; i < o.repeat; i++) {
					int nx = x + (dx[d - 1]);
					int ny = y + (dy[d - 1]);

					// 벽에 충돌한 경우
					if (nx < 0 || nx >= X || ny < 0 || ny >= Y) {
						bw.write("Robot " + (o.robotIdx + 1) + " crashes into the wall");
						return;
					}

					// 다른 로봇에 충돌한 경우
					if (map[nx][ny] != 0) {
						for (int j = 0; j < robot.length; j++) {
							if (nx == robot[j].x && ny == robot[j].y) { // 충돌한 로봇 번호 탐색
								bw.write("Robot " + (o.robotIdx + 1) + " crashes into robot " + (j + 1));
								return;
							}
						}
					}
					robot[o.robotIdx].x = nx;
					robot[o.robotIdx].y = ny;
					map[nx][ny] = map[x][y];
					map[x][y] = 0;

					x = nx;
					y = ny;
				}
				break;
			}
		}
		bw.write("OK");
	}
}
