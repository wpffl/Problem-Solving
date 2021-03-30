import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 포도주 잔의 개수
		int[] podo = new int[n]; // 포도주 잔에 들어있는 포도주의 양을 저장할 배열

		// 입력받은 포도주의 양 저장
		for (int i = 0; i < n; i++) {
			podo[i] = Integer.parseInt(br.readLine());
		}

		if (n == 1) { // 포도주가 1개인 경우
			System.out.println(podo[0]);
		} else if (n == 2) { // 포도주가 2개인 경우
			System.out.println(podo[0] + podo[1]);
		} else { // 포도주가 3개 이상인 경우
			/* dp배열 초기 세팅 */
			int[] dp = new int[n]; // 해당 위치에서 최선의 선택을 저장할 배열
			dp[0] = podo[0]; 
			dp[1] = dp[0] + podo[1];
			dp[2] = Math.max(dp[1], Math.max(podo[0] + podo[2], podo[1] + podo[2]));

			// dp 배열 계산
			for (int i = 3; i < n; i++) {
				// dp[i - 3] + podo[i - 1] + podo[i]: i-3까지의 최선의 선택 + 두 칸 건너 뛰고, 현재 위치의 포도주, 바로 앞의 포도주 마시는 경우
				// dp[i - 2] + podo[i]: i-2까지의 최선의 선택 + 한 칸 건너뛰고 현재 위치의 포도주를 마시는 경우
				dp[i] = Math.max(dp[i - 3] + podo[i - 1] + podo[i], dp[i - 2] + podo[i]);
				
				// dp[i-1]이 더 큰 경우: 현재 위치에서 포도주를 마시지 않는 경우
				dp[i] = Math.max(dp[i - 1], dp[i]); 
			}
			
			System.out.println(dp[n - 1]);
		}
	}
}
