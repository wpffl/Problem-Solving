import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()); // 숫자의 개수
		int[] A = new int[N]; // A 배열
		int[] B = new int[N]; // B 배열
		
		// 입력받은 A 배열의 수 저장
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		// 입력받은 B 배열의 수 저장
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
			
		Arrays.sort(A); // A 배열 오름차순 정렬
		Arrays.sort(B); // B 배열 오름차순 정렬
		
		int res = 0; // 결과 변수
		// A 배열은 내림차순, B 배열은 오름차순 정렬한 것처럼 사용
		for (int i = 0; i < N; i++) {
			res += A[N - i - 1] * B[i];
		}
		
		bw.write(String.valueOf(res));
		bw.flush();
	}
}
