import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt(); // 테스트 케이스 수

		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt(); // 농장의 크기
			int res = 0; // 결과 변수
			int[][] arr = new int[N][N];

			// 농장물 가치 데이터 넣기
			for (int i = 0; i < N; i++) {
				String s = sc.next();
				for (int j = 0; j < N; j++) {
					arr[i][j] = Character.getNumericValue(s.charAt(j));
				}
			}
      // 마름모 모양으로 농작물 수확
			for (int i = 0; i < N; i++) {
				for (int j = Math.abs(N / 2 - i); j < N - Math.abs(N / 2 - i); j++) {
					res += arr[i][j];
				}
			}

			System.out.println("#" + t + " " + res);
		}
	}
}
