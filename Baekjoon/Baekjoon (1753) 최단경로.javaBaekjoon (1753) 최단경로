import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
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
			return this.weight - o.weight; // 오름차순 정렬
		}
	}
	
	static int[] distance;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수

		LinkedList<Node>[] adjList = new LinkedList[V + 1]; // 인접 리스트
		for (int i = 0; i <= V; i++) {
			adjList[i] = new LinkedList<>();
		}
		
		int start = Integer.parseInt(br.readLine());

		// 인접 리스트 생성
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken()); // 가중치
		
			adjList[from].add(new Node(to, w));
		}
		
		distance = new int[V + 1]; // 시작 정점에서의 거리의 최소 비용
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		dijkstra(adjList, new boolean[V + 1], start, V);
		
		// start 정점에서 i번 정점까지의 최단 경로값 출력
		for (int i = 1; i <= V; i++) {
			if(distance[i] == Integer.MAX_VALUE) // 경로가 존재하지 않는 경우
				bw.write("INF");
			else
				bw.write(String.valueOf(distance[i]));
			
			bw.newLine();
		}
		
		br.close();
		bw.close();
	}

	private static void dijkstra(LinkedList<Node>[] adjList, boolean[] visited, int start, int V) {
		PriorityQueue<Node> pqueue = new PriorityQueue<>();
		
		pqueue.add(new Node(start, 0));
		distance[start] = 0; // 시작 정점 표시
		
		while(!pqueue.isEmpty()) {
			int current = pqueue.poll().to;
			
			if(!visited[current])
				visited[current] = true;
			
			for (Node node : adjList[current]) {
				if(distance[current] + node.weight < distance[node.to]) {
					distance[node.to] = distance[current] + node.weight;
					pqueue.add(new Node(node.to, distance[node.to]));
				}
			}
		}
	}
}
