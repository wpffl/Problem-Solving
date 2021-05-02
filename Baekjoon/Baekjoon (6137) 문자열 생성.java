import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine()); // 문자열의 길이
		char[] s = new char[N];
		// 입력받은 문자 저장
		for (int i = 0; i < N; i++) {
			s[i] = br.readLine().charAt(0);
		}

		// 문자열 개수만큼 반복
		int left = 0, right = N - 1;
		int cnt = 0; // 새로운 문자열의 길이
		while (cnt < N) {
			if (s[left] < s[right]) { // 오른쪽 포인터가 가리키는 문자가 더 큰 경우
				bw.write(s[left]);
				++left;
			} else if (s[right] < s[left]) { // 왼쪽 포인터가 가리키는 문자가 더 큰 경우
				bw.write(s[right]);
				--right;

			} else { // 왼쪽 포인터가 가리키는 문자와 오른쪽 포인터가 가리키는 문자가 같은 경우
				// 그 다음 가리킬 문자들 탐색
				int l = left + 1, r = right - 1;

				while (l < r) { // 두 포인터가 가리키는 문자가 같지 않을 때까지 탐색
					if (s[l] != s[r]) {
						break;
					}
					++l;
					--r;
				}

				if (s[l] < s[r]) { // 뒤에 나오는 문자 중 왼쪽이 더 사전순으로 앞인 경우
					bw.write(s[left]);
					++left;
				} else { // 그 외의 경우
					bw.write(s[right]);
					--right;
				}

			}
			// 80글자마다 개행
			if (++cnt % 80 == 0) 
				bw.write("\n"); // bw.newLine()보다 빠름
		}
		bw.flush();
	}
}
