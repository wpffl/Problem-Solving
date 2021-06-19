import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, start, end;
	static LinkedList<Node>[] list;

	static class Node {
		int to; // 도착지
		int weight; // 중량 제한

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 섬의 개수
		M = Integer.parseInt(st.nextToken()); // 다리의 개수

		list = new LinkedList[N]; // 다리의 정보를 가지고 있는 양방향 리스트
		for (int i = 0; i < N; i++) {
			list[i] = new LinkedList<>();
		}

		int maxWeight = 0; // 중량 제한의 최댓값

		// 입력받은 다리의 정보 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken()) - 1; // 출발지
			int to = Integer.parseInt(st.nextToken()) - 1; // 도착지
			int weight = Integer.parseInt(st.nextToken()); // 중량 제한

			list[from].add(new Node(to, weight));
			list[to].add(new Node(from, weight));

			maxWeight = Math.max(maxWeight, weight);
		}

		st = new StringTokenizer(br.readLine(), " ");
		start = Integer.parseInt(st.nextToken()) - 1; // 출발지
		end = Integer.parseInt(st.nextToken()) - 1; // 도착지

		// 출력
		bw.write(String.valueOf(binarySearch(0, maxWeight)));
		bw.flush();
	}

	private static int binarySearch(int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;
			if (bfs(start, end, mid)) { // 도착지에 도착한 경우 -> mid 이후 검사
				left = mid + 1;
			} else { // 도착지에 도착하지 못한 경우 -> mid 이전 검사
				right = mid - 1;
			}
		}

		return right; // 최종적으로 right에 최대 중량 제한이 들어감
	}

	private static boolean bfs(int start, int end, int mid) {
		boolean[] visited = new boolean[N];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();

			if (current == end) { // 도착지에 도착한 경우
				return true;
			}

			for (Node n : list[current]) {
				if (!visited[n.to] && mid <= n.weight) { // 방문하지 않았고, mid보다 중량 제한이 큰 경우 계속 탐색
					visited[n.to] = true;
					queue.add(n.to);
				}
			}
		}
		return false; // 도착지에 도착하지 못한 경우
	}
}
