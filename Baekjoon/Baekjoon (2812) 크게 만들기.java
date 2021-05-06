import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 수의 자리 수
		int K = Integer.parseInt(st.nextToken()); // 지워야될 자리 수
		int cnt = N - K; // 지운 최종 자리 수(출력할 자리 수)
		char[] num = br.readLine().toCharArray(); // 수

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			// stack의 마지막 수보다 현재 수가 더 크다면 현재 수보다 작은 수 모두 지우기
			while (K > 0 && !stack.isEmpty() && stack.peek() < num[i]) { 
				stack.pop(); 
				--K; 
			}
			stack.push(num[i]);
		}

		// cnt(N - K)만큼 출력
		for (int i = 0; i < cnt; i++) {
			bw.write(String.valueOf(stack.get(i)));
		}
		bw.flush();
	}
}
