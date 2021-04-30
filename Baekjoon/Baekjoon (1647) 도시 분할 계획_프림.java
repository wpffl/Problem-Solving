import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static LinkedList<Node>[] adjlist;

	static class Node implements Comparable<Node>{
		int to; // 도착 정점
		int weight; // 가중치

		public Node(int to, int weight) {
			super();
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
		adjlist = new LinkedList[N];
		for (int i = 0; i < N; i++) {
			adjlist[i] = new LinkedList<>();
		}

		// 입력받은 간선 정보로 양방향 인접리스트 생성
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());

			adjlist[from].add(new Node(to, weight));
			adjlist[to].add(new Node(from, weight));
			
		}

		prim();
	}

	private static void prim() {
		boolean[] visited = new boolean[N];
		PriorityQueue<Node> pqueue = new PriorityQueue<>(); // 가중치 순으로 정렬된 Priority Queue
		pqueue.offer(new Node(0, 0)); // 0번 부터 시작
		
		int maxWeight = Integer.MIN_VALUE; // 최소 신장 트리 중 가장 가중치가 큰 간선 비용(얘를 제거하면 마을이 2개가 됨)
		int totalWeight = 0; // 총 간선 비용
		int cnt = 0; // 연결된 간선 수
		
		while(!pqueue.isEmpty()) {
			Node node = pqueue.poll();
			
			if(!visited[node.to]) { // 방문하지 않은 정점의 경우
				visited[node.to] = true;
				
				totalWeight += node.weight;
				maxWeight = Math.max(maxWeight, node.weight);
								
                if(cnt++ == N - 1)
					break;
                
				for(Node n : adjlist[node.to]) {
					if(!visited[n.to]) {
						pqueue.offer(n);
					}
				}
			}
		}

		System.out.println(totalWeight - maxWeight);
	}
}
