import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); 
		StringTokenizer st = new StringTokenizer(br.readLine(), " "); 

		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken()); 
		int res = 0; 

		for (int i = 0; i < N; i++) { 
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()); 
			int c = Integer.parseInt(st.nextToken());

			if (a >= M && b >= M && c >= M) { 
				if (a + b + c >= S) { 
					res++; 
					sb.append(a + " " + b + " " + c + " "); 
				}
			}
		}
		
		// 최종 출력
		System.out.println(res);
		System.out.println(sb.toString());
	}
}
