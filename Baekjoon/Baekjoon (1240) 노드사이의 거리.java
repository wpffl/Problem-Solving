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
	static class Node {
		int to; // 도착점
		int distance; // 거리

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.distance = weight;
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

			int distance = bfs(start, end);
			bw.write(distance + "\n");
		}

		bw.flush();
	}

	private static int bfs(int start, int end) {
		int distance = 0; // start와 end 노드 사이의 거리
		boolean[] visited = new boolean[N]; // 방문 체크 배열
		
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(start, 0)); // 출발지 표시
		visited[start] = true;

		while (!queue.isEmpty()) {
			Node current = queue.poll();
			
			if(current.to == end) { // 현재 노드가 도착지인 경우
				distance = current.distance;
				break;
			}
			
			for (Node next : list[current.to]) { // 현재 노드와 연결된 노드 중 방문하지 않은 곳을 방문
				if (!visited[next.to]) {
					visited[next.to] = true;
					queue.offer(new Node(next.to, current.distance + next.distance));
				}
			}
		}
		return distance;
	}
}
