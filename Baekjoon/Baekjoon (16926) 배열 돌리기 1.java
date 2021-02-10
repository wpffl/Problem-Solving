import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R;
	static int[][] arr;
	static int[] dx = {0, 1, 0, -1}; // 방향 배열(우하좌상)
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 배열 크기 N
		M = Integer.parseInt(st.nextToken()); // 배열 크기 M
		arr = new int[N][M];
		
		R = Integer.parseInt(st.nextToken()); //회전의 수
		
		// 회전 전 초기 배열
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = Math.min(N, M)/2; // 1회전 시 돌려야하는 사각형 갯수
		for (int i = 0; i < R; i++) { // 회전 수 만큼 반복 호출
			rotation(cnt);
		}
		
		// 배열 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
	// 반시계 방향으로 배열 회전 하는 함수
	public static void rotation(int cnt) {
		for (int i = 0; i < cnt; i++) {
			int dir = 0; // 방향 변수
			int x = i;
			int y = i;
			int tmp = arr[i][i];
			
			while(dir < 4) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if(nx >= i && nx < N-i && ny >= i && ny < M-i) { // 범위 체크
					arr[x][y] = arr[nx][ny];
					
					x = nx;
					y = ny;
				} else { // 범위 벗어나면 방향 바꿔주기
					dir++;
				}
			}
			arr[i+1][i] = tmp; // 마지막 값은 arr[i][i]값 대입
		}
	}
	
}
