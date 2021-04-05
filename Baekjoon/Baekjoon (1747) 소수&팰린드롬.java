import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int res = 0; // 결과 변수

		if (N == 1) // 1은 소수가 아니므로 다음으로 큰 소수인 2로 세팅
			res = 2;
		else {
			/* N보다 크거나 같고, 소수이면서 팰린드롬인 수 중에서 가장 작은 수 구하기 */
			// 1. 소수 검사(에라토스테네스의 체) -> 미리 구해 놓기
			boolean[] check = new boolean[1004001]; // true면 지워진 수, false면 지워지지 않은 수
			for (int i = 2; i <= 1004000; i++) {
				if (!check[i]) {
					for (int j = i + i; j <= 1004000; j += i) {
						check[j] = true;
					}
				}
			}

			// 소수면 팰린드롬 검사
			while (true) {
				if (!check[N]) { // 소수인지 검사
					// 팰린드롬인지 검사
					boolean isPalindrome = true;
					String num = Integer.toString(N);

					int left = 0;
					int right = num.length() - 1;

					for (int cnt = 0; cnt < num.length() / 2; cnt++) {
						if (num.charAt(left) != num.charAt(right)) {
							isPalindrome = false;
							break;
						}

						++left;
						--right;
					}

					// 팰린드롬인 경우 반복문 종료
					if (isPalindrome) {
						res = N;
						break;
					}
				}
				++N; // 소수 또는 팰린드롬이 아닌 경우 N 증가
			}
		}
		System.out.println(res);
	}
}
