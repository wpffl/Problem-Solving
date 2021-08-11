import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		String s = br.readLine();
        int len = s.length();
		boolean isTag = false;

		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);

			if (c == '<') {
				bw.write(sb.reverse().toString());
				sb.setLength(0);
				bw.write("<");
				isTag = true;
			} else if (c == '>') {
				bw.write(">");
				isTag = false;
			} else if (isTag) { // 태그인 경우 그대로 출력
				bw.write(c);
			} else {
				if (c == ' ') { // 공백이면 단어 역순으로 출력
					bw.write(sb.reverse().toString() + " ");
					sb.setLength(0);
				} else { // 공백이 아닌 경우 StringBuilder에 저장
					sb.append(c);
				}
			}
		}
    
		bw.write(sb.reverse().toString());
		bw.flush();
	}
}
