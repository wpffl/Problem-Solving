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
		public int compareTo(Node o) { // 가중치 기준 오름차순 정렬
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 정점 수
		M = Integer.parseInt(st.nextToken()); // 간선 수
		list = new LinkedList<>();
		parent = new int[N];

		// 입력받은 간선 정보로 간선리스트 생성
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());

			list.add(new Node(from, to, weight));
		}

		kruskal();
	}

	private static void kruskal() {
		Collections.sort(list); // 정렬

		make(); // 크기가 1인 단위집합 생성

		int maxWeight = 0; // 최소 신장 트리 중 가장 가중치가 큰 간선 비용(얘를 제거하면 마을이 2개가 됨)
		int totalWeight = 0; // 총 간선 비용
		int count = 0; // 선택한 간선 수

		for (Node node : list) {
			if (union(node.from, node.to)) { // 사이클 발생안했으면
				totalWeight += node.weight;
				maxWeight = node.weight; // 가중치 기준으로 정렬했으므로 바로 대입

				if (++count == N - 1) // 총 N-1개의 간선이 연결된 경우(최소 신장 트리 생성 완료)
					break;
			}
		}

		System.out.println(totalWeight - maxWeight);
	}

	private static void make() {
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}

	/* 두 개의 집합을 합치는 함수 */
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot) // 이미 합쳐져 있는 경우
			return false;

		parent[bRoot] = aRoot;
		return true;
	}

	/* 인자로 들어온 원소가 속한 집합의 대표자를 탐색 */
	private static int findSet(int x) {
		if (parent[x] == x)
			return x;

		return parent[x] = findSet(parent[x]);
	}
}
