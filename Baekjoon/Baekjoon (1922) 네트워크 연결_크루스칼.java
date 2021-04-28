import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static LinkedList<Node> list;
	static int[] parent;

	static class Node implements Comparable<Node> {
		int from; // 시작 정점
		int to; // 도착 정점
		int weight; // 가중치

		public Node(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 정점 수
		M = Integer.parseInt(br.readLine()); // 간선 수
		list = new LinkedList<>(); // 간선 정보 리스트
		parent = new int[N];

		// 입력받은 간선 정보 저장
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());

			list.add(new Node(from, to, weight));
		}

		kruskal();
	}

	private static void kruskal() {
		Collections.sort(list);

		make();

		int res = 0; // 결과 변수(최소 간선 비용)
		int cnt = 0; // 연결된 간선 수

		for (Node node : list) {
			if (union(node.from, node.to)) { // 순환이 없으면
				res += node.weight;

				if (++cnt == N - 1)
					break;
			}
		}

		System.out.println(res);
	}

	/* 크기가 1인 단위 집합 만들어주는 함수 */
	private static void make() {
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}

	/* 인자로 들어온 두 개의 집합을 합치는 함수 */
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;

		parent[bRoot] = aRoot;
		return true;
	}

	/* 인자로 들어온 집합의 root를 구하는 함수 */
	private static int findSet(int a) {
		if (parent[a] == a) {
			return a;
		}

		return parent[a] = findSet(parent[a]);
	}
}
