import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()); // 정수의 개수
		int[] num = new int[N];
		
		// 입력받은 정수 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
		
		int M = Integer.parseInt(br.readLine()); // 검사할 숫자의 개수
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int idx = Arrays.binarySearch(num, Integer.parseInt(st.nextToken()));
			
			if(idx < 0) // 이분 탐색한 결과값이 음수이면 수가 존재하지 않는 경우
				bw.write(0 + "\n");
			else
				bw.write(1 + "\n");
		}
		bw.flush();
	}
}
