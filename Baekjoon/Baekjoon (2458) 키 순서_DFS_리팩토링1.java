import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] adj, radj;
	static int cnt; // 나보다 키가 큰 학생 수 + 나보다 키가 작은 학생 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 두 학생 키를 비교한 횟수
		int res = 0; // 결과 변수(자신이 키가 몇번째인지 알 수 있는 학생의 수)

		adj = new int[N][N]; // 인접 행렬
		radj = new int[N][N]; // 행, 열을 뒤집은 인접 행렬

		// 입력 받은 학생들의 키를 비교한 결과 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1; // 키가 작은 학생
			int b = Integer.parseInt(st.nextToken()) - 1; // 키가 큰 학생

			adj[a][b] = 1; // a는 b보다 키가 작다(키 작은 -> 큰)
			radj[b][a] = 1; // b는 a보다 키가 크다(키 큰 -> 작은)
		}

		// 모든 학생에 대해 자신보다 큰 학생, 자신보다 작은 학생 탐색
		for (int i = 0; i < N; i++) {
			cnt = 0;
			dfs(i, adj, new boolean[N]);
			dfs(i, radj, new boolean[N]);

			if (cnt == N - 1) // 나보다 큰 학생 수 + 나보다 작은 학생 수가 나를 제외한 학생 수와 같으면
				res++; // 나의 키가 몇번째 순서인지 알 수 있으므로 카운트
		}
		System.out.println(res);
	}

	/* adj에 따라 자신보다 큰 학생을 탐색하거나 자신보다 작은 학생을 탐색하는 함수 */
	private static void dfs(int cur, int[][] adj, boolean[] visited) {
		visited[cur] = true;

		// 모든 학생 검사
		for (int i = 0; i < N; i++) {
			if (!visited[i] && adj[cur][i] == 1) {  
				dfs(i, adj, visited);
				cnt++;
			}
		}
	}
}
