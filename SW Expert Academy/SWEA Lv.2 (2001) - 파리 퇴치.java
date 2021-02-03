import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 총 테스트케이스 
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 배열 크기
			int M = Integer.parseInt(st.nextToken()); // 파리채 크기
			int[][] arr = new int[N][N];
			int sum = 0, max = 0;
			
			for (int i = 0; i < N; i++) {
				st =  new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sum = 0;
					for (int x = 0; x < M; x++) {
						for (int y = 0; y < M; y++) {
							if(i+x < N && j+y < N) {
								sum += arr[i+x][j+y];
							}
						}
					}
					if(sum > max)
						max = sum;
					
				}
				
			}
		System.out.println("#" + t + " " + max);
		}			
	}
}
