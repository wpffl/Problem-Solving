import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static char[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = ' ';
			}
		}

		printStar(0, 0, N);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bw.write(arr[i][j]);
			}
			bw.newLine();
		}
		
		br.close();
		bw.close();
	}

	private static void printStar(int x, int y, int N) {
		if (N == 1) {
			arr[x][y] = '*';
			return;
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1)
					continue;
				else {
					printStar(x + i * (N / 3), y + j * (N / 3), N / 3);
				}
			}
		}
	}
}
