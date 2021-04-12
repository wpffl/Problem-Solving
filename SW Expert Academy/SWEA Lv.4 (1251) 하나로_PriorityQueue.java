import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 최소 신장 트리(MST)
// 1. Prim 알고리즘 (정점 중심) - 인접 행렬, 인접 리스트
// 2. Kruskal 알고리즘 (간선 중심) - 간선 리스트

// O(V^2 * logV)
public class Solution {
	static class Vertex implements Comparable<Vertex>{
		int no; // 정점 번호
		long cost; // 간선 비용
		
		public Vertex(int no, long cost) {
			super();
			this.no = no;
			this.cost = cost;
		}

		@Override
		public int compareTo(Vertex o) {
			return Long.compare(this.cost, o.cost);
		}
	}
	
	static int N;
	static long[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // 섬의 개수
			map = new long[N][N]; // 인접 행렬(안에 데이터는 해저터널 길이의 제곱 -> 가중치)

			// 입력받은 섬의 x 좌표 저장
			int[] x = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}

			// 입력받은 섬의 y 좌표 저장
			int[] y = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			
			// 인접행렬 구성
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = map[j][i] = getDistance(x[i], x[j], y[i], y[j]);
				}
			}
			
			double E = Double.parseDouble(br.readLine()); // 환경 세율
			// 결과: 해저터널 길이의 제곱 * 환경 세율
			bw.write("#" + t + " " + Math.round(makeMST() * E)); 
			bw.newLine();
		}
		bw.flush();
	}

	// 두 정점 사이의 거리 계산해주는 함수
	static long getDistance(int x1, int x2, int y1, int y2) {
		return (long)(Math.pow(x1 - x2, 2) + Math.pow(y1- y2, 2));
	}
	
	// 최소 신장 트리(Prim 알고리즘 사용)
	private static long makeMST() {
		long[] minEdge = new long[N];
		boolean[] visited = new boolean[N];
		PriorityQueue<Vertex> pqueue = new PriorityQueue<>();
		
		/* 초기 세팅 */
		Arrays.fill(minEdge, Long.MAX_VALUE); // 최대값으로 채워둠
		minEdge[0] = 0; // 임의의 정점을 시작점으로 만듦
		long result = 0; // 최소 신장 트리 비용
		int cnt = 0; // 정점의 개수 
		pqueue.offer(new Vertex(0, minEdge[0]));
		
		while(true) {
			// 신장 트리에 포함되지 않은 정점 중 최소간선비용의 정점 선택
			Vertex minVertex = pqueue.poll();
			
			if(visited[minVertex.no]) // 이미 방문한 노드면 제외
				continue;
			
			// 신장 트리에 포함 시킴
			visited[minVertex.no] = true;
			result += minVertex.cost;
			
			if(++cnt == N)
				break;
			
			// 신장 트리에 연결되지 않은 정점 중에 가중치가 최소인 것으로 minEdge 업데이트
			for (int i = 0; i < N; i++) {
				if(!visited[i] && map[minVertex.no][i] < minEdge[i]) {
					minEdge[i] = map[minVertex.no][i];
					pqueue.offer(new Vertex(i, minEdge[i])); // queue에서 지우지 않고 계속 넣는 방법
				}
			}
		}
		
		return result;
	}
}
