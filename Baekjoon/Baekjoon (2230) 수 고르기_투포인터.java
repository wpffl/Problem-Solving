import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 수열의 개수
		M = Integer.parseInt(st.nextToken()); // 수의 차이
		numbers = new int[N]; // 수열
		int res = Integer.MAX_VALUE; // 결과 변수(M이상이면서 가장 작은 차이)

		// 입력 받은 수열 저장
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}

		// 수열 오름차순 정렬
		Arrays.sort(numbers);

		// 투 포인터
		int left = 0, right = 0;
		while (right < N) {
			if (numbers[right] - numbers[left] < M) { // M보다 작으면 오른쪽 포인터 오른쪽으로 한 칸 이동
				++right;
				continue;
			} else if (numbers[right] - numbers[left] == M) { // M이랑 같으면 최소이므로 결과 출력
				res = M;
				break;
			}

			res = Math.min(res, numbers[right] - numbers[left]); // 그 외의 경우 최소값 저장
			++left;
		}

		System.out.println(res);
	}
}
