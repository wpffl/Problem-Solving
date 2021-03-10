import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static ArrayList<Long> downNumList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 감소하는 수 리스트 만들기
		downNumList = new ArrayList<>();

		// i는 맨 앞자리 수
		for (int i = 0; i < 10; i++) {
			getDownNumber(i, 1);
		}

		// 오름차순 정렬
		Collections.sort(downNumList);

		// 가장 큰 감소하는 수는 9876543210 (이는 1022번째) 
		if (N > 1022) {
			System.out.println(-1); // 감소하는 수를 만들 수 없는 경우
		} else {
			System.out.println(downNumList.get(N));
		}
	}

	/* 0으로 시작하는 감소하는 수, 1로 시작하는 감소하는 수 ... 만드는 함수 */
	private static void getDownNumber(long num, int digit) {

		if (digit > 10) { // '9'876543210 (맨 앞자리를 제외하고 9번만 돌면 됨)
			return;
		}

		downNumList.add(num); 

		for (int i = 0; i < 10; i++) {
			if (num % 10 > i) { // 1의 자리 수가 i보다 클 경우
				getDownNumber((num * 10) + i, digit + 1); // 뽑은 수를 뒤에 붙이기 위한 shift 작업ㄴ
			}
		}
        return;
	}
}
