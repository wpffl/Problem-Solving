/* 위상 정렬 (시간복잡도: O(V + E) -> 정점의 개수 + 간선의 개수) */
// 사이클이 없는 방향그래프인 경우만 가능! 답이 여러개일 수 있다.
// 스택과 큐를 사용해서 구현할 수 있지만 보통 큐를 많이 사용
// 1. 진입차수가 0인 정점을 큐에 삽입(진입차수: 선행적으로 수행되어야할 작업의 수)
// 2. 큐에서 원소를 꺼내 연결된 모든 간선을 제거
// 3. 간선 제거 이후에 진입차수가 0인 정점을 큐에 삽입
// 4. 큐가 빌 때까지 2 ~ 3 과정 반복. 
// 모든 원소를 방문하기 전에 큐가 빈다면 사이클이 존재함을 뜻함. 모든 원소를 방문했다면 큐에서 꺼낸 순서가 위상 정렬의 결과.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine()); // 수행할 작업 개수
		int[] indegree = new int[N]; // 정점별 진입차수
		int[] time = new int[N]; // 작업별 작업 소요 시간
		ArrayList<Integer>[] list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();

			st = new StringTokenizer(br.readLine(), " ");
			time[i] = Integer.parseInt(st.nextToken()); // 작업하는데 걸리는 시간
			int cnt = Integer.parseInt(st.nextToken()); // 선행 작업의 개수

			for (int j = 0; j < cnt; j++) {
				int preJob = Integer.parseInt(st.nextToken()) - 1;
				list[preJob].add(i); // 선행 작업 뒤에 현재 수행할 작업 삽입

				++indegree[i]; // 해당 작업 차수 1 증가
			}
		}
		bw.write(String.valueOf(topologicalSort(indegree, time, list, N)));
		bw.flush();
	}

	/* 위상정렬 함수 */
	private static int topologicalSort(int[] indegree, int[] time, ArrayList<Integer>[] list, int N) {
		Queue<Integer> queue = new LinkedList<>();
		
		int[] result = new int[N]; // 각각의 작업을 수행하는데 걸리는 시간
		for (int i = 0; i < N; i++) {
			result[i] = time[i];
			
			// 진입차수가 0인 작업을 큐에 넣음
			if(indegree[i] == 0)
				queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int next : list[cur]) {
                // 이전의 작업이 끝나지 않으면 다음 작업을 수행할 수 없으므로 max 구하기
				result[next] = Math.max(result[next], result[cur] + time[next]);
				
				// 해당 노드가 가리키는 노드의 indegree 를 1 감소. indegree가 0이면 큐에 넣기
				if(--indegree[next] == 0) 
					queue.offer(next);
			}
		}
		
		int res = 0;
		for (int i = 0; i < N; i++) {
			res = Math.max(res, result[i]);
		}
		return res;
	}
}
