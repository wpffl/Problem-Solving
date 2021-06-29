import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int V, E;
	static boolean res;
	static ArrayList<Integer>[] list;
	static int[] colors;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken()); // 정점의 수
			E = Integer.parseInt(st.nextToken()); // 간선의 수

			res = true; // 결과 변수 초기화
			colors = new int[V]; // 해당 정점이 둘 중 무슨 그룹인지 나타내는 배열(0:방문 X, 1:RED, -1:BLUE)
			list = new ArrayList[V]; // 간선 정보를 가지고 있는 리스트
			for (int i = 0; i < V; i++) {
				list[i] = new ArrayList<>();
			}

			// 입력받은 간선의 정보 저장
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken()) - 1; // 출발지
				int to = Integer.parseInt(st.nextToken()) - 1; // 도착지

				// 양방향 그래프
				list[from].add(to);
				list[to].add(from);
			}

			for (int i = 0; i < V; i++) { // 모든 정점에 대해 탐색
				if (colors[i] == 0) { // 방문하지 않은 정점인 경우
					if (!dfs(i, 1)) // 이분그래프가 아닌 경우 중지
						break;
				}
			}
			
			bw.write((res ? "YES" : "NO") + "\n");
		}
		bw.flush();
	}

	// [Return] true: 이분그래프, false: 이분그래프가 아니다
	// [color] 1: RED, -1: BLUE
	private static boolean dfs(int v, int color) {
		colors[v] = color; // 현재 정점 색칠
		
		for(int next : list[v]) { // 현재 정점과 인접한 모든 정점 탐색
			if(colors[next] == color) { // 인접 정점의 색이 같은 경우 -> 이분 그래프 X
				res = false;
				return false;
			} else if(colors[next] == 0) { // 인접정점의 색이 다르고, 방문하지 않은 정점인 경우 -> 현재 정점과 다른 색을 칠함
				if(!dfs(next, -color)) // 이분 그래프가 아닌 경우
					return false;
			}
		}
		return true;
	}
}
