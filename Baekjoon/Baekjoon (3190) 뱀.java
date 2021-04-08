import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/* 뱀의 몸 좌표를 가지고 있는 class */
	static class Loc {
		int x;
		int y;

		public Loc(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	/* 방향 전환 초와 변환할 방향을 저장하는 class */
	static class ChangeDir implements Comparable<ChangeDir> {
		int time; // 해당 초 후에
		char dir; // 방향 변환 (L: 왼쪽으로 90도, D: 오른쪽으로 90도)

		public ChangeDir(int time, char dir) {
			super();
			this.time = time;
			this.dir = dir;
		}

		@Override
		public int compareTo(ChangeDir o) {
			return this.time - o.time; // 시간 순으로 오름차순 정렬
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine()); // 보드의 크기
		int K = Integer.parseInt(br.readLine()); // 사과의 개수

		int[][] map = new int[N][N]; // 보드의 상태를 나타내는 배열(1:사과 있음. 2:뱀의 몸)

		// 입력받은 사과의 좌표 저장
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;

			map[x][y] = 1; // 사과 표시
		}

		// 입력 받은 방향 변경 정보  저장
		int L = Integer.parseInt(br.readLine()); // 방향 변환 횟수
		Queue<ChangeDir> queue = new LinkedList<>(); // 방향 변환의 정보를 담는 큐
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int time = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);

			queue.offer(new ChangeDir(time, dir));
		}

		// 입력 끝!! -------------------------------------------------------
		/* 방향 세팅 */
		int[] dx = { 0, 1, 0, -1 }; // 우하좌상
		int[] dy = { 1, 0, -1, 0 };
		int D = 0; // 처음 뱀의 방향은 오른쪽
		
		/* 뱀 세팅 */
		map[0][0] = 2; // 뱀의 몸 표시
		Deque<Loc> snake = new LinkedList<>(); // 뱀의 몸 좌표를 저장할 큐
		snake.offer(new Loc(0, 0));
		
		/* 시간 세팅 */
		int time = 0; // 결과 변수(게임이 몇 초에 종료되는지 표시)
		ChangeDir cd = queue.poll(); // 처음 방향 변경 시간 빼내기

		/* 뱀 게임 시작 */
		while (true) {
			++time; // 1초 카운트

			int nx = snake.getFirst().x + dx[D]; // 다음 이동할 X
			int ny = snake.getFirst().y + dy[D]; // 다음 이동할 Y

			// 뱀이 벽에 부딪힌 경우: 게임 종료
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				break;

			// 뱀이 자기 몸에 부딪힌 경우: 게임 종료
			if (map[nx][ny] == 2)
				break;

			// 그 외의 경우
			if (map[nx][ny] == 1) { // 도착한 곳에 사과가 있으면
				// 몸길이 늘어나고 사과는 없어짐
				map[nx][ny] = 2;
				snake.offerFirst(new Loc(nx, ny));
			} else { // 도착한 곳에 사과가 없으면
				// 꼬리 줄임 -> 몸 길이 그대로
				map[nx][ny] = 2;
				snake.offerFirst(new Loc(nx, ny));

				Loc tail = snake.pollLast();
				map[tail.x][tail.y] = 0;
			}

			// 방향 변경 시간이 현재 시간보다 작을 경우 새로운 방향 변경 시간 얻음.
			if (cd.time < time) {
				if (!queue.isEmpty())
					cd = queue.poll();
			}
			// 방향 변경 시간인 경우 방향 변경!
			if (cd.time == time) { 
				if (cd.dir == 'L') { // 왼쪽 90도
					if (--D < 0)
						D = 3;
				} else if (cd.dir == 'D') { // 오른쪽 90도
					if (++D > 3)
						D = 0;
				}
			}
		}

		System.out.println(time);
	}
}
