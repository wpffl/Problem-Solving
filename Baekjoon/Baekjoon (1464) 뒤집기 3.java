import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String origin = br.readLine(); // 원래 문자열
		String change = "";
		int len = origin.length();
		change += origin.charAt(0);
		
		for (int i = 1; i < len; i++) {
			if(change.charAt(i - 1) < origin.charAt(i)) // 다음 문자가 사전순으로 더 큰 경우
				change = origin.charAt(i) + change; 
			else
				change = change + origin.charAt(i);
		}
		
		// 출력
		// 사전순으로 작은 문자를 뒤로 보냈으므로 출력도 한번 reverse!!
		for (int i = len - 1; i >= 0; i--) {
			bw.write(change.charAt(i));
		}
		
		bw.flush();
	}
}
