import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int N, X;
	static int[][] map, reverseMap;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // map의 크기
			X = Integer.parseInt(st.nextToken()); // 경사로의 길이(높이는 1로 고정)
			map = new int[N][N]; // 입력 받은 행과 열 그대로의 map
			reverseMap = new int[N][N]; // 입력 받은 행과 열을 뒤집어서 저장한 map

			// 입력 받은 지형 정보 저장
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = reverseMap[j][i] = Integer.parseInt(st.nextToken());
				}
			}

			bw.write("#" + t + " " + process());
			bw.newLine();
		}
		bw.flush();
	}

	/* 활주로 건설 가능한 개수를 세는 함수 */
	private static int process() {

		int count = 0;
		for (int i = 0; i < N; i++) {
			if (makeRoad(map[i])) // 행 탐색
				++count;
			if (makeRoad(reverseMap[i])) // 열 탐색
				++count;
		}
		return count;
	}

	/* 입력으로 들어온 배열을 기준으로 탐색해서 활주로 건설 가능한지 확인하는 함수 */
	private static boolean makeRoad(int[] road) {
		int beforeHeight = road[0]; // 이전 높이
		int size = 0; // 연속된 동일 높이의 개수
		int j = 0; // 탐색 열 위치

		while (j < N) {
			if (beforeHeight == road[j]) { // 이전 높이와 현재 행 높이가 같으면 사이즈 증가, j 증가
				++size;
				++j;
			} else if (beforeHeight + 1 == road[j]) { // 오르막 경사로 설치 가능한지 판단
				if (size < X) // 경사로의 길이가 X보다 작은 경우
					return false; // 경사로 설치 불가
				// 그 외의 경우 (설치 가능)
				beforeHeight++;
				size = 1;
				++j;
			} else if (beforeHeight - 1 == road[j]) { // 내리막 경사로 설치 가능한지 판단
				int count = 0;

				for (int k = j; k < N; k++) { // 다음 나오는 경사로의 길이가 X만큼 똑같은지 확인
					if (road[k] != beforeHeight - 1)
						break;
					if (++count == X)
						break;
				}

				if (count < X) // 경사로의 길이가 X보다 작은 경우
					return false; // 경사로 설치 불가
				// 그 외의 경우 (설치 가능)
				beforeHeight--;
				size = 0;
				j += X;
			} else {
				return false;
			}
		}
		return true;
	}
}
