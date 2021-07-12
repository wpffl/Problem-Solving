import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int leaderScore = Integer.MAX_VALUE, leaderCnt, INF = 987654321;
	static int[] score;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine()); // 회원의 수

		int[][] distance = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i != j) // ***중요*** 자기자신은 제외
					distance[i][j] = INF;
			}
		}

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			if (a == -2 && b == -2) // 입력의 끝인 경우
				break;

			// a와 b는 친구다(양방향)
			distance[a][b] = distance[b][a] = 1;
		}

		floyd(n, distance);
		
		bw.write(leaderScore + " " + leaderCnt + "\n");
		bw.write(sb.toString());
		bw.flush();
	}

	private static void floyd(int n, int[][] distance) {
		// 1. 모든 정점 사이의 최단거리를 구하기
		for (int k = 0; k < n; k++) { // 경유지
			for (int i = 0; i < n; i++) { // 출발지
				for (int j = 0; j < n; j++) { // 도착지
					if(distance[i][k] + distance[k][j] < distance[i][j])
						distance[i][j] = distance[i][k] + distance[k][j];
				}
			}
		}
		
		// 2. 특정 정점에서 다른 정점으로의 최단거리 중 가장 큰 값 구하기 -> 친구 점수
		score = new int[n]; // 각 인원별 친구 점수 목록 배열
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(distance[i][j] != INF)
					score[i] = Math.max(score[i], distance[i][j]);
			}
			
			leaderScore = Math.min(leaderScore, score[i]);
		}
		
		// 3. 친구 점수 목록 배열 중 가장 작은 값 찾고, 리더가 될 수 있는 인원 몇명인지 찾기
		for (int i = 0; i < n; i++) {
			if(leaderScore == score[i]) { // 리더가 될 수 있는 경우
				++leaderCnt;
				sb.append((i + 1) + " ");
			}
		}
		
		return;
	}
}
