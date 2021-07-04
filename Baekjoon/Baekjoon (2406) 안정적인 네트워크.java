import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
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
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken()); // 컴퓨터의 개수
		int m = Integer.parseInt(st.nextToken()); // 간선의 개수
		parent = new int[n + 1];
		int totalWeight = 0; // MST 길이
		int edgeCnt = 0; // 연결된 간선 수

		make(n); // 크기가 1인 단위집합 생성

		// 입력받은 연결된 컴퓨터 쌍
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (union(a, b))
				++edgeCnt;
		}

		PriorityQueue<Node> pqueue = new PriorityQueue<>();
		ArrayList<Node> list = new ArrayList<>();

		// 입력받은 컴퓨터 연결비용
		for (int i = 1; i <= n; i++) {
			String[] input = br.readLine().split(" ");

			if (i == 1) // 1번 컴퓨터는 본사컴퓨터라 제외
				continue;

			for (int j = i + 1; j <= n; j++) {
				pqueue.add(new Node(i, j, Integer.parseInt(input[j - 1])));
			}
		}

		while (!pqueue.isEmpty() && edgeCnt < n - 2) {
			Node node = pqueue.poll();

			if (union(node.from, node.to)) {
				totalWeight += node.weight;
				++edgeCnt;
				list.add(new Node(node.from, node.to, 0));
			}
		}

		// 출력
		bw.write(totalWeight + " " + list.size() + "\n");
		for (Node node : list) {
			bw.write(node.from + " " + node.to + "\n");
		}
		bw.flush();
	}

	private static void make(int n) {
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
	}

	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;

		parent[bRoot] = aRoot;
		return true;
	}

	private static int findSet(int a) {
		if (parent[a] == a)
			return a;

		return parent[a] = findSet(parent[a]);
	}
}
