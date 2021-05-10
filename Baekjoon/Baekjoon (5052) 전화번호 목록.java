import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine()); // 전화번호의 수

			// 입력받은 전화번호 저장
			String[] phoneNum = new String[n];
			for (int i = 0; i < n; i++) {
				phoneNum[i] = br.readLine();
			}

			Arrays.sort(phoneNum); // 오름차순 정렬
			
			// 일관성 있는 목록인지 검사
			boolean flag = true; // 일관성이 있는지 나타내는 변수
			for (int i = 1; i < n; i++) {
				if (phoneNum[i].startsWith(phoneNum[i - 1])) {
					flag = false;
					break;
				}
			}

			// 결과 출력
			if(flag)
				bw.write("YES\n");
			else
				bw.write("NO\n");
		}
		bw.flush();
	}
}
