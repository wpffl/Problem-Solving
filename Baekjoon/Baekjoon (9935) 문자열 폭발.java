import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		Stack<Character> stack = new Stack<>();
		String s = br.readLine(); // 문자열
		String bomb = br.readLine(); // 폭발 문자열
		int sLen = s.length();
		int bombLen = bomb.length();

		for (int i = 0; i < sLen; i++) {
			stack.push(s.charAt(i));

			if (bombLen <= stack.size()) { // stack에 있는 문자열의 길이가 폭발 문자열의 길이보다 같거나 큰 경우
				boolean flag = true;

				for (int j = 0; j < bombLen; j++) {
					if (stack.get(stack.size() - bombLen + j) != bomb.charAt(j)) {
						flag = false;
						break;
					}
				}

				if (flag) { // 폭발 문자열이 있는 경우
					for (int j = 0; j < bombLen; j++) {
						stack.pop();
					}
				}
			}
		}
		// 출력
		if (!stack.isEmpty()) {
			for (char c : stack) // for-each문 써서 stack 출력하면 선입선출!
				bw.write(c);
		} else {
			bw.write("FRULA");
		}
		bw.flush();
	}
}
