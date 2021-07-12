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
		
		int V = Integer.parseInt(st.nextToken()); // 정점 수
		int E = Integer.parseInt(st.nextToken()); // 간선 수
		
		int[][] distance = new int[V][V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if(i != j)
					distance[i][j] = INF;
			}
		}
		
		// 입력 받은 간선 정보 저장
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			
			distance[from][to] = weight;
		}
		
		bw.write(String.valueOf(floyd(V, distance)));
		bw.flush();
	}
	
	private static int floyd(int V, int[][] distance) {
		for (int k = 0; k < V; k++) { // 경유지
			for (int i = 0; i < V; i++) { // 출발지
				for (int j = 0; j < V; j++) { // 도착지
					if(distance[i][k] + distance[k][j] < distance[i][j])
						distance[i][j] = distance[i][k] + distance[k][j];
				}
			}
		}
		
		// 제일 작은 사이클 구하기
		int minDistance = Integer.MAX_VALUE;
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if(i == j) // *** 주의. 안써주면 답이 0으로 나옴!
					continue;
				
				// 서로에게 가는 경로가 있으면 사이클 존재
				if(distance[i][j] != INF && distance[j][i] != INF) {
					minDistance = Math.min(minDistance, distance[i][j] + distance[j][i]);
				}
			}
		}
		
		return (minDistance == Integer.MAX_VALUE) ? -1 : minDistance;
	}
}
