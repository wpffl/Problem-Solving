import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static class Node implements Comparable<Node> {
		int from;
		int to;
		int weight;

		public Node(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight; // 가중치 기준 오름차순 정렬
		}
	}
	
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()); // 행성의 수
		ArrayList<Node> list = new ArrayList<>();
		parent = new int[N];
		 
		// 입력받은 행성간의 플로우 관리 비용 저장
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");

			for (int j = i + 1; j < N; j++) {
				list.add(new Node(i, j, Integer.parseInt(input[j])));
			}
		}
		
		bw.write(String.valueOf(kruskal(N, list)));
		bw.flush();
	}

	private static long kruskal(int N, ArrayList<Node> list) {
		long totalWeight = 0; // MST 길이
		int edgeCnt = 0; // MST 연결 간선 수
		
		/* init */
		Collections.sort(list);
		make(N);
		
		for(Node n : list) {
			if(union(n.from, n.to)) {
				totalWeight += n.weight;
				
				if(++edgeCnt == N -1) // MST가 완성된 경우
					break;
			}
		}
		
		return totalWeight;
	}

	private static void make(int N) {
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}

	/* 두 개의 집합을 합치는 함수 */
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;

		parent[bRoot] = aRoot;
		return true;
	}

	/* 인자로 들어온 원소가 속한 집합의 대표자를 탐색 */
	private static int findSet(int a) {
		if (parent[a] == a)
			return a;

		return parent[a] = findSet(parent[a]);
	}
}
