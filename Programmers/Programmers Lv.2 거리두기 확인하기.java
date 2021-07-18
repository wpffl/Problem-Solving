import java.util.LinkedList;
import java.util.Queue;

class Solution {
    	static class Pos {
		int x;
		int y;
		int len;

		public Pos(int x, int y, int len) {
			super();
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
    
    public int[] solution(String[][] places) {
		int[] answer = new int[5];

		for (int t = 0; t < 5; t++) {
			char[][] map = new char[5][5];
			boolean flag = true;

			for (int i = 0; i < 5; i++) {
				map[i] = places[t][i].toCharArray();
			}

			loop: for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (map[i][j] == 'P') {
						flag = bfs(map, i, j);

						if (!flag) {
							break loop;
						}
					}
				}
			}

			if (flag)
				answer[t] = 1;
			else
				answer[t] = 0;
		}

		return answer;
    }
    
    private static boolean bfs(char[][] map, int x, int y) {
		boolean[][] visited = new boolean[5][5];
		Queue<Pos> queue = new LinkedList<>();

		queue.offer(new Pos(x, y, 0));
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			Pos p = queue.poll();

			if (p.len == 2)
				continue;

			for (int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];

				if (isValid(nx, ny) && !visited[nx][ny]) {
					if (map[nx][ny] == 'P') {
						return false;
					} else if (map[nx][ny] == 'O') {
						visited[nx][ny] = true;
						queue.offer(new Pos(nx, ny, p.len + 1));
					}
				}
			}
		}

		return true;
	}

	private static boolean isValid(int nx, int ny) {
		if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5)
			return true;
		else
			return false;
	}
}
