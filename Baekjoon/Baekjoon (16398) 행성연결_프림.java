import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()); // 행성의 수
		int[][] map = new int[N][N];
		 
		// 입력받은 행성간의 플로우 관리 비용 저장
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bw.write(String.valueOf(prim(N, map)));
		bw.flush();
	}

	private static long prim(int N, int[][] map) {
		long totalWeight = 0; // MST 길이
		boolean[] visited = new boolean[N]; // 신장트리에 연결된 정점인지 여부
		
		/* init */
		long[] minWeight = new long[N]; // 해당 정점 기준 가장 짧은 길이
		Arrays.fill(minWeight, Long.MAX_VALUE); // 최대값으로 초기화
		minWeight[0] = 0; // 시작점 표시
		
		for (int i = 0; i < N; i++) {
			int minVertex = 0; 
			long minEdge = Long.MAX_VALUE;
			
			// 신장 트리에 연결되지 않은 정점 중 가장 가중치가 최소인 정점 찾기
			for (int j = 0; j < N; j++) {
				if(!visited[j] && minWeight[j] < minEdge) {
					minVertex = j;
					minEdge = minWeight[j];
				}
			}
			
			totalWeight += minEdge;
			visited[minVertex] = true; // 신장트리에 포함시킴
			
			// 현재 minVertex와 연결된 간선들의 mindWeight[] 업데이트 -> 가장 길이가 작은 간선을 뽑기 위한 작업
			for (int j = 0; j < N; j++) {
				if(!visited[j] && map[minVertex][j] != 0 && map[minVertex][j] < minWeight[j]) 
					minWeight[j] = map[minVertex][j];
			}
			
		}
	
		return totalWeight;
	}

}
