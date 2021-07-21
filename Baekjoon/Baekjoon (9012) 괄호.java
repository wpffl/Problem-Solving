import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		for (int t = 0; t < T; t++) {
			String s = br.readLine();
			Stack<Character> stack = new Stack<>();
			boolean flag = true;
			
			for (int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == '(') 
					stack.push('(');
				else {
					if(stack.isEmpty()) {
						flag = false;
						break;
					} else {
						stack.pop();
					}
				}
					
			}
			
			if(flag && stack.isEmpty()) 
				bw.write("YES\n");
			else
				bw.write("NO\n");
		}
		bw.flush();
	}
}
