import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int cur = 0; // 현재 가속도
			int res = 0; // 총 이동 거리
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int cmd = Integer.parseInt(st.nextToken()); // 명령어

				switch (cmd) {
				case 0:	
					break;

				case 1:
					cur += Integer.parseInt(st.nextToken()); // 가속도만큼 가속
					break;
				
				case 2:
					cur -= Integer.parseInt(st.nextToken()); // 가속도만큼 감속
					break;
				}
				if(cur < 0) // 가속도가 음수일 경우 0으로 변경
					cur = 0;
				res += cur; // 현재 가속도만큼 거리 이동
			}
			sb.append("#" + t + " " + res + "\n");
		}
		System.out.println(sb.toString());
	}
}
