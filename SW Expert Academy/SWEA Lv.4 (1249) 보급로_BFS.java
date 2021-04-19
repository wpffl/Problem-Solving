import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	private static int[][] map, minTime;
	private static int N, INF = Integer.MAX_VALUE;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			minTime = new int[N][N]; // 각 위치까지의 최소시간저장 목적, 너비가 크지만 시간이 더 최소면 같은 자리 또 지나감.
									 // 너비가 같거나 크며 시간이 더 걸리면 해당 자리 지나가지 않음.(가지치기 효과)

			for (int i = 0; i < N; ++i) {
				char[] ch = br.readLine().toCharArray();
				for (int j = 0; j < N; ++j) {
					map[i][j] = ch[j] - '0';
					minTime[i][j] = INF;
				}
			}
			// 출력
			bw.write("#" + t + " " + bfs(0, 0));
			bw.newLine();
		}
		bw.flush();
	}

	private static int bfs(int startX, int startY) {
		int recoveryTime = INF;
		Queue<int[]> queue = new LinkedList<int[]>();

		minTime[startX][startY] = 0;
		queue.offer(new int[] { startX, startY });

		int x, y, nx, ny, current[];
		while (!queue.isEmpty()) {
			current = queue.poll();
			x = current[0];
			y = current[1];

			if (x == N - 1 && y == N - 1) {
				// 먼저 처리된 경로의 최소시간보다 더 작다면 최소값 갱신
				if (minTime[x][y] < recoveryTime)
					recoveryTime = minTime[x][y];
				continue;
			}

			for (int d = 0; d < 4; ++d) {
				nx = x + dx[d];
				ny = y + dy[d];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && minTime[x][y] + map[nx][ny] < minTime[nx][ny]) {
					minTime[nx][ny] = minTime[x][y] + map[nx][ny];
					queue.offer(new int[] { nx, ny });
				}
			}
		}
		return recoveryTime;
	}
}
