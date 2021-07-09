import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int to;
		int weight;

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

	static int N, E;
	// Integer.MAX>VALUE로 하면 오버플로우 남. 
	// 간선의 최대 개수는 200,000이고, 가중치의 최대값은 1,000이므로 다음과 같이 설정
	static int INF = 200000000; 
	static ArrayList<Node>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수

		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}

		// 입력받은 간선의 정보 저장
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());

			// 양방향
			list[from].add(new Node(to, weight));
			list[to].add(new Node(from, weight));
		}

		// 반드시 거쳐야하는 두 개의 정점
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken()) - 1;
		int b = Integer.parseInt(st.nextToken()) - 1;

		bw.write(String.valueOf(sol(a, b)));
		bw.flush();
	}

	private static int sol(int a, int b) {
		int res1 = 0; // 1. 0 -> a -> b -> N-1 로 가는 최단거리
		int res2 = 0; // 2. 0 -> b -> a -> N-1 로 가는 최단거리

		// 방법 1.
		res1 += dijkstra(0, a);
		res1 += dijkstra(a, b);
		res1 += dijkstra(b, N - 1);

		// 방법 2.
		res2 += dijkstra(0, b);
		res2 += dijkstra(b, a);
		res2 += dijkstra(a, N - 1);

		if(res1 >= INF && res2 >= INF)
			return -1;
		else
			return Math.min(res1, res2);
	}

	private static int dijkstra(int start, int end) {
		boolean[] visited = new boolean[N]; 
		int[] distance = new int[N];
		Arrays.fill(distance, INF);
		
		PriorityQueue<Node> pqueue = new PriorityQueue<>();
		
		// 출발지 설정
		pqueue.add(new Node(start, 0));
		distance[start] = 0;
		
		while(!pqueue.isEmpty()) {
			Node cur = pqueue.poll();
			
			if(cur.to == end) // 도착지인 경우
				break;
			
			if(!visited[cur.to]) { // 방문 체크
				visited[cur.to] = true; 
				
				for(Node n : list[cur.to]) { // 나와 연결된 정점들 탐색
					// 방문한적 없고, 바로 n까지 가는 비용보다 current를 거쳐 가는 비용이 더 최소인 경우
					if(!visited[n.to] && distance[cur.to] + n.weight < distance[n.to]) {
						distance[n.to] = distance[cur.to] + n.weight;
						pqueue.add(new Node(n.to, distance[n.to]));
					}
				}
			}
		}
		
		return distance[end];
	}
}
