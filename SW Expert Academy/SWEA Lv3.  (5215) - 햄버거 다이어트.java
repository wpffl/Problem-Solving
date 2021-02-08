import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] arr; // 재료 정보를 저장할 배열
	static int N; // 재료 갯수
	static int L; // 최대 칼로리
	static int max;

	public static void powerSet(int select, int sumCal, int sumScore) {
		if (sumCal > L) // 칼로리 초과
			return; 

		if (select == N) {
			if (sumScore > max) {
				max = sumScore;
			}
			return;
		}
		// 재료 넣기
		powerSet(select + 1, sumCal + arr[select][1], sumScore + arr[select][0]);		
		// 재료 빼기
		powerSet(select + 1, sumCal, sumScore);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 재료 갯수
			L = Integer.parseInt(st.nextToken()); // 최대 칼로리
			arr = new int[N][2];
			max = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");

				for (int j = 0; j < 2; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			powerSet(0, 0, 0);

			sb.append("#" + t + " " + max + "\n");
		}
		System.out.println(sb.toString());
	}
}
