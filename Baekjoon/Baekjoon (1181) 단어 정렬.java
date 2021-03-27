import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine()); // 단어의 개수
		String[] arr = new String[N]; // 단어 저장할 배열

		// 입력 받은 단어 저장
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}

		// 배열 정렬
		Arrays.sort(arr, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if(o1.length() == o2.length()) // 길이가 같으면
					return o1.compareTo(o2); // 오름차순 정렬
				else // 길이가 다르면
					return o1.length() - o2.length(); // 길이가 짧은 순으로 출력
			}
		});
		
		sb.append(arr[0] + "\n");
		
		for (int i = 1; i < N; i++) {
			// 중복되지 않는 단어만 출력
			if (!arr[i].equals(arr[i - 1])) {
				sb.append(arr[i] + "\n");
			}
		}
		System.out.println(sb.toString());
	}
}
