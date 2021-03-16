import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 데이터의 길이
			int start = Integer.parseInt(st.nextToken()); // 시작점
			
			// list 선언 및 생성
			LinkedList<Integer>[] list = new LinkedList[101];
			for (int i = 0; i < 101; i++) {
				list[i] = new LinkedList<>();
			}
			
			// 입력 데이터로 인접 그래프 만들기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				list[from].add(to);
			}
			
			bw.write("#" + t + " " + bfs(list, start));
			bw.newLine();
		}
		br.close();
		bw.close();
	}

	private static int bfs(LinkedList<Integer>[] list, int start) {
		int max = Integer.MIN_VALUE; // 최종 결과값(마지막에 연락받은 사람 중 가장 숫자가 큰 사람)
		int maxCnt = 0; // 방문 level를 나타내는 변수
		int[] visited = new int[101]; // 방문 체크 및 현재 level을 나타낼 배열
		
		// start 정점 값 넣기
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[start] = 1; // 방문 체크
		queue.offer(start);

		// bfs 탐색
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int size = list[cur].size();
			
			// 현재 정점과 연결된 정점을 큐에 넣기
			for (int i = 0; i < size; i++) {
				int next = list[cur].get(i);
				
				if(visited[next] == 0) {
					// 다음에 탐색할 정점 방문 체크 및 레벨 표시(현재 level + 1)
					visited[next] = visited[cur] + 1; 
					queue.offer(next);
				}
			}
			
			// maxCnt를 현재 level로 갱신
			maxCnt = visited[cur];
		}
		
		// 최대 방문 횟수를 찾아서 노드의 번호가 더 큰 값으로 업데이트
		for (int i = 1; i <= 100; i++) {
			if(visited[i] == maxCnt) {
				max = Math.max(max, i);
			}
		}
		return max;
	}
}
