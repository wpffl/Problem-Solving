import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static class Status {
		int screen; // 화면에 출력된 이모티콘의 개수
		int clipboard; // 클립보드에 있는 이모티콘의 개수
		int time; // 현재까지 걸린 시간
		
		public Status(int screen, int clipboard, int time) {
			super();
			this.screen = screen;
			this.clipboard = clipboard;
			this.time = time;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int S = Integer.parseInt(br.readLine()); // 화면에 만들 이모티콘의 개수
		boolean[][] visited = new boolean[S * 2 + 1][S * 2 + 1]; // visited[화면에 있는 이모티콘의 개수][클립보드에 있는 이모티콘의 개수]
		
		// BFS ------------------------------------------------------------------------------------------------------
		Queue<Status> queue = new LinkedList<>();
		queue.offer(new Status(1, 0, 0));
		
		while(!queue.isEmpty()) {
			Status current = queue.poll();
			
			if(current.screen == S) { // 현재 화면에 출력된 이모티콘의 개수가 S와 같을 경우
				System.out.println(current.time);
				break;
			}
			
			if(!visited[current.screen][current.clipboard]) {
				visited[current.screen][current.clipboard] = true; // 방문 체크
				
				// 1. 화면에 있는 이모티콘의 개수를 클립보드로 복사 (1초 소요)
				queue.offer(new Status(current.screen, current.screen, current.time + 1));
				
				// 2. 클립보드에 있는 이모티콘을 화면에 붙여넣기(1초 소요)
				if(current.clipboard != 0 && current.screen + current.clipboard <= S) {
					queue.offer(new Status(current.screen + current.clipboard, current.clipboard, current.time + 1));
				}
				
				// 3. 화면에 있는 이모티콘의 개수 - 1 (1초 소요)
				if(current.screen != 0) {
					queue.offer(new Status(current.screen - 1, current.clipboard, current.time + 1));
				}
			}
		}
	}
}
