import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static class Planet {
		int id; // 행성 번호
		int x;
		int y;
		int z;

		public Planet(int id, int x, int y, int z) {
			super();
			this.id = id;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static class Node implements Comparable<Node> {
		int from;
		int to;
		int weight;

		public Node(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight; // 가중치 기준 오름차순 정렬
		}
	}

	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine()); // 행성의 개수
		Planet[] p = new Planet[N]; // 행성의 정보 저장할 배열
		parent = new int[N];

		// 입력받은 행성의 좌표 정보 저장
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			p[i] = new Planet(i, x, y, z);
		}

		bw.write(String.valueOf(kruskal(N, p)));
		bw.flush();
	}

	private static int kruskal(int N, Planet[] p) {
		int totalWeight = 0; // MST의 길이
		int edgeCnt = 0; // MST 간선의 길이

		/* x, y, z 각각에 대해서 정렬하고 각 행성의 번호와 비용을 list에 추가. */
		ArrayList<Node> list = new ArrayList<>();

		// 1. x 기준 정렬
		Arrays.sort(p, (p1, p2) -> p1.x - p2.x);
		for (int i = 0; i < N - 1; i++) {
			int weight = Math.abs(p[i].x - p[i + 1].x);
			list.add(new Node(p[i].id, p[i + 1].id, weight));
		}

		// 2. y 기준 정렬
		Arrays.sort(p, (p1, p2) -> p1.y - p2.y);
		for (int i = 0; i < N - 1; i++) {
			int weight = Math.abs(p[i].y - p[i + 1].y);
			list.add(new Node(p[i].id, p[i + 1].id, weight));
		}

		// 3. z 기준 정렬
		Arrays.sort(p, (p1, p2) -> p1.z - p2.z);
		for (int i = 0; i < N - 1; i++) {
			int weight = Math.abs(p[i].z - p[i + 1].z);
			list.add(new Node(p[i].id, p[i + 1].id, weight));
		}

		/* init */
		make(N);
		Collections.sort(list);

		for (Node n : list) {
			if (union(n.from, n.to)) {
				totalWeight += n.weight;

				if (++edgeCnt == N - 1)
					break;
			}
		}

		return totalWeight;
	}

	private static void make(int N) {
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}

	/* 두 개의 집합을 합치는 함수 */
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;

		parent[bRoot] = aRoot;
		return true;
	}

	/* 인자로 들어온 원소가 속한 집합의 대표자를 탐색 */
	private static int findSet(int a) {
		if (parent[a] == a)
			return a;

		return parent[a] = findSet(parent[a]);
	}
}
