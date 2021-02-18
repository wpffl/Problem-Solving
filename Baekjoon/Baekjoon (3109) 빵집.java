import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, res;
	static char[][] arr;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new char[R][];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		makePipe();

		br.close();
		System.out.println(res);
		//bw.write(String.valueOf(res));
		//bw.close();
	}

	private static void makePipe() {
		// 윗행부터 시도
		for (int i = 0; i < R; i++) {
			visited[i][0] = true;
			dfs(i, 0);
		}
	}

	static int[] dr = { -1, 0, 1 };

	private static boolean dfs(int r, int c) {

		// 마지막 열에 도착한 경우
		if (c == C - 1) {
			res++; // 경우의 수 증가
			return true; // 파이프 놓기 성공
		}

		int nr, nc = c + 1;

		// 오른쪽 대각선 위, 오른쪽, 오른쪽 대각선 아래 위치 탐색
		for (int i = 0; i < 3; i++) {
			nr = r + dr[i];

			// 경계 체크, 건물 체크, 방문 체크
			if (nr < 0 || nr >= R || arr[nr][nc] == 'x' || visited[nr][nc])
				continue;

			visited[nr][nc] = true;
			if (dfs(nr, nc))
				return true;
			/* 실패했던 흔적 되돌리지 않기: 뒤의 선택지의 방향은 현재보다 유리하지 않은 상황이고 결국 같은 상황 */
//			visited[nr][nc] = false; 
		}

		return false; // 파이프 놓기 실패
	}
}
