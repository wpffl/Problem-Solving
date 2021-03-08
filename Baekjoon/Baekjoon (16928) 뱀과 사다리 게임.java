import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location implements Comparator<Location> {
	int x; // 도착한 위치
	int cnt; // 주사위 굴린 횟수

	public Location(int x, int cnt) {
		super();
		this.x = x;
		this.cnt = cnt;
	}

	@Override
	public int compare(Location o1, Location o2) {
		return o1.x - o2.x;
	}
}

public class Main {
	static int N, M;
	static Queue<Location> queue = new LinkedList();
	static int[] map = new int[101];
	static boolean[] visited = new boolean[101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 사다리의 수
		M = Integer.parseInt(st.nextToken()); // 뱀의 수
		int res; // 결과 변수
		
		/* map 기본 세팅 */
		for (int i = 1; i <= 100; i++) {
			map[i] = i;
		}

		/* 사다리 세팅 */
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
	
			map[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}

		/* 뱀 세팅 */
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			map[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		// 현재 1번 위치에 있는 거 삽입
		queue.offer(new Location(1, 0));
		
		res = bfs();
		
		System.out.println(res);
		
	}

	private static int bfs() {
		while(!queue.isEmpty()) {
			Location loc = queue.poll();
			
			if(loc.x == 100) 
				return loc.cnt;
			
			for (int i = 1; i <= 6; i++) {
				int nx = loc.x + i;
				if(nx <= 100) {
					if(!visited[nx]) {
						visited[nx] = true;
						queue.offer(new Location(map[nx], loc.cnt + 1));
					}
				}
			}
		}
		return -1;
	}

}
