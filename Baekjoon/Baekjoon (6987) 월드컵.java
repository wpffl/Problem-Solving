import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] team1 = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 }; // team1의 index 순서
	static int[] team2 = { 1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5 }; // team2의 index 순서
	static int[][] score;
	static boolean flag; // 경기 가능 여부에 대해 판단할 변수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			score = new int[6][3]; // 각 나라의 승, 무, 패 횟수를 기록할 배열

			// 입력 받은 점수 저장(승, 무, 패)
			int x = 0;
			int y = 0;
			int sum = 0; // 승, 무, 패의 합을 저장할 변수
			flag = false;
			while (st.hasMoreTokens()) {
				if (y == 3) {
					x++;
					y = 0;
				}
				sum += score[x][y++] = Integer.parseInt(st.nextToken());
			}
			
			// 승, 무, 패의 합은 30이어야 함.
			if(sum != 30)
				flag = false;
			else
				sol(0);

			if (flag)
				bw.write(1 + " ");
			else
				bw.write(0 + " ");
		}

		br.close();
		bw.close();
	}

	private static void sol(int cnt) {
        if(flag)
			return;
        
		if (cnt == 15) { // 마지막 게임까지 온 경우, 성공
			flag = true;
			return;
		}

		int t1 = team1[cnt];
		int t2 = team2[cnt];

		// team1의 승리
		if (score[t1][0] > 0 && score[t2][2] > 0) {
			score[t1][0]--; // team1의 승리 카운트 감소
			score[t2][2]--; // team2의 패배 카운트 감소
			sol(cnt + 1);
			// 다음 탐색을 위해 되돌리기
			score[t1][0]++;
			score[t2][2]++;
		}

		// team2의 승리
		if (score[t1][2] > 0 && score[t2][0] > 0) {
			score[t1][2]--; // team1의 패배 카운트 감소
			score[t2][0]--; // team2의 승리 카운트 감소
			sol(cnt + 1);
			// 다음 탐색을 위해 되돌리기
			score[t1][2]++;
			score[t2][0]++;
		}

		// 무승부
		if (score[t1][1] > 0 && score[t2][1] > 0) {
			score[t1][1]--; // team1의 무승부 카운트 감소
			score[t2][1]--; // team2의 무승부 카운트 감소
			sol(cnt + 1);
			// 다음 탐색을 위해 되돌리기
			score[t1][1]++;
			score[t2][1]++;
		}
	}
}
