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

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		int greatest = 0; // a와 b의 최대공약수
		int least = 0; // a와 b의 최소 공배수

		// 최대공약수 구하기
		if (a > b)
			greatest = gcd(a, b);
		else
			greatest = gcd(b, a);

		// 최소공배수 구하기
		least = (a * b) / greatest;

		bw.write(greatest + "\n" + least);
		bw.flush();
	}

	private static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}
}
