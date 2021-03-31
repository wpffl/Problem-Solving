import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Product {
		int weight; // 물건의 무게
		int value; // 물건의 가치

		public Product(int weight, int value) {
			super();
			this.weight = weight;
			this.value = value;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 물품의 수
		int K = Integer.parseInt(st.nextToken()); // 가방에 넣을 수 있는 최대 무게
		Product[] arr = new Product[N + 1]; // 물건의 정보를 저장할 배열
		int[][] DP = new int[N + 1][K + 1]; // 해당 물건까지 고려하여 해당 무게를 만들때의 최대가치(예: DP[2][4]: 물건 1~2까지 고려하고, 배낭의 용량이 4일 때의 최대 가치)

		// 입력받은 물건의 정보 저장
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int weight = Integer.parseInt(st.nextToken()); // 물건의 무게
			int value = Integer.parseInt(st.nextToken()); // 물건의 가치
			
			arr[i] = new Product(weight, value);
		}

		for (int i = 1; i <= N; i++) { // 첫물건부터 고려
			for (int w = 1; w <= K; w++) { // 무게1부터 고려
				if (arr[i].weight <= w) { // 가방에 넣을수 있는 상황
					// 물건을 넣을지 말지
					DP[i][w] = Math.max(DP[i - 1][w - arr[i].weight] + arr[i].value, DP[i - 1][w]);

				} else { // 가방에 넣지 못하는 상황
					DP[i][w] = DP[i - 1][w];
				}
			}
		}
		System.out.println(DP[N][K]);
	}
}
