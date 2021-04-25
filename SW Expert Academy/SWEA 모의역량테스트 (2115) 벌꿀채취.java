import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 두 명의 일꾼의 능력치가 있는 것이 아니므로 "조합" + C를 넘지 않는 최대 이익을 선택해야 하므로 "부분 집합"
public class Solution {
	static int N, M, C, maxSum;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 벌통들의 크기
			M = Integer.parseInt(st.nextToken()); // 벌통의 개수
			C = Integer.parseInt(st.nextToken()); // 꿀을 채취할 수 있는 최대 양

			map = new int[N][N];

			// 입력 받은 벌통에서 채취할 수 있는 꿀의 양에 대한 정보 저장
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bw.write("#" + t + " " + getMaxBenefit());
			bw.newLine();
		}
		bw.flush();
	}

	private static int getMaxBenefit() {
		return processCombination();
	}

	/* 조합을 만드는 함수 */
	// 일꾼 A는 첫 행부터 연속된 M을 선택하고 고정. 일꾼 B는 일꾼 A가 선택하지 않은 공간에서 연속된 M을 선택하고 움직임!
	private static int processCombination() {
		int aBenefit = 0, bBenefit = 0; // 일꾼 A의 최대 수익, 일꾼 B의 최대 수익
		int result = 0; // 최종 최대 수익(일꾼 A + 일꾼 B)

		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) { // 첫 열부터 연속된 M개 채취가 가능한 열까지, 일꾼 A의 선택
				// 선택된 M개 벌통에서 얻을 수 있는 최대 이익
				maxSum = 0;
				makeMaxSubset(i, j, 0, 0, 0);
				aBenefit = maxSum; // 일꾼 A의 최대 이익
				

				// 일꾼 B의 선택
				// 일꾼 A와 같은 행에 뒷쪽 열에서 선택
				maxSum = 0;
				bBenefit = 0;
				for (int j2 = j + M; j2 <= N - M; j2++) {
					makeMaxSubset(i, j2, 0, 0, 0);
					bBenefit = Math.max(bBenefit, maxSum);
				}

				// 일꾼 A의 다음행부터 선택
				for (int i2 = i + 1; i2 < N; i2++) {
					for (int j2 = 0; j2 <= N - M; j2++) {
						makeMaxSubset(i2, j2, 0, 0, 0);
						bBenefit = Math.max(bBenefit, maxSum);
					}
				}
				
				result = Math.max(result, aBenefit + bBenefit);
			}
		}

		return result;
	}

	/* 부분집합을 구하고, 최대 이익을 구하는 함수 */
	private static void makeMaxSubset(int i, int j, int cnt, int sum, int powerSum) {

		// 가지치기(이미 sum이 꿀을 채취할 수 있는 최대 양(C)을 초과하면 더이상 고려 X)
		if(sum > C)
			return;
		
		// 마지막 원소까지 다 부분집합에 고려해봤다면
		if(cnt == M) {
			maxSum = Math.max(maxSum, powerSum);
			return;
		}

		// 선택
		makeMaxSubset(i, j + 1, cnt + 1, sum + map[i][j], powerSum + (map[i][j] * map[i][j]));
		// 비선택
		makeMaxSubset(i, j + 1, cnt + 1, sum, powerSum);
	}
}
