import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Integer>> list;
	static int person1, person2;
	static int res;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 전체 사람 수
		list = new ArrayList<>();
		// 사람 수만큼 list 생성
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}

		// 촌수 계산해야 할 두 사람
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		person1 = Integer.parseInt(st.nextToken());
		person2 = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(br.readLine()); // 부모 자식들 간의 관계의 개수
		// 양방향 리스트 생성
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			list.get(x).add(y);
			list.get(y).add(x);
		}
		res = -1;
		dfs(new boolean[N + 1], 0, person1);
		System.out.println(res);
	}

	private static void dfs(boolean[] visited, int cnt, int next) {
		visited[next] = true;

		for (int i : list.get(next)) {
			if (!visited[i]) {
				if (i == person2) {
					res = cnt + 1;
					return;
				}

				dfs(visited, cnt + 1, i);
			}
		}
	}
}
