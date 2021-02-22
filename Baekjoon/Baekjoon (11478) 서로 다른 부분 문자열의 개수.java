import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine(); // 문자열
		int length = s.length(); // 문자열 길이
		HashSet<String> set = new HashSet<>(); // 부분 문자열을 저장할 Set

		int size = 1; // 부분 문자열의 길이
		while (size < length) {
			for (int i = 0; i <= length - size; i++) {
				String str = s.substring(i, i + size); // i번째 index부터 부분 문자열의 길이만큼 자르기
				set.add(str);
			}
			size++;
		}
		// 부분 문자열 갯수에 1을 더해 문자열 전체도 부분문자열에 포함시킴
		System.out.println(set.size() + 1);

	}
}
