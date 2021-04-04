import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static final int INF = 9999999;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 사람의 수
			int[][] distance = new int[N][N]; // 사람 네트워크의 인접 행렬

			// 인접 행렬 저장
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					distance[i][j] = Integer.parseInt(st.nextToken());

					if (i != j && distance[i][j] == 0) { // 자기자신으로의 인접 정보가 아니고 인접해있지 않다면 INF로 채우기
						distance[i][j] = INF;
					}
				}
			}

			for (int i = 0; i < N; i++) { // 경유지
				for (int start = 0; start < N; start++) { // 출발지
					if (i == start) // 경유지와 출발지가 같은 경우 pass
						continue;
					for (int end = 0; end < N; end++) { // 도착지
						if (i == end || start == end) // 경유지와 도착지가 같거나, 출발지와 도착지가 같은 경우 pass
							continue;
						 if (distance[start][i] != INF && distance[i][end] != INF // 출발지-경유지, 경유지-도착지가 연결되지 않은 경우 제외
	                                && distance[start][i] + distance[i][end] < distance[start][end] ) { // 출발지-경유지-도착지가 출발지-경유지보다 거리가 작은 경우
							 distance[start][end] = Math.min(distance[start][end], distance[start][i] + distance[i][end]);
						 }
					}
				}
			}

			// 사람 네트워크에서 distance 합이 가장 작은 사용자 구하기
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += distance[i][j];
				}

				min = Math.min(min, sum);
			}
			sb.append("#" + t + " " + min + "\n");
		}
		System.out.println(sb);
	}
}
