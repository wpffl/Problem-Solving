import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static char[][] arr;
	static int R, C, res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 문자열의 개수
		C = Integer.parseInt(st.nextToken()); // 문자열 당 문자의 개수
		arr = new char[R][C];

		// 입력받은 문자열 저장
		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		// 처음에 주어지는 테이블에는 열을 읽어서 문자열을 만들 때, 동일한 문자열이 존재하지 않음이 보장되므로 1번째 행부터 검사
		binarySearch(1, R - 1);
		
		// 출력
		bw.write(String.valueOf(res));
		bw.flush();
	}

	private static void binarySearch(int start, int end) {
		if (start > end)
			return;

		int mid = (start + end) / 2;
		boolean isOverlap = check(mid, R - 1); // 문자열 검사(mid 행부터 R-1 행까지)

		if (isOverlap) { // 동일한 문자열이 있는 경우 -> mid 이전 검사
			binarySearch(start, mid - 1);
		} else { // 동일한 문자열이 없는 경우 -> mid 이후 검사
			res = Math.max(res, mid); // 중복이 발생하지 않은 행 중 최대값으로 업데이트
			binarySearch(mid + 1, end);
		}
	}

	private static boolean check(int start, int end) {
		Set<String> set = new HashSet<>();
		
		// start 행부터 마지막 행까지 열 단위로 읽기
		for (int i = 0; i < C; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = start; j <= end ; j++) {
				sb.append(arr[j][i]);
			}
			
			if(set.contains(sb.toString())) { // set에 해당 문자열이 존재하면 true 리턴(중복이 존재함을 뜻함)
				return true;
			} else { // 존재하지 않으면 set에 추가
				set.add(sb.toString());
			}
		}
		return false;
	}
}
