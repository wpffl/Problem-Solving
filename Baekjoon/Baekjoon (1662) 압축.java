import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static String s;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		s = br.readLine(); // 압축된 문자열
		System.out.println(sol());
	}

	static int sol() {
		int res = 0; // 결과 변수
		int length = s.length() - 1; // 문자열 맨 끝부터 시작
		Stack<Integer> stack = new Stack<>();

		/* 문자열의 맨 뒤에서 0번 index(맨 처음)까지 탐색 */
		while (length >= 0) {
			char c = s.charAt(length);
			// 닫는 괄호를 만나면 -1 push
			if (c == ')') {
				stack.push(-1);
			} else if (c == '(') {
				int total = 0;
				while (stack.peek() != -1) { // ')'를 만날 때 까지 연산 더하기
					total += stack.pop();
				}
				stack.pop(); // ')' pop()
				int K = s.charAt(--length) - '0'; // '(' 앞 K int형으로 변환
				int release = K * total; // 문자열 길이 * K => 압축 해제
				stack.push(release);
			} else {
				stack.push(1); // 숫자를 만난 경우(숫자는 무조건 1자리 수)
			}
			length--;
		}

		/* 최종 문자열의 길이 계산 */
		while (!stack.isEmpty()) {
			res += stack.pop();
		}
		return res;
	}
}
