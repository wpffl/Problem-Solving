import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static char[][] map;
	static boolean[][] visited;
	static LinkedList<Node> list;
	static int res = 0; // 결과 변수(연쇄 반응 횟수 카운트)

	static int dx[] = { -1, 1, 0, 0 }; // 상하좌우
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[12][6]; // 뿌요뿌요 게임의 필드 상태를 나타낼 배열
		visited = new boolean[12][6]; // 방문 체크
		list = new LinkedList<>(); // 색이 같은 뿌요의 리스트

		// 입력 받은 뿌요의 상태
		for (int i = 0; i < 12; i++) {
			String s = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		sol();
		System.out.println(res);
	}

	private static void sol() {
		while (true) {
			boolean flag = true; // while문 탈출을 위한 flag

			// visited 배열 초기화
			for (int i = 0; i < 12; i++) {
				Arrays.fill(visited[i], false);
			}

			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					// 뿌요인 경우 bfs 탐색
					if (!visited[i][j] && map[i][j] != '.')
						bfs(i, j);

					// bfs 탐색 후, list의 크기가 4 이상이면 뿌요 터트리기
					if (list.size() >= 4) {
						flag = false;
						for (Node node : list) {
							map[node.x][node.y] = '.';
						}
					}
					// list 초기화
					list.clear();
				}
			}
			down(); // 뿌요 내리기
			if (flag)
				break;
			else
				res += 1;
		}
	}

	/* 매개변수로 들어온 뿌요 기준으로 4방 탐색 */
	private static void bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<>();

		visited[x][y] = true;
		char c = map[x][y]; // 뿌요의 색깔 저장
		queue.offer(new Node(x, y)); // 찾은 뿌요 큐에 넣기

		list.add(new Node(x, y)); // 찾은 뿌요 리스트에 넣기(4개 모이면 터트리려고)
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();

			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];

				// 범위 체크 && 방문 체크 && 같은 색 뿌요인지 체크
				if (isValid(nx, ny) && !visited[nx][ny] && map[nx][ny] == c) {
					visited[nx][ny] = true;
					list.add(new Node(nx, ny));
					queue.offer(new Node(nx, ny));
				}
			}
		}
	}

	/* 뿌요 밑으로 내리는 함수 */
	private static void down() {
		for (int i = 0; i < 6; i++) {
			for (int j = 11; j > 0; j--) {
				if (map[j][i] == '.') {
					for (int k = j - 1; k >= 0; k--) {
						if(map[k][i] != '.') {
							map[j][i] = map[k][i];
							map[k][i] = '.';
							break;
						}
					}
				}
			}
		}
	}

	/* 배열 index 범위 체크해주는 함수 */
	private static boolean isValid(int nx, int ny) {
		return (nx >= 0 && nx < 12) && (ny >= 0 && ny < 6);
	}

}
