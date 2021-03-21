import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, V;
	static LinkedList<Integer>[] list;
	static BufferedWriter bw;
	static boolean[] visited2; // DFS 탐색을 위한 방문 체크 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		M = Integer.parseInt(st.nextToken()); // 간선의 개수
		V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호

		// 정점 개수만큼 리스트 생성
		list = new LinkedList[N + 1];
		
		for (int i = 0; i < N + 1; i++) {
			list[i] = new LinkedList<Integer>();
		}
		
		visited2 = new boolean[N + 1];

		// 입력 받은 간선으로 인접리스트 생성
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			list[from].add(to);
			list[to].add(from);
		}
		
		// 방문할 수 있는 정점이 여러개인 경우 정점 번호가 작은 것을 먼저 탐색하도록 정렬
		for (int i = 0; i < N; i++) {
			list[i].sort(new Comparator<Integer>() {
				
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1 - o2;
				}
				
			});
		}

		dfs(V);
		bfs();

		br.close();
		bw.close();
	}

	private static void bfs() throws IOException {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];

		bw.newLine(); 
		
		// 처음 탐색할 정점
		visited[V] = true;
		queue.offer(V);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			int size = list[cur].size();
			
			bw.write(cur + " ");
			
			// 현재 level에서 연결된 정점들을 큐에 넣어 다음 탐색
			for (int i = 0; i < size; i++) {
				int next = list[cur].get(i);
				
				if(!visited[next]) {
					visited[next] = true;
					queue.offer(next);
				}
			}
		}
	}

	private static void dfs(int current) throws IOException {
		visited2[current] = true;
		bw.write(current + " ");
		
		int size = list[current].size();
		for (int i = 0; i < size; i++) {
			int next = list[current].get(i);
			
			if(!visited2[next]) {
				dfs(next);
			}
		}
	}
}
