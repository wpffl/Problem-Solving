import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static LinkedList<Node>[] adjList;

	static class Node implements Comparable<Node> {
		int to; // 도착 정점
		int weight; // 가중치

		public Node(int to, int weight) {
			super();
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
		adjList = new LinkedList[N]; // 인접 리스트
		for (int i = 0; i < N; i++) {
			adjList[i] = new LinkedList<>();
		}

		// 입력받은 간선 정보로 인접 리스트 생성
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());

			adjList[from].add(new Node(to, weight));
			adjList[to].add(new Node(from, weight));
		}

		prim();
	}

	private static void prim() {
		boolean[] visited = new boolean[N];
		PriorityQueue<Node> pqueue = new PriorityQueue<>();
		pqueue.offer(new Node(0, 0)); // 0번 정점부터 시작

		int res = 0; // 결과 변수(최소 간선 비용)
		int cnt = 0; // 연결된 간선 개수

		while (!pqueue.isEmpty()) {
			Node node = pqueue.poll();

			if (!visited[node.to]) {
				visited[node.to] = true;
				res += node.weight;

				// 해당 정점과 연결된 노드들 중 방문하지 않은 노드를 큐에 넣음
				for (Node n : adjList[node.to]) {
					if (!visited[n.to])
						pqueue.add(n);
				}

				if (++cnt == N)
					break;
			}
		}

		System.out.println(res);
	}
}
