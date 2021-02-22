import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; // 숫자 개수
	static int[] num; // 숫자
	static int[] op; // 연산자(+, -, *, /)

	static int MAX;
	static int MIN;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MAX = Integer.MIN_VALUE;
		MIN = Integer.MAX_VALUE;

		N = Integer.parseInt(br.readLine()); // 숫자 개수
		num = new int[N];
		op = new int[4];

		// 숫자 파싱
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		// 연산자 파싱
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}

		dfs(num[0], 1); // num[0]값을 초기값으로 주고, num[1]부터 연산하기 위해 이와 같이 파라미터 전달
		System.out.println(MAX);
		System.out.println(MIN);
	}

	public static void dfs(int sum, int depth) {
		// 연산자 모두 사용 완료
		if (depth == N) {
			MAX = Math.max(sum, MAX);
			MIN = Math.min(sum, MIN);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (op[i] <= 0)
				continue;
			else {
				--op[i]; // 연산자 개수 하나 감소
				
				switch (i) {
				case 0: // 덧셈
					dfs(sum + num[depth], depth + 1);
					break;
				case 1: // 뺄셈
					dfs(sum - num[depth], depth + 1);
					break;
				case 2: // 곱셈
					dfs(sum * num[depth], depth + 1);
					break;
				case 3: // 나눗셈
					dfs(sum / num[depth], depth + 1);
					break;
				}
				
				++op[i]; // 재귀 종료 후 연산자 개수 되돌리기
			}
		}
	}
}
