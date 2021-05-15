import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 가로 세로 길이
		M = Integer.parseInt(st.nextToken()); // 애벌레가 자라는 일 수
		
		map = new int[N][N]; // 애벌레 크기를 저장할 배열
		// 초기 크기: 모두 1로 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 1);
		}
		
		// 제일 왼쪽 열, 제일 위쪽 열만 애벌레 키우기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int[] cnt = new int[3]; 
			
			cnt[0] = Integer.parseInt(st.nextToken()); // 크키가 커지는 정도가 0인 애벌레의 개수
			cnt[1] = Integer.parseInt(st.nextToken()); // 크키가 커지는 정도가 1인 애벌레의 개수
			cnt[2] = Integer.parseInt(st.nextToken()); // 크키가 커지는 정도가 2인 애벌레의 개수
			
			sol(cnt);
		}
		
		// M일이 지난 후, 나머지 애벌레 키우기
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				// 무조건 내 윗 행의 원소가 max 값
				map[i][j] = map[i-1][j];
			}
		}
		
		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bw.write(map[i][j] + " ");
			}
			bw.write("\n");
		}
		bw.flush();
	}
	
	private static void sol(int[] cnt) {
		
		// 제일 왼쪽 열 애벌레 키우기
		for (int i = N-1; i >= 0; i--) {
			if(cnt[0] != 0) // 0만큼 크기 키우기
				--cnt[0];
			else if(cnt[1] != 0) { // 1만큼 크기 키우기
				--cnt[1];
				++map[i][0];
			} else { // 2만큼 크기 키우기
				--cnt[2];
				++map[i][0];
				++map[i][0];
			}
		}
		
		// 제일 위쪽 행 애벌레 키우기(map[0][0]은 위에서 계산했으므로 skip)
		for (int i = 1; i < N; i++) {
			if(cnt[0] != 0) // 0만큼 크기 키우기
				--cnt[0];
			else if(cnt[1] != 0) { // 1만큼 크기 키우기
				--cnt[1];
				++map[0][i];
			} else { // 2만큼 크기 키우기
				--cnt[2];
				++map[0][i];
				++map[0][i];
			}
		}
	}

}
