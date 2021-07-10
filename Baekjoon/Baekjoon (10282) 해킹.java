import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Computer implements Comparable<Computer> {
		int to;
		int time;

		public Computer(int to, int time) {
			super();
			this.to = to;
			this.time = time;
		}

		@Override
		public int compareTo(Computer o) {
			return this.time - o.time;
		}
	}

	static int totalCnt, totalTime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
			int d = Integer.parseInt(st.nextToken()); // 의존성 개수
			int c = Integer.parseInt(st.nextToken()) - 1; // 해킹당한 컴퓨터 번호

			int[] distance = new int[n];
			ArrayList<Computer>[] list = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				list[i] = new ArrayList<>();
			}

			// 입력받은 컴퓨터간 의존 정보 저장
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());

				int to = Integer.parseInt(st.nextToken()) - 1;
				int from = Integer.parseInt(st.nextToken()) - 1;
				int time = Integer.parseInt(st.nextToken());

				list[from].add(new Computer(to, time));
			}

            totalTime = 0;
			totalCnt = 1; // 최초 1대의 컴퓨터는 이미 감염됨
			dijkstra(list, c, distance);
			
			bw.write(totalCnt + " " + totalTime + "\n");
		}
		bw.flush();
	}

	private static void dijkstra(ArrayList<Computer>[] list, int C, int[] distance) {
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<Computer> pqueue = new PriorityQueue<>();

		// 출발지 표시
		pqueue.add(new Computer(C, 0));
		distance[C] = 0;

		while (!pqueue.isEmpty()) {
			Computer cur = pqueue.poll();
			
			if(distance[cur.to] < cur.time) // 내 시간이 계산된 시간보다 크면 갱신 필요 X
				continue;
			
			for(Computer c : list[cur.to]) {
				// c로 가는 것 중 cur을 거쳐 c로 가는게 더 최단인 경우
				if(distance[cur.to] + c.time < distance[c.to]) {
					if(distance[c.to] == Integer.MAX_VALUE) // 방문한적 없을 경우, 감염된 컴퓨터 수 1 증가
						++totalCnt;
					
					distance[c.to] = distance[cur.to] + c.time; 
					pqueue.add(new Computer(c.to, distance[c.to]));
				}
			}
		}
		
		// distance[] 에서 Integer.MAX_VALUE를 제외한 최댓값이 감염되는 최소 시간
		for (int time : distance) {
			if(time != Integer.MAX_VALUE) { // 방문하지 않은 경우를 제외하고
				totalTime = Math.max(totalTime, time);
			}
		}
	}
}
