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
		
		String origin = br.readLine(); // 원래 문자열
		String change = "";
		int len = origin.length();
		change += origin.charAt(0);
		
		for (int i = 1; i < len; i++) {
			if(change.charAt(i - 1) < origin.charAt(i)) 
				change = origin.charAt(i) + change;
			else
				change = change + origin.charAt(i);
		}
		
		for (int i = len - 1; i >= 0; i--) {
			bw.write(change.charAt(i));
		}
		bw.flush();
	}
}
