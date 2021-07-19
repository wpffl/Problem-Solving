import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			char[] arr = br.readLine().toCharArray();
			Arrays.sort(arr);

			for (int j = 0; j < arr.length; j++) // 첫 문자 출력
				bw.write(arr[j]);
			bw.newLine();

			while (np(arr)) {
				for (int j = 0; j < arr.length; j++)
					bw.write(arr[j]);
				bw.newLine();
			}
		}
		bw.flush();
	}

	private static boolean np(char[] arr) {
		int i = arr.length - 1;

		while (i > 0 && arr[i] <= arr[i - 1]) // 앞의 문자보다 뒤의 문자가 사전상 뒤에 오는 경우 탐색
			i--;

		if (i <= 0)
			return false;

		int j = arr.length - 1;

		while (arr[i - 1] >= arr[j]) // 선택한 문자보다 사전상 뒤에 오는 문자를 배열 끝에서부터 탐색
			j--;

		// swap
		char temp = arr[j];
		arr[j] = arr[i - 1];
		arr[i - 1] = temp;

		j = arr.length - 1;
		while (i < j) { // 뒤에 문자들 순서 뒤집어 줌
			temp = arr[j];
			arr[j] = arr[i];
			arr[i] = temp;
			i++;
			j--;
		}
		return true;
	}
}
