import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine()); // 명령의 수
		Stack<Integer> stack = new Stack<>();
		
		// 입력받은 명령 처리
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();

			switch (s) {
			case "push":
				int x = Integer.parseInt(st.nextToken()); // 정수 x를 스택에 넣어라
				stack.push(x);
				break;
			case "pop":
				if(!stack.isEmpty()) 
					bw.write(stack.pop() + "\n");
				else 
					bw.write(-1 + "\n");
				break;
			case "size":
				bw.write(stack.size() + "\n");
				break;
			case "empty":
				if(!stack.isEmpty()) 
					bw.write(0 + "\n");
				else 
					bw.write(1 + "\n");
				break;
			case "top":
				if(!stack.isEmpty()) 
					bw.write(stack.peek() + "\n");
				else 
					bw.write(-1 + "\n");
				break;
			}
		}
		bw.flush();
	}
}
