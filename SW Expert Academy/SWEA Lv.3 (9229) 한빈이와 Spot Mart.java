import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[] arr;
	static int N = 2;
	static int M;
	static int max;

	public static void makeCombination(int select, int[] selected, int startIdx) {
		if (select == N) {
			int sum = 0;
			for (int i = 0; i < selected.length; i++) {
				sum += selected[i];
			}

			if (sum > M) // 과자 무게 합이 초과했을 경우
				return;

			if (sum > max) { // max값 구하기
				max = sum;
			}
			return;
		}

		for (int i = startIdx; i < arr.length; i++) {
			selected[select] = arr[i];
			makeCombination(select + 1, selected, i + 1);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken()); // 과자 봉지의 수
			M = Integer.parseInt(st.nextToken()); // 들고 갈 수 있는 최대 무게
			arr = new int[num];
			max = -1;

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < num; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			makeCombination(0, new int[N], 0);
			
			sb.append("#" + t + " " + max + "\n");
		}
		System.out.println(sb.toString());
	}
}
