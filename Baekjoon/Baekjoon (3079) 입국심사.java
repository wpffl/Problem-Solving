import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static long N, M, maxTime, res = Long.MAX_VALUE;
	static int[] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Long.parseLong(st.nextToken()); // 입국 심사대 수
		M = Long.parseLong(st.nextToken()); // 인원 수
		time = new int[(int) N]; // 각 입국 심사대별 심사 시간을 담고 있는 배열

		// 입력받은 입국 심사 시간 저장
		for (int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(br.readLine());
			maxTime = Math.max(maxTime, time[i]); // 최대 입국 심사 시간 찾기
		}

		binarySearch(0, maxTime * M); // 최대값 = 최대 입국 심사 시간 * 인원 수

		bw.write(String.valueOf(res));
		bw.flush();
	}

	private static void binarySearch(long left, long right) {
		while (left <= right) {
			long mid = (left + right) / 2;

			if (isPossible(mid)) { // 해당 시간에 인원 전부 입국 심사가 가능한 경우
				res = Math.min(res, mid); // mid 값 업데이트
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
	}

	/* 인자로 주어진 시간에 인원 전부 입국 심사 가능한지 확인하는 함수 */
	private static boolean isPossible(long mid) {
		long pepolCnt = 0;

		for (int t : time) {
			pepolCnt += mid / t;
		}

		return pepolCnt >= M;
	}
}
