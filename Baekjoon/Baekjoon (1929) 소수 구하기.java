import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		boolean[] num = new boolean[1000001]; // true: 소수 X, false: 소수 
		num[0] = num[1] = true; // 0과 1은 소수가 아니므로 제외
		
		// 에라토스테네스의 체 사용하여 소수 구하기
		for (int i = 2; i * i< 1000000; i++) {
			if(!num[i]) {
				for (int j = i * i; j < 1000000; j+=i) {
					num[j] = true;
				}
			}
		}
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		// 출력
		for (int i = M; i <= N; i++) {
			if(!num[i]) // 소수인 경우
				bw.write(String.valueOf(i) + "\n");
		}
		
		bw.flush();
	}
}
