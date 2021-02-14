import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int res = 0;
		int val = 1;
		Stack<Character> stack = new Stack<>();

		String s = br.readLine();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == '(') {
				stack.push(c);
				val *= 2;

			} else if (c == ')') {
				// 잘못된 괄호의 경우
				if (stack.isEmpty() || stack.peek() != '(') {
					res = 0;
					break;
				}
				// 내 바로 앞이 '('인 경우만 더해주기
				if (s.charAt(i - 1) == '(')
					res += val;
				stack.pop();
				val /= 2;

			} else if (c == '[') {
				stack.push(c);
				val *= 3;

			} else if (c == ']') {
				// 잘못된 괄호의 경우
				if (stack.isEmpty() || stack.peek() != '[') {
					res = 0;
					break;
				}
				// 내 바로 앞이 '['인 경우만 더해주기
				if (s.charAt(i - 1) == '[')
					res += val;
				stack.pop();
				val /= 3;
			}
		}

		/* stack이 비어있지 않으면 잘못된 괄호  아니면 결과 출력*/
		if (!stack.isEmpty())
			System.out.println(0);
		else
			System.out.println(res);

	}
}
