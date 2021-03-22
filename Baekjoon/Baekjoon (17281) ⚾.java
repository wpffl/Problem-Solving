import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, score, max = Integer.MIN_VALUE;
	static int startIdx; // 이닝 시작 시 처음 출전하는 선수 index
	static int[][] arr;
	static boolean[] ground;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 이닝 수
		arr = new int[N][9];

		ground = new boolean[3]; // 1루, 2루, 3루에 선수들이 있는지 체크해주는 배열

		// 각 이닝에서 얻는 결과 저장
		for (int i = 0; i < N; i++) {
			// 9명의 선수가 얻는 결과
			// 0:아웃, 1:안타, 2:2루타, 3:3루타, 4:홈런
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 출전 순서 조합해서 가장 많은 득점을 하는 타순 찾기
		makePermutation(0, new int[9], new boolean[9]);

		System.out.println(max);
	}

	/* 출전 순서 순열 만드는 함수 */
	private static void makePermutation(int select, int[] selected, boolean[] visited) {
		if (select == 3) {
			selected[3] = 0; // 1번 선수를 4번 주자로 고정
			makePermutation(select + 1, selected, visited);
			return;
		}

		if (select == 9) {
			score = 0;
			// 같은 타순으로 이닝 진행
			for (int i = 0; i < N; i++) {
				play(selected, i);
			}
			max = Math.max(max, score);
			startIdx = 0; // 타순 초기화
			return;
		}

		// 2번 ~ 9번 타자 순서 조합
		for (int i = 1; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[select] = i;
				makePermutation(select + 1, selected, visited);
				visited[i] = false;
			}
		}

	}

	/* 경기 진행하여 점수 계산하는 함수 */
	private static void play(int[] selected, int nowInning) {
		int outCnt = 0;
		ground[0] = ground[1] = ground[2] = false; // 1루, 2루, 3루 초기화
		while (outCnt != 3) {
			// 0:아웃, 1:안타, 2:2루타, 3:3루타, 4:홈런
			switch (arr[nowInning][selected[startIdx]]) {
			case 0: // 아웃인 경우
				outCnt++;
				break;
			case 1:
				if (ground[2]) // 3루에 있던 선수 득점
					score++;

				// 2루 -> 3루, 1루 -> 2루
				ground[2] = ground[1];
				ground[1] = ground[0];

				ground[0] = true; // 타자가 1루로 도착

				break;

			case 2:
				if (ground[1]) // 2루에 있던 선수 득점
					score++;
				if (ground[2]) // 3루에 있던 선수 득점
					score++;

				// 1루 -> 3루
				ground[2] = ground[0];
				ground[0] = false; // 1루 사람 없음 표시

				ground[1] = true; // 타자가 2루로 도착

				break;

			case 3:
				if (ground[0]) // 1루에 있던 선수 득점
					score++;
				if (ground[1]) // 2루에 있던 선수 득점
					score++;
				if (ground[2]) // 3루에 있던 선수 득점
					score++;

				// 1루, 2루 사람 없음 표시. 3루 타자 도착
				ground[0] = ground[1] = false;
				ground[2] = true;

				break;

			case 4:
				if (ground[0]) // 1루에 있던 선수 득점
					score++;
				if (ground[1]) // 2루에 있던 선수 득점
					score++;
				if (ground[2]) // 3루에 있던 선수 득점
					score++;

				score++; // 타자 본인 홈런 득점
				// 1루, 2루, 3루 사람 없음 표시.
				ground[0] = ground[1] = ground[2] = false;

				break;
			}

      			// 9번 선수 까지 다 돌았으면 1번 선수로 초기화
			if (++startIdx == 9) {
				startIdx = 0;
			}
		}
	}
}
