import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // O의 개수
		int M = Integer.parseInt(br.readLine()); // 문자열의 길이
		char[] arr = br.readLine().toCharArray(); // 문자열
		int res = 0; // 결과 변수
		int cnt = 0; // OI 패턴 연속 수

		for (int i = 0; i <= M - 3; i++) {
			if (arr[i] == 'I' && arr[i + 1] == 'O' && arr[i + 2] == 'I') {
				cnt++;
				i++;

				if (cnt == N) {
					cnt--;
					res++;
				}
			} else {
				cnt = 0;
			}
		}
		System.out.println(res);
	}
}
