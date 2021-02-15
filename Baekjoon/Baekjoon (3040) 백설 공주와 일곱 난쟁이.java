import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N = 7;
	static int[] arr;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		arr = new int[9];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		makeCombination(0, new int[N], 0);
		System.out.println(sb.toString());
	}

	static void makeCombination(int select, int[] selected, int startIdx) {
		if (select == N) {
			int sum = 0;
			for (int i = 0; i < selected.length; i++) {
				sum += selected[i];
			}

			if (sum == 100) {
				for (int i = 0; i < selected.length; i++) {
					sb.append(selected[i] + "\n");
				}

			}
			return;
		}

		for (int i = startIdx; i < arr.length; i++) {
			selected[select] = arr[i];
			makeCombination(select + 1, selected, i + 1);
		}
	}
}
