import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine()); // 가로수의 수
		int[] trees = new int[N]; // 가로수의 위치
		int[] distance = new int[N - 1]; // 가로수간 간격

		// 입력받은 가로수의 위치를 받아 저장
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(br.readLine());

			if (i != 0) // 가로수간 간격 저장
				distance[i - 1] = trees[i] - trees[i - 1];
		}

		// 가로수간 간격 중 최대공약수 찾기
		for (int i = 0; i < N - 2; i++) {
			distance[i + 1] = gcd(distance[i + 1], distance[i]);
		}

		int dis = distance[N - 2]; // 가로수간 간격
		int total = trees[N - 1] - trees[0]; // 가로수 총 거리

		// 출력
		bw.write(String.valueOf(total / dis - (N - 1)));
		bw.flush();
	}

	/* 유클리드 호제법을 사용한 최대공약수 구하기 */
	private static int gcd(int num1, int num2) {
		if (num2 == 0)
			return num1;

		return gcd(num2, num1 % num2);
	}
}
