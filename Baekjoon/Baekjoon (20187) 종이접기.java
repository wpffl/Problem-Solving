import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int k = Integer.parseInt(br.readLine()); // 한 변의 길이가 2^k인 정사각형
		int len = (int) Math.pow(2, k); // 정사각형 한 변의 길이

		// 마지막까지 위치가 변하지 않는 칸을 찾기 위한 변수
		int left = 0;
		int right = len;
		int top = 0;
		int bottom = len;

		// 입력받은 종이 접는 방법에 따라 처리
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < k * 2; i++) {
			char dir = st.nextToken().charAt(0); // 종이 접는 방향

			switch (dir) {
			case 'D': // 위에서 아래로 접음
				top = (top + bottom) / 2;
				break;

			case 'U': // 아래에서 위로 접음
				bottom = (top + bottom) / 2;
				break;
			case 'R': // 왼쪽에서 오른쪽으로 접음
				left = (left + right) / 2;
				break;
			case 'L': // 오른쪽에서 왼쪽으로 접음
				right = (left + right) / 2;
				break;
			}
		}

		int h = Integer.parseInt(br.readLine()); // 구멍 뚫는 위치

		int res[][][] = new int[4][2][2]; // 결과 배열(res[h][x][y])

		// 기본
		res[0][0][0] = 0;
		res[0][0][1] = 2;
		res[0][1][0] = 1;
		res[0][1][1] = 3;

		// 좌우 대칭
		res[1][0][0] = 1;
		res[1][0][1] = 3;
		res[1][1][0] = 0;
		res[1][1][1] = 2;

		// 상하 대칭
		res[2][0][0] = 2;
		res[2][0][1] = 0;
		res[2][1][0] = 3;
		res[2][1][1] = 1;

		// 상하좌우 대칭
		res[3][0][0] = 3;
		res[3][0][1] = 1;
		res[3][1][0] = 2;
		res[3][1][1] = 0;

		for (int y = 0; y < len; y++) {
			for (int x = 0; x < len; x++) {
				bw.write(String.valueOf(res[h][Math.abs(left - x) % 2][Math.abs(top - y) % 2])
						+ (x == len - 1 ? "" : " ")); // 마지막을 제외하고 한 칸씩 띄어쓰기
			}
			bw.newLine();
		}
		bw.flush();
	}
}
