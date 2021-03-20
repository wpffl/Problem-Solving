import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int[] parents;
	static int[] rank;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int res; // 결과 변수

		int N = Integer.parseInt(st.nextToken()); // 학생 수
		int M = Integer.parseInt(st.nextToken()); // 같은 종교를 가진 학생의 쌍 수

		parents = new int[N + 1];
		rank = new int[N + 1]; // tree의 깊이 표시

		make(N + 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			unoin(a, b);
		}

		Set<Integer> set = new HashSet<>();
		for (int i = 1; i <= N; i++) {
			set.add(findSet(parents[i]));
		}
		System.out.println(set.size());
	}

	private static void make(int n) {
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}

	}

	private static int findSet(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	private static void unoin(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		// 항상 높이가 더 낮은 트리를 높이가 높은 트리 밑에 넣는다. 즉, 높이가 더 높은 쪽을 root로 삼음.
		if (rank[aRoot] < rank[bRoot]) { // 즉, 트리의 깊이를 서로 비교해서 작은것을 큰것아래에 붙인다.(트리의 깊이 최소화)
			parents[aRoot] = bRoot; // x의 root를 y로 변경한다.
		}
		// 그렇지 않은 경우엔 반대로
		else {
			parents[bRoot] = aRoot; // y의 root를 x로 변경한다.
			if (rank[aRoot] == rank[bRoot]) {
				rank[aRoot]++; // 만약 높이가 같다면 합친 후 x의 높이를 1 증가시킨다.(x의 높이 + 1)
			}
		}
	}
}
