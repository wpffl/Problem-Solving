import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int res = 0; // 결과 변수(설탕 봉지 개수)

		int N = Integer.parseInt(br.readLine()); // 설탕 kg 값

		while (N > 0) {

			if (N % 5 == 0) { // N이 5의 배수인 경우(5kg 설탕 봉지 쓰는게 가장 조금 든다)
				res += N / 5;
				break;
			}

			N -= 3;

			if (N < 0) { // 3kg과 5kg 봉지를 써서 딱 떨어지지 않는 경우
				res = -1;
				break;
			}

			res++; // 3kg 설탕 봉지 1개 추가

		}
		System.out.println(res);
	}
}
