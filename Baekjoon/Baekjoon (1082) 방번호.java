import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, budget, minIdx, minPrice;
	static int[] price;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine()); // 문방구에서 파는 숫자의 수
		price = new int[N]; // index: 숫자 번호. 값: 숫자의 가격

		// 입력받은 숫자별 가격 저장
		st = new StringTokenizer(br.readLine(), " ");
		minIdx = 0; // 가격이 가장 낮은 숫자의 index를 저장
		minPrice = 51; // 숫자 중 최소 가격
		for (int i = 0; i < N; i++) {
			price[i] = Integer.parseInt(st.nextToken());

			if (price[i] <= minPrice) { // 숫자 중 최소 가격 찾기
				minIdx = i;
				minPrice = price[i];
			}
		}

		budget = Integer.parseInt(br.readLine()); // 내가 가지고 있는 돈

		// 1. minIdx만 사용해서 최대로 만들 수 있는 수를 만듦.
		int maxLen = getMaxLen();

		// 2. 남은 돈으로 큰 숫자들 중 살 수 있는 수가 있다면 사서 맨 뒤에 붙임. 없으면 종료. (0인 경우도 따로 고려)
		if (minIdx == 0)
			sol(maxLen, true);
		else
			sol(maxLen, false);
		
		bw.write(sb.toString());
		bw.flush();
	}

	/* minIdx를 사용해서 만들 수 있는 최대 길이 구하는 함수 */
	private static int getMaxLen() {
		int len = 0; // 숫자 길이

		while (budget >= minPrice) { // 남은 예산으로 숫자 살 수 있는지 확인
			++len;
			budget -= minPrice;
		}

		return len;
	}

	/* 만들 수 있는 가장 큰 수를 구해주는 함수 */
	// 1. 최소 금액 숫자가 0인 경우
	// 0을 하나씩 지워가며 살 수 있는 가장 큰 수 삼 -> 더이상 살 수 없을 때까지 반복
	// 하나도 살 수 없는 경우 0 출력

	// 2. 그 외의 경우
	// 최소의 숫자를 하나 지워서 살 수 있는 가장 큰 수 삼 
	// 살 수 없는 경우 팔았던 최소의 숫자를 다시 사서 결과 출력
	// 그 외의 경우 새로 산 수를 앞에 더해서 결과 출력
	private static void sol(int maxLen, boolean isAllZero) {
		boolean buyNumber = false; // 새로운 숫자를 샀는지 나타내는 변수

		while (maxLen-- > 0) {
			budget += minPrice; // 비용이 최소인 숫자 버리기
			buyNumber = false;

			for (int i = N - 1; i > minIdx; i--) { // 큰 수부터 탐색하면서 살 수 있는 수가 있는지 확인
				if (price[i] <= budget) {
					buyNumber = true; // 숫자 산거 표시
					isAllZero = false; // 모두 0이 아니므로 표시
					budget -= price[i]; // 새로 산 수의 가격만큼 예산에서 제외
					sb.append(i); // 새로 산 수 앞에 넣어둠(minIdx보다 큰 수이므로)
					break;
				}
			}

			if (!buyNumber) { // 숫자 못 산 경우
				if (isAllZero) // 모두 0이면 하나 더 팔아서 숫자를 살 수 있는지 확인
					continue;
				else { // 그 외의 경우 팔았던 최소 숫자 다시 사고 종료(두개를 팔아서 큰 수를 사면 오히려 숫자가 작아지므로)
					++maxLen;
					budget += minPrice;
					break;
				}
			}
		}

		if (isAllZero) { // 모두 0인 경우
			sb.append(0);
		} else { // 그 외의 경우
			for (int i = 0; i < maxLen; i++)
				sb.append(minIdx);
		}
	}
}
