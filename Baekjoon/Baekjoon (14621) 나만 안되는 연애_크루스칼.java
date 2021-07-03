import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int from;
		int to;
		int weight;

		public Node(int from, int to, int weight) {
			this.from = from;
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
	static int[] parent;
	static ArrayList<Node> list;
	static int[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 학교의 수
		M = Integer.parseInt(st.nextToken()); // 학교를 연결하는 도로의 개수

		gender = new char[N]; // 각 대학이 여대인지 남대인지 표시
		parent = new int[N];
		list = new ArrayList<>();

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

			list.add(new Node(from, to, weight));
		}

		bw.write(String.valueOf(kruskal()));
		bw.flush();
	}

	private static int kruskal() {
		int totalWeight = 0; // MST 길이
		int edgeCnt = 0; // MST 연결된 간선 수

		/* init */
		Collections.sort(list); // 정렬
		make(); // 크기가 1인 단위집합 생성

		for (Node n : list) {
			if (union(n.from, n.to)) { // 사이클이 발생하지 않은 경우
				totalWeight += n.weight;

				if (++edgeCnt == N - 1) // MST가 완성된 경우
					break;
			}
		}
		
		if(edgeCnt != N - 1) // MST를 만들 수 없는 경우
			return -1;

		return totalWeight;
	}

	private static void make() {
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}

	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if(gender[a] == gender[b]) // 성별이 같은 경우
			return false;
		
		if (aRoot == bRoot) // 이미 합쳐져 있는 경우
			return false;

		parent[bRoot] = aRoot;

		return true;
	}

	private static int findSet(int a) {
		if (parent[a] == a)
			return a;

		return parent[a] = findSet(parent[a]);
	}

}
