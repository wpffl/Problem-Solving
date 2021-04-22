import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
	static int N, M;
	static int[][] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 두 학생 키를 비교한 횟수
		int res = 0; // 결과 변수(자신이 키가 몇번째인지 알 수 있는 학생의 수)

		adj = new int[N + 1][N + 1]; // 인접 행렬 (adj[][0]: 자신보다 큰 학생 수, adj[0][]: 자신보다 작은 학생 수)

		// 메모제이션을 위해 adj[][0](나보다 큰 학생 수)에 초기값 -1 세팅 -> 탐색 여부 확인을 위해
		for (int i = 0; i <= N; i++) {
			adj[i][0] = -1;
		}

		// 입력 받은 학생들의 키를 비교한 결과 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()); // 키가 작은 학생
			int b = Integer.parseInt(st.nextToken()); // 키가 큰 학생

			adj[a][b] = 1; // a는 b보다 키가 작다(키 작은 -> 큰)
		}

		// 모든 학생에 대해 자신보다 큰 학생 탐색
		for (int i = 1; i <= N; i++) {
			if (adj[i][0] == -1) // 메모의 상태를 보고 아직 탐색 전이면 자신보다 큰 학생 쭉 따라 탐색
				dfs(i);
		}

		// 모든 학생에 대해 자신보다 작은 학생 계산
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				adj[0][j] += adj[i][j];
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (adj[i][0] + adj[0][i] == N - 1) // 나보다 큰 학생 수 + 나보다 작은 학생 수가 나를 제외한 학생 수와 같으면
				res++; // 나의 키가 몇번째 순서인지 알 수 있으므로 카운트
		}

		System.out.println(res);
	}

	/* 모든 학생을 기준으로 자신보다 큰 학생을 탐색하는 함수(단, 이미 탐색을 마친 학생은 재탐색 X) */
	private static void dfs(int cur) {

		// 모든 학생 검사
		for (int i = 1; i <= N; i++) {
			if (adj[cur][i] == 1) { // 나보다 큰 학생인 경우(cur < i)
				if (adj[i][0] == -1) // 아직 탐색 하지 않은 학생이면 탐색하러 감
					dfs(i);

				// i 학생을 탐색하고 왔거나, 이미 탐색이 되어있어서 탐색하지 않고 내려옴.
				if (adj[i][0] > 0) { // i학생보다 큰 학생이 있다면
					for (int j = 1; j <= N; j++) {
						if (adj[i][j] == 1) // i학생 보다 큰 j학생이면
							adj[cur][j] = 1; // cur학생 보다 큰 j학생으로 표시
					}
				}
			}
		}
		
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			cnt += adj[cur][i]; // cur학생 보다 큰 학생들만 더해짐
		}
		
		adj[cur][0] = cnt;
	}
}
