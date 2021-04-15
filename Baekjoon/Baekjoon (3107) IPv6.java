import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		String s = br.readLine(); // 압축된 IPv6
		String[] arr = s.split(":"); // ':'을 기준으로 문자열 쪼개기
		int len = arr.length; // 쪼갠 문자열의 길이

		String[] res = new String[8]; // 결과 배열

		for (int i = 0; i < len; i++) {
			int cnt = 4 - arr[i].length();
			if (cnt != 0) { // 0이 생략된 경우, 부족한 만큼 앞에 0 채워주고 문자 넣기
				for (int j = 0; j < cnt; j++) {
					sb.append("0");
				}
				sb.append(arr[i]);

				arr[i] = sb.toString();
				sb.setLength(0);
			}
		}

		// 만약 쪼갠 갯수가 8개가 아닌 경우 -> '::'가 있다
		if (len != 8) {
			int arrIdx = 0, resIdx = 0;
			if(len == 0) { // 입력이 '::'인 경우
				for (int i = 0; i < 8; i++) 
					res[i] = "0000";
			} else {
				while(resIdx < 8) {
					// 현재 arr[]이 "0000"이 아닌 경우, 그대로 결과배열에 넣기
					if(arrIdx < len && !arr[arrIdx].equals("0000")) {
						res[resIdx++] = arr[arrIdx++];
					} else { // 현재 arr[]이 "0000"인 경우
						while(8 - resIdx != len - arrIdx) {
							res[resIdx++] = "0000";
							
							if(arrIdx < len && arr[arrIdx].equals("0000")) {
								arrIdx++;
							}
						}
					}
				}
			}
		} else { // 쪼갠 개수가 8개인 경우 결과 배열에 넣기
			for (int i = 0; i < 8; i++) {
				res[i] = arr[i];
			}
		}

		// 출력
		for (int i = 0; i < 8; i++) {
			if (i == 7)
				bw.write(res[i]);
			else
				bw.write(res[i] + ":");
		}

		bw.flush();
	}
}
