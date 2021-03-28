import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine()); // 편의점의 개수
			Node[] location = new Node[N + 1]; // 편의점 좌표와 도착 좌표를 저장할 배열
			boolean[] visited = new boolean[N + 1]; // 방문 체크 배열
			Queue<Node> queue = new LinkedList<>(); // 갈 수 있는 곳의 좌표를 저장하는 큐
			boolean flag = false; // 성공, 실패 여부를 판단하는 flag

			// 시작 좌표
			st = new StringTokenizer(br.readLine(), " ");
			Node start = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			// 편의점 좌표 와 펜타포트 락 페스티벌 좌표
			for (int i = 0; i < N + 1; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				location[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			queue.add(start); // 시작 좌표부터 출발
			Node end = location[N]; // 도착 좌표 저장
			
			while(!queue.isEmpty()) {
				Node next = queue.poll();
				
				// 다음 장소가 도착지이면 반복문 종료
				if(next.equals(end)) {
					flag = true;
					break;
				}
				
				// 거리가 1000 이하인 위치만 Queue에 저장
				for (int i = 0; i < N + 1; i++) {
					int distance = Math.abs(next.x - location[i].x) + Math.abs(next.y - location[i].y);
					if(!visited[i] && distance <= 1000) {
						visited[i] = true;
						queue.offer(location[i]);
					}
				}
			}
			
			if(flag) 
				bw.write("happy");
			else
				bw.write("sad");
			bw.newLine();
		}
		br.close();
		bw.close();
	}
}
