import java.util.Scanner;

public class Main {
	private static int MAX = 0;
	private static int BJ; // 블랙잭의 기준이 되는 값(나올 수 있는 합의 최대값)
	private static int[] card;
	private static int N = 3; // 뽑아야할 카드 수

	private static void makePermutation(int toSelect, int[] selected, boolean[] visited) {
		if (toSelect == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				sum += selected[i];
			}

			// 블랙잭의 최대값이 더 클 경우
			if (sum > MAX && sum <= BJ)
				MAX = sum;

			return;
		}

		for (int i = 0; i < card.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[toSelect] = card[i];
				makePermutation(toSelect + 1, selected, visited);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int total = sc.nextInt(); // 총 카드의 수
		BJ = sc.nextInt(); // 합의 최대(블랙잭의 기준이 되는 값)
		card = new int[total];

		for (int i = 0; i < total; i++) {
			card[i] = sc.nextInt();
		}

		makePermutation(0, new int[N], new boolean[card.length]);
		
		System.out.println(MAX);
	}
}
