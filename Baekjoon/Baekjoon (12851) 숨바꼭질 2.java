import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pos {
		int location;
		int time;

		public Pos(int location, int time) {
			super();
			this.location = location;
			this.time = time;
		}
	}

	static int resCnt, resTime = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치
		int K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치

		if (N > K) { // 수빈이가 있는 위치가 동생이 있는 위치보다 큰 경우 -> -1씩 뒤로 가야함(이때 방법은 1가지)
			bw.write(String.valueOf(N - K) + "\n");
			bw.write(String.valueOf(1));
		} else {
			bfs(N, K);
			bw.write(String.valueOf(resTime) + "\n");
			bw.write(String.valueOf(resCnt));
		}

		bw.flush();
	}

	private static void bfs(int N, int K) {
		boolean[] visited = new boolean[100001];
		Queue<Pos> queue = new LinkedList<>();
		
		// 출발지 표시
		queue.add(new Pos(N, 0));
		visited[N] = true;

		while (!queue.isEmpty()) {
			Pos p = queue.poll();
			
			if(p.time > resTime) // 현재시간이 최소시간보다 클 경우 pass
				continue;

			if (p.location == K) { // 동생 찾은 경우
				if (p.time < resTime) { // 현재 시간이 최소 시간인 경우
					resTime = p.time;
					resCnt = 1; 
				} else if (p.time == resTime) // 현재 시간이 최소 시간과 같은 경우
					++resCnt;
				continue;
			}

			visited[p.location] = true; // 방문 체크(늦게 해줘야 함! 모든 경우 체크를 위해)
			
			// 3가지 이동 방법으로 이동할 수 있으면 이동
			if (isValid(p.location - 1) && !visited[p.location - 1])
				queue.add(new Pos(p.location - 1, p.time + 1));
			if (isValid(p.location + 1) && !visited[p.location + 1])
				queue.add(new Pos(p.location + 1, p.time + 1));
			if (isValid(p.location * 2) && !visited[p.location * 2])
				queue.add(new Pos(p.location * 2, p.time + 1));
		}
	}

	/* 이동할 수 있는 범위인지 확인해주는 함수 */
	private static boolean isValid(int location) {
		if (location >= 0 && location <= 100000)
			return true;
		else
			return false;
	}

}
