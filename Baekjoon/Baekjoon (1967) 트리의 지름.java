import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int child; // 자식 노드
		int weight; // 가중치
		
		public Node(int child, int weight) {
			super();
			this.child = child;
			this.weight = weight;
		}
	}
	
	static ArrayList<Node>[] list;
	static boolean[] visited;
	static int maxWeight, maxIdx;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine()); // 노드의 개수
		list = new ArrayList[n]; // 자식의 노드 번호와 가중치를 가지고 있는 리스트
		
		for (int i = 0; i < n; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 입력 받은 간선 정보 저장(무방향 가중치 그래프)
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken()) - 1; // 부모 노드 
			int child = Integer.parseInt(st.nextToken()) - 1; // 자식 노드
			int weight = Integer.parseInt(st.nextToken()); // 가중치
			
			// 양방향 저장
			list[parent].add(new Node(child, weight));
			list[child].add(new Node(parent, weight));
		}
		
		/* 트리 지름 구하기  */
		// 1. 루트노드 기준으로 가장 멀리 떨어진 노드 탐색
		// 2. 해당 노드를 기준으로 제일 멀리 떨어진 노드를 탐색한 거리가 트리의 지름
		
		// 1번 과정
		visited = new boolean[n]; // 노드 방문체크 배열
		dfs(0, 0); 
		
		// 2번 과정
		visited = new boolean[n]; // 노드 방문체크 배열
		dfs(maxIdx, 0);
		
		bw.write(String.valueOf(maxWeight)); 
		bw.flush();
	}

	private static void dfs(int nodeIdx, int weight) {
		visited[nodeIdx] = true;
		
		if(maxWeight < weight) { // max 가중치 합 update
			maxWeight = weight;
			maxIdx = nodeIdx;
		}
		
		for(Node node : list[nodeIdx]) { // 해당 노드와 연결되어 있는 노드들 탐색
			if(!visited[node.child]) {
				dfs(node.child, weight + node.weight);
			}
		}
	}
}
