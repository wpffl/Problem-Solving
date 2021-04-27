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

		// 이분 탐색
		int start = 0, end = 0;
		for (int i = 0; i < N; i++) {
			start = i;
			end = N;

			while (start < end) {
				int mid = (start + end) / 2;

				if (numbers[mid] - numbers[i] < M) { // mid에서 i를 뺐을 때, M보다 작은 경우
					start = mid + 1;
				} else { // mid에서 i를 뺐을 때, M 이상인 경우
					end = mid;
				}
			}
			
			if(end == N) // M 이상인 경우가 없을 때
				continue;

			res = Math.min(res, numbers[end] - numbers[i]); // 최소값 저장

			if (res == M) // 두 수의 차가 M일 경우 결과 출력
				break;

		}

		System.out.println(res);
	}
}
