import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, max;
	static char[][] arr;
	static boolean[] visited;
	static int[] dx = { 0, 1, 0, -1 }; // 방향 배열 우하좌상
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new char[R][];
		visited = new boolean[26];
		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		visited[arr[0][0] - 'A'] = true; // abcd ... 순서대로 index로 쓰기 위해 'A' 빼줌
		max = Integer.MIN_VALUE;
		dfs(0, 0, 1);
		System.out.println(max);
		br.close();
	}

	public static void dfs(int x, int y, int length) {

		visited[arr[x][y] - 'A'] = true; 

		for (int i = 0; i < 4; i++) {
			// 4방 탐색
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < R && ny >= 0 && ny < C) { // 범위체크
				if (!visited[arr[nx][ny] - 'A']) { // 앞에 같은 알파벳이 나온적 있는지 체크
					dfs(nx, ny, length + 1);
				}
			}
		}
		visited[arr[x][y] - 'A'] = false;
		max = Math.max(max, length);
	}
}
