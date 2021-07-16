import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 구슬의 개수
		int M = Integer.parseInt(st.nextToken()); // 저울에 올려본 쌍의 개수

		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				map[i][j] = INF;
			}
		}

		// 입력받은 저울에 올려본 쌍의 무게 정보 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken()) - 1; // 더 무거운 구슬
			int b = Integer.parseInt(st.nextToken()) - 1; // 가벼운 구슬

			// b < a
			map[b][a] = -1; // b가 a보다 작음
			map[a][b] = 1; // a가 b보다 큼
		}

		bw.write(String.valueOf(floyd(N, map)));
		bw.flush();
	}

	private static int floyd(int N, int[][] map) {
		for (int k = 0; k < N; k++) { // 경유지
			for (int i = 0; i < N; i++) { // 출발지
				for (int j = 0; j < N; j++) { // 도착지
					if (map[i][k] == -1 && map[k][j] == -1) { // i < k < j 경우
						map[i][j] = -1; // i가 j보다 작다
						map[j][i] = 1; // j가 i보다 작다
					}
				}
			}
		}

		// 가벼운 구슬 개수가 중간보다 많은 경우, 무거운 구슬 개수가 중간보다 많은 경우 찾기
		int res = 0; // 결과 변수(중간이 절대로 될 수 없는 구슬 개수)
		int mid = N / 2;

		for (int i = 0; i < N; i++) {
			int lightCnt = 0;
			int heavyCnt = 0;
			
			for (int j = 0; j < N; j++) {
				if (map[i][j] == -1) // 나보다 가벼운 구슬이 있다
					++lightCnt;
				else if(map[i][j] == 1) // 나보다 무거운 구슬이 있다
					++heavyCnt;
			}
			
			if(mid < lightCnt || mid < heavyCnt)
				++res;
		}

		return res;
	}

}
