import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge>{
		int to; // 도착 정점
		int weight; // 가중치
		
		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight; // 가중치 기준으로 오름차순 정렬
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine()); // 정점의 개수
		int M = Integer.parseInt(br.readLine()); // 간선의 개수
		int res = 0; // 결과 변수(출발점에서 도착점으로 가는데 드는 최소 비용)
		
		// 인접리스트 선언
		LinkedList<Edge>[] list = new LinkedList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new LinkedList<>();
		}

		// 입력받은 간선의 정보로 단방향 인접리스트 생성 
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken()) - 1; // 시작 정점
			int to = Integer.parseInt(st.nextToken()) - 1; // 도착 정점
			int weight = Integer.parseInt(st.nextToken()); // 가중치
			
			list[from].add(new Edge(to, weight));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken()) - 1; // 출발 도시
		int end = Integer.parseInt(st.nextToken()) - 1; // 도착 도시

		// 다익스트라 알고리즘-------------------------------------------------------------
		
		boolean[] visited = new boolean[N]; // 해당 정점을 방문했는지 확인하는 배열
		int[] distance = new int[N]; // 시작 정점에서의 거리의 최소 비용
		
		Arrays.fill(distance, Integer.MAX_VALUE); // 최대 값으로 초기화
		distance[start] = 0; // 출발지 표시
		
		PriorityQueue<Edge> pqueue = new PriorityQueue<>();
		pqueue.offer(new Edge(start, 0)); // 시작 정점부터 출발
		
		while(!pqueue.isEmpty()) {
			Edge edge = pqueue.poll();
			int current = edge.to;
			
			if(edge.to == end) // 다음 정점이 도착지인 경우, break
				break;
			
			if(!visited[current]) { 
				visited[current] = true; // 방문 체크
				
				for(Edge e : list[current]) { // 현재 current 정점과 연결된 다른 정점들 하나씩 검사
					// current와 연결된 정점인 e를 방문한적 없으면서, 바로 e까지 가는 비용보다 current를 거쳐서 가는 비용이 더 최소인 경우
					if(!visited[e.to] && distance[current] + e.weight < distance[e.to]) {
						distance[e.to] = distance[current] + e.weight;
						pqueue.add(new Edge(e.to, distance[e.to]));
					}
				}
			}
		}
		
		System.out.println(distance[end]);
	}
}
