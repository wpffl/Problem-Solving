import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] map = new boolean[100][100];
		int res = 0;

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 색종이 면적 true로 체크
			for (int j = x - 1; j < x + 9; j++) {
				for (int k = y - 1; k < y + 9; k++) {
					map[j][k] = true;
				}
			}
		}

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		/* 둘레 길이 체크(상하좌우 한면이라도 0이면 둘레!) */
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j]) {
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						// 경계선에 있는 애 -> 무조건 둘레
						if (nx < 0 || nx >= 100 || ny < 0 || ny >= 100) {
							res++;
							continue;
						}
						if (!map[nx][ny]) {
							res++;
						}
					}
				}
			}
		}
		System.out.println(res);
	}
}
