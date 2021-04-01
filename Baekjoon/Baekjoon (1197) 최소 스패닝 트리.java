import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge> {
		int to;
		int weight;

		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// 가중치 기준 오름차순 정렬 -> 가중치가 음수도 가능하므로 Integer.compare() 사용
			return Integer.compare(this.weight, o.weight); 
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수

		boolean[] visited = new boolean[V]; // 방문 체크 배열
		int res = 0; // 결과 변수(최소 신장 트리의 가중치 합)

		// list 생성
		LinkedList<Edge> list[] = new LinkedList[V];
		for (int i = 0; i < V; i++) {
			list[i] = new LinkedList<>();
		}

		// 입력 받은 간선 정보로 양 방향 인접리스트 생성(가중치가 적은 순으로 정렬)
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int v1 = Integer.parseInt(st.nextToken()) - 1; // 정점 1
			int v2 = Integer.parseInt(st.nextToken()) - 1; // 정점 2
			int weight = Integer.parseInt(st.nextToken()); // 가중치

			list[v1].add(new Edge(v2, weight));
			list[v2].add(new Edge(v1, weight));
		}

		PriorityQueue<Edge> pqueue = new PriorityQueue<>();
		pqueue.offer(new Edge(0, 0)); // 0번 정점부터 시작

		while (!pqueue.isEmpty()) {
			Edge edge = pqueue.poll();

			if (!visited[edge.to]) {
				visited[edge.to] = true; // 방문 표시

				res += edge.weight; // 가중치 더하기

				// 그래프에 연결된 노드들 중 방문하지 않은 노드를 큐에 넣어줌.
				for (Edge e : list[edge.to]) {
					if (!visited[e.to]) {
						pqueue.add(e);
					}
				}
			}
		}
		System.out.println(res);
	}
}
