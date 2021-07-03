import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int to;
		int weight;

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight; // 가중치 기준 오름차순 정렬
		}
	}

	static int N, M;
	static char[] gender;
	static ArrayList<Node>[] list;
	static int[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 학교의 수
		M = Integer.parseInt(st.nextToken()); // 학교를 연결하는 도로의 개수

		gender = new char[N]; // 각 대학이 여대인지 남대인지 표시
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}

		// 입력받은 성별 정보 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			gender[i] = st.nextToken().charAt(0);
		}

		// 입력받은 경로 정보 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());

			list[from].add(new Node(to, weight));
			list[to].add(new Node(from, weight));
		}

		bw.write(String.valueOf(prim()));
		bw.flush();
	}

	private static int prim() {
		int totalWeight = 0; // MST 길이
		int edgeCnt = 0; // MST 연결된 간선 수
		boolean[] visited = new boolean[N];
		char preGender = 'A'; // 전에 연결한 성별

		/* init */
		PriorityQueue<Node> pqueue = new PriorityQueue<>();
		pqueue.add(new Node(0, 0));

		while (!pqueue.isEmpty()) {
			Node cur = pqueue.poll();

			if (!visited[cur.to]) {
				visited[cur.to] = true;
				totalWeight += cur.weight;
				preGender = gender[cur.to];
				
				if (edgeCnt++ == N - 1)
					break;
				
				for(Node n : list[cur.to]) {
					if (preGender != gender[n.to]) 
						pqueue.add(n);
				}
			}
		}

		if(edgeCnt != N) // MST를 만들 수 없는 경우
			return -1;
		
		return totalWeight;
	}
}
