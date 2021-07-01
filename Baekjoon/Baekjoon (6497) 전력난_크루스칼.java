import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

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
	
	static int m, n;
	static ArrayList<Node> list;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken()); // 집의 수
			n = Integer.parseInt(st.nextToken()); // 길의 수

			if (m == 0 && n == 0) // 입력의 끝인 경우 -> 테케 끝
				break;

			int totalWeight = 0; // 전체 길의 길이
			parent = new int[m];
			list = new ArrayList<>(); // 인접리스트
			

			// 입력받은 길의 정보로 양방향 인접리스트 생성
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());

				list.add(new Node(from, to, weight));

				totalWeight += weight;
			}

			// 절약한 길이를 구하기 위해 전체 길의 길이 - MST의 길이
			bw.write(String.valueOf(totalWeight - kruskal()) + "\n");
		}
		bw.flush();
	}

	private static int kruskal() {
		int totalWeight = 0; // MST의 길이
		int edgeCnt = 0; // 연결된 간선 수

		/* init */
		Collections.sort(list); // 가중치가 작은 순으로 정렬
		make(); // 크기가 1인 단위집합 생성
		
		for (Node n : list) {
			if(union(n.from, n.to)) { // true: 사이클 발생하지 않은 경우
				totalWeight += n.weight;
				
				if(++edgeCnt == m -1) // MST가 완성된 경우, break
					break;
			}
		}
		
		return totalWeight;
	}

	private static void make() {
		for (int i = 0; i < m; i++) {
			parent[i] = i;
		}
	}

	/* 두 개의 집합을 합치는 함수 */
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) // 이미 합쳐져 있는 경우
			return false;
		
		parent[bRoot] = aRoot;
		return true;
	}

	/* 인자로 들어온 원소가 속한 집합의 대표자를 탐색 */
	private static int findSet(int a) {
		if(parent[a] == a)
			return a;
		
		return parent[a] = findSet(parent[a]);
	}
}
