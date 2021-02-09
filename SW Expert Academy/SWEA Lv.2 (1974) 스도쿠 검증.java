import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {
	static HashSet<Integer> s = new HashSet<>(); // 중복 체크 하기 위해 사용
	static int[][] arr = new int[9][9]; // 스도쿠 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for (int t = 1; t <= T; t++) {
			
			// 스도쿠 값 넣기
			for (int i = 0; i < 9; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 9; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append("#" + t + " " + valid() + "\n");
		}
		System.out.println(sb.toString());
	}

	// 검사 함수
	static int valid() {
		// 한 줄 검사(가로)
		for (int i = 0; i < 9; i++) {
			s.clear();

			for (int j = 0; j < 9; j++) {
				s.add(arr[i][j]);
			}
			if (s.size() < 9)
				return 0;
		}
		
		// 한 줄 검사(세로)
		for (int i = 0; i < 9; i++) {
			s.clear();

			for (int j = 0; j < 9; j++) {
				s.add(arr[j][i]);
			}
			if (s.size() < 9)
				return 0;
		}

		// 3*3 배열 검사
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				s.clear();
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						s.add(arr[(i * 3) + k][(j * 3) + l]);
					}
				}
				if(s.size() < 9)
					return 0;
			}
		}
		return 1;
	}
}
