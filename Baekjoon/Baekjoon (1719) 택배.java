import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken()); // 집하장 개수
		int m = Integer.parseInt(st.nextToken()); // 집하장 경로 수

		int[][] path = new int[n + 1][n + 1]; // 각 집하장을 가기 위한 첫 구간을 기록한 배열
		int[][] distance = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j)
					continue;

				distance[i][j] = INF;
			}
		}

		// 입력받은 집하장 경로 정보 저장
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			// 양방향
			distance[from][to] = distance[to][from] = weight;

			path[from][to] = to; // from에서 to로 가려면 to가 첫 구간
			path[to][from] = from; // to에서 from으로 가려면 from이 첫 구간
		}

		path = floyd(n, distance, path);

		// 출력
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j)
					bw.write("- ");
				else
					bw.write(path[i][j] + " ");
			}
			bw.newLine();
		}
		bw.flush();
	}

	private static int[][] floyd(int n, int[][] distance, int[][] path) {
		for (int k = 1; k <= n; k++) { // 경유지
			for (int i = 1; i <= n; i++) { // 출발지
				for (int j = 1; j <= n; j++) { // 도착지
					if (distance[i][k] + distance[k][j] < distance[i][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
						path[i][j] = path[i][k]; // i에서 j까지 까는데 k가 첫 구간
					}
				}
			}
		}

		return path;
	}

}
