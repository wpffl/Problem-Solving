import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int M, bcCnt;
	static int[] pathA, pathB, playerA, playerB;
	static int[][] bc;
	static int[] dx = { 0, 0, 1, 0, -1 }; // 상우하좌(x축, y축을 기준으로)
	static int[] dy = { 0, -1, 0, 1, 0 };
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		playerA = new int[2]; // playerA의 위치
		playerB = new int[2]; // playerB의 위치

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			M = Integer.parseInt(st.nextToken()); // 총 이동 시간
			bcCnt = Integer.parseInt(st.nextToken()); // 배터리의 개수

			// playerA와 playerB의 초기 위치 세팅
			playerA[0] = playerA[1] = 1;
			playerB[0] = playerB[1] = 10;

			pathA = new int[M + 1]; // playerA의 궤적
			pathB = new int[M + 1]; // playerB의 궤적
			bc = new int[bcCnt][4]; // 배터리의 정보(bc[][0]: x 좌표, bc[][1]: y 좌표, bc[][2]: 충전 범위, bc[][3]: 성능)
			
			// 입력받은 사용자 A의 궤적과 사용자 B의 궤적 저장
			StringTokenizer stA = new StringTokenizer(br.readLine(), " ");
			StringTokenizer stB = new StringTokenizer(br.readLine(), " ");

			for (int i = 1; i <= M; i++) { // i = 0 값은 0으로 남아있다.(0: 그대로)
				pathA[i] = Integer.parseInt(stA.nextToken());
				pathB[i] = Integer.parseInt(stB.nextToken());
			}
			
			// 입력받은 배터리의 정보를 저장
			for (int i = 0; i < bcCnt; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				bc[i][0] = Integer.parseInt(st.nextToken()); // x
				bc[i][1] = Integer.parseInt(st.nextToken()); // y
				bc[i][2] = Integer.parseInt(st.nextToken()); // 충전 가능 거리
				bc[i][3] = Integer.parseInt(st.nextToken()); // 충전량
			}
			
			bw.write("#" + t + " " + sol());
			bw.newLine();
		}
		bw.flush();
	}

	private static int sol() {
		int totalSum = 0; // 결과 변수(모든 사용자의 충전량 합의 최대값)
		
		// 매시간마다의 각 위치에서 두 플레이어의 최대 충전량을 계산하여 합산
		for (int t = 0; t <= M; t++) { // 초기위치도 충전 시도
			
			// 두 플레이어 궤적에 따라 이동
			playerA[0] += dx[pathA[t]];
			playerA[1] += dy[pathA[t]];
			playerB[0] += dx[pathB[t]];
			playerB[1] += dy[pathB[t]];
			
			// 현 위치에서의 최대충전량 계산
			totalSum += getMaxCharge();
		}
		return totalSum;
	}

	/* 충전량 계산하는 함수 (충전이 불가능하면 0 리턴) */
	// bcIdx: 충전소 번호
	private static int check(int bcIdx, int x, int y) {
		return (Math.abs(bc[bcIdx][0] - x) + Math.abs(bc[bcIdx][1] - y) <= bc[bcIdx][2] ? bc[bcIdx][3] : 0);
	}
	
	/* 최대 충전량을 계산하는 함수 */
	private static int getMaxCharge() {
		int max = 0;
		
		for (int a = 0; a < bcCnt; a++) { // 플레이어 A가 선택한 배터리 번호
			for (int b = 0; b < bcCnt; b++) { // 플레이어 B가 선택한 배터리 번호
				// ex) 배터리가 3개인 경우
				// 0,0  0,1  0,2  1,0  1,1  1,2  2,0  2,1  2,2 
				int sum = 0;
				int amountA = check(a, playerA[0], playerA[1]); // player A의 충전량
				int amountB = check(b, playerB[0], playerB[1]); // player B의 충전량
				
				// 두 충전소가 다르면
				if(a != b)
					sum = amountA + amountB;
				else // 두 충전소가 같으면
					sum = Math.max(amountA, amountB);
				
				max = Math.max(max, sum);
			}
		}
		return max;
	}
}
