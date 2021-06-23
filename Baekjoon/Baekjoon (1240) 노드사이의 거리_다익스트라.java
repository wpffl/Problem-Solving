import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int to; // 도착점
		int distance; // 거리

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.distance = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}
	}

	static int N, M;
	static ArrayList<Node>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 노드의 개수
		M = Integer.parseInt(st.nextToken()); // 노드 사이의 거리를 알고 싶은 개수

		list = new ArrayList[N]; // 노드의 정보를 가지고 있는 list
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}

		// 입력 받은 간선의 정보 저장
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken()) - 1; // 출발지
			int to = Integer.parseInt(st.nextToken()) - 1; // 도착지
			int distance = Integer.parseInt(st.nextToken()); // 거리

			list[from].add(new Node(to, distance));
			list[to].add(new Node(from, distance));
		}

		// 노드의 거리 계산
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken()) - 1; // 출발지
			int end = Integer.parseInt(st.nextToken()) - 1; // 도착지

			int distance = dijkstra(start, end);
			bw.write(distance + "\n");
		}

		bw.flush();
	}

	private static int dijkstra(int start, int end) {
		boolean[] visited = new boolean[N]; // 방문 체크 배열
		int[] distance = new int[N]; // 시작 정점에서의 거리 최소 비용
		Arrays.fill(distance, Integer.MAX_VALUE); // 최대 값으로 초기화
		
		PriorityQueue<Integer> pqueue = new PriorityQueue<>();
		
		// 출발지 설정
		pqueue.offer(start); 
		distance[start] = 0;

		while (!pqueue.isEmpty()) {
			int current = pqueue.poll();
			
			if(current == end) // 도착지인 경우
				break;
			
			if(!visited[current]) { // 방문 체크
				visited[current]= true; 
				
				for(Node n : list[current]) { // 현재 current와 연결된 다른 정점들 검사
					// 방문한적 없고, 바로 n까지 가는 비용보다 current를 거쳐 가는 비용이 더 최소인 경우
					if(!visited[n.to] && distance[current] + n.distance < distance[n.to]) {
						distance[n.to] = distance[current] + n.distance;
						pqueue.add(n.to);
					}
				}
			}
		}
		return distance[end];
	}
}
