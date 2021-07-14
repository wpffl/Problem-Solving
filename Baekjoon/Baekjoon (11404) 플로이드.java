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

		int v = Integer.parseInt(br.readLine()); // 도시의 개수
		int e = Integer.parseInt(br.readLine()); // 간선의 개수

		int[][] distance = new int[v][v];
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				if (i == j)
					continue;

				distance[i][j] = INF;
			}
		}

		// 입력받은 간선의 정보 저장
		for (int i = 0; i < e; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			
			if(distance[start][end] != INF) // 출발지와 도착지를 연결하는 간선이 여러개일 경우 최소값 저장
				distance[start][end] = Math.min(distance[start][end], weight);
			else
				distance[start][end] = weight;
		}

		distance = floyd(v, distance);
		
		// 출력
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				if(distance[i][j] == INF)
					bw.write(0 + " ");
				else
					bw.write(distance[i][j] + " ");
			}
			bw.newLine();
		}
		bw.flush();
	}

	private static int[][] floyd(int v, int[][] distance) {
		for (int k = 0; k < v; k++) { // 경유지
			for (int i = 0; i < v; i++) { // 출발지
				for (int j = 0; j < v; j++) { // 도착지
					if(distance[i][k] + distance[k][j] < distance[i][j])
						distance[i][j] = distance[i][k] + distance[k][j];
				}
			}
		}
		
		return distance;
	}
}
