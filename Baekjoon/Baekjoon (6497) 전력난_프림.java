import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken()); // 집의 수
			int n = Integer.parseInt(st.nextToken()); // 길의 수

			if (m == 0 && n == 0) // 입력의 끝인 경우 -> 테케 끝
				break;

			int totalWeight = 0; // 전체 길의 길이

			ArrayList<Node>[] list = new ArrayList[m]; // 인접리스트
			for (int i = 0; i < m; i++) {
				list[i] = new ArrayList<>();
			}

			// 입력받은 길의 정보로 양방향 인접리스트 생성
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());

				list[from].add(new Node(to, weight));
				list[to].add(new Node(from, weight));

				totalWeight += weight;
			}

			// 절약한 길이를 구하기 위해 전체 길의 길이 - MST의 길이
			bw.write(String.valueOf(totalWeight - prim(m, n, list)) + "\n");
		}
		bw.flush();
	}

	private static int prim(int m, int n, ArrayList<Node>[] list) {
		int totalWeight = 0; // MST의 길이
		int edgeCnt = 0; // 연결된 간선 수
		boolean[] visited = new boolean[m]; // 정점 방문 여부 체크 배열
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(0, 0)); // 출발점 표시

		while (!queue.isEmpty()) {
			Node cur = queue.poll();

			if (!visited[cur.to]) {
				visited[cur.to] = true;

				totalWeight += cur.weight; // 해당 간선 길이 더하기
				if (edgeCnt++ == m - 1) // MST가 완성된 경우 break
					break;

				for (Node node : list[cur.to]) {
					if (!visited[node.to])
						queue.add(node);
				}
			}
		}
		return totalWeight;
	}

}
