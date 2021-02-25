import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String s = br.readLine();
			int sum = 0;
			int cnt = 0;
			for (int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == 'O') {
					cnt++;
					sum += cnt;
					
				} else {
					cnt = 0;
				}
			}
			System.out.println(sum);
		}
	}
}
