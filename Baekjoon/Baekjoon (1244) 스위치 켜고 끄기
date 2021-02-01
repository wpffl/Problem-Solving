import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 스위치 개수
		int[] swit = new int[N + 1]; // 스위치 상태를 나타내는 배열

		for (int i = 1; i <= N; i++) {
			swit[i] = sc.nextInt();
		}

		int snum = sc.nextInt(); // 학생 수

		for (int i = 0; i < snum; i++) {
			int gender = sc.nextInt(); // 학생의 성별 (1: 남학생, 2: 여학생)
			int sw = sc.nextInt(); // 받은 스위치 번호

			if (gender == 1) { // 남학생의 경우
				for (int j = sw; j <= N; j += sw) {
					if (swit[j] == 0)
						swit[j] = 1;
					else
						swit[j] = 0;
				}
			} else { // 여학생의 경우
				if (swit[sw] == 0) {
					swit[sw] = 1;
				} else {
					swit[sw] = 0;
				}

				int prev = sw - 1;
				int next = sw + 1;

				while (prev > 0 && next <= N) {
					if (swit[prev] == swit[next]) {
						if (swit[prev] == 0) {
							swit[prev] = swit[next] = 1;
						} else {
							swit[prev] = swit[next] = 0;
						}
						--prev;
						++next;
					} else {
						break;
					}
				}
			}
		}
		// 최종 출력
		for (int i = 1; i <= N; i++) {
			System.out.print(swit[i] + " ");
			if (i % 20 == 0)
				System.out.println();
		}
	}
}
