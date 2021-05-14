import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int res;
	static boolean[][] map;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new boolean[5][5]; // 해당 자리에 누가 앉아 있나(0:임도연파, 1:이다솜파 )

		// 입력 받은 자리 배치도 저장
		for (int i = 0; i < 5; i++) {
			String s = br.readLine();
			for (int j = 0; j < 5; j++) {
				if (s.charAt(j) == 'S')
					map[i][j] = true;
				else
					map[i][j] = false;
			}
		}

		makeConbination(0, new int[7], 0, 0, new boolean[25]); // 25C7 조합
		bw.write(String.valueOf(res));
		bw.flush();
	}

	private static void makeConbination(int select, int[] selected, int startIdx, int cntSom, boolean[] princess) {
		if (select == 7) { // 7명 다 뽑은 경우
			if (cntSom >= 4) { // 이다솜파의 학생이 4명이상인지 확인
				if (bfs(selected, princess)) // 가로세로 인접해있는지 확인
					++res;
			}
			return;
		}

		for (int i = startIdx; i < 25; i++) {
			princess[i] = true; // 공주 표시

			selected[select] = i;
			if (map[i / 5][i % 5])
				makeConbination(select + 1, selected, i + 1, cntSom + 1, princess);
			else
				makeConbination(select + 1, selected, i + 1, cntSom, princess);

			princess[i] = false; // 공주 표시 해제
		}

	}

	/* 뽑은 7명의 학생이 가로 또는 세로로 인접해있는지 확인 */
	private static boolean bfs(int[] selected, boolean[] princess) {
		int cnt = 1;
		boolean visited[] = new boolean[25]; // 방문한 적 있는 위치인지 나타내주는 배열
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(selected[0]); // 임의의 학생 넣기

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			visited[cur] = true;
			
			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int nx = (cur / 5) + dx[d];
				int ny = (cur % 5) + dy[d];

				if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) { // 범위 체크
					if (!visited[(nx * 5) + ny]) { // 방문 체크
						if (princess[(nx * 5) + ny]) { // 인접한 애가 7공주 중 한명인지 체크
							++cnt;
							visited[(nx * 5) + ny] = true;
							queue.add((nx * 5) + ny); // 다음 탐색을 위해 큐에 넣음
						}
					}
				}
			}

		}

		return (cnt == 7) ? true : false;
	}
}
