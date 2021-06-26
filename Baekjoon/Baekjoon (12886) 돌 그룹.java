import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int A, B, C, sum, res;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		A = Integer.parseInt(st.nextToken()); // A 그룹 돌의 개수
		B = Integer.parseInt(st.nextToken()); // B 그룹 돌의 개수
		C = Integer.parseInt(st.nextToken()); // C 그룹 돌의 개수
		sum = A + B + C;
		visited = new boolean[2500][2500];

		if (sum % 3 != 0) { // A B C를 3으로 나눌 수 없으면 바로 결과 출력
			bw.write("0");
		} else {
			dfs(A, B);
			bw.write(String.valueOf(res));
		}
		bw.flush();
	}

	private static void dfs(int a, int b) {
		int c = sum - a - b;
		if (a == b && b == c) { // 세 그룹의 돌의 수가 같으면 return
			res = 1;
			return;
		}

		sol(a, b);
		sol(b, c);
		sol(a, c);
	}

	private static void sol(int a, int b) {
		int small = Math.min(a, b);
		int big = Math.max(a, b);

		// visited[3][4]와 visited[4][3]은 똑같은 의미!
		if (!visited[small * 2][big - small] || !visited[big - small][small * 2]) {
			visited[small * 2][big - small] = visited[big - small][small * 2] = true;
			dfs(small * 2, big - small);
		}
	}
}
