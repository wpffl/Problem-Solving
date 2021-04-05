import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 컨베이어 벨트의 길이
		int K = Integer.parseInt(st.nextToken()); // 내구도가 0인 칸이 K개일 경우 종료

		int[] conveyor = new int[N * 2]; // 컨베이어 벨트의 내구도 
		boolean[] robot = new boolean[N]; // 로봇이 있는지 표시
		st = new StringTokenizer(br.readLine(), " ");

		// 입력받은 컨베이어 벨트의 내구도 저장
		for (int i = 0; i < N * 2; i++) {
			conveyor[i] = Integer.parseInt(st.nextToken());
		}

		int res = 0; // 단계 카운트 변수
		int cnt = 0; // 내구도가 0인 칸 카운트하는 변수

		// 로봇 옮기는 과정 시작
		while (true) {
			// 단계 카운트
			++res;

			// 1. 벨트 한 칸 회전
			int tmp1 = conveyor[N * 2 - 1];
			for (int i = N * 2 - 1; i > 0; --i) {
				conveyor[i] = conveyor[i - 1];
			}
			conveyor[0] = tmp1;
			
			// 로봇도 회전
			for (int i = N - 1; i > 0; --i) {
				robot[i] = robot[i - 1];
			}
			robot[0] = false;
			robot[N - 1] = false; // 내려가는 위치에 로봇이 있으면 로봇 내림

			// 2. 로봇 움직이기
			for (int i = N - 2; i >= 0; i--) {
				// 현재 위치에 로봇이 존재하면
				if (robot[i]) {
					// 다음 칸의 내구도가 0보다 크고, 다음 칸에 로봇이 없는 경우 이동
					if (conveyor[i + 1] > 0 && !robot[i + 1]) {
						robot[i] = false;
						robot[i + 1] = true;
						if (--conveyor[i + 1] == 0)
							cnt++;
					}
				}
			}

			// 3. 올라가는 위치의 내구도가 0보다 크면 로봇 올리기
			if (conveyor[0] > 0) {
				if (--conveyor[0] == 0)
					++cnt;
				robot[0] = true;
			}

			// 4. 내구도가 0인 칸이 K개일 경우 종료
			if (cnt >= K)
				break;
		}

		System.out.println(res);
	}
}
