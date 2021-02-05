import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int num, sum, res = 0;
		
		for (int i = 0; i < N; i++) {
			num = i;
			sum = 0;
			
			while(num != 0) {
				sum += num % 10; // 자릿수 더하기
				num /= 10;
			}
			
			if(i + sum == N) { // 생성자를 찾은 경우
				res = i;
				break;
			}
		}
		System.out.println(res);
	}
}
