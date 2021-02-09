import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			int res = 0;
			for (int i = 0; i < N; i++) {
				char[] arr = new char[N];
				int cnt = 0;
				st = new StringTokenizer(br.readLine(), " ");

				while(st.hasMoreTokens()) {
					arr[cnt++] = st.nextToken().charAt(0);
				}
				
				if(i < N/2) { // arr[1]에 연산자만 나와야 함.
					if(arr[1] == '+' || arr[1] == '-' || arr[1] == '*' || arr[1] == '/') 
						res = 1;
					else
						res = 0;
				} else { // arr[1]에 숫자만 나와야 함.
					if(arr[1] == '+' || arr[1] == '-' || arr[1] == '*' || arr[1] == '/') 
						res = 0;
				}
			}
			sb.append("#" + t + " " + res + "\n");
		}
		System.out.println(sb.toString());
	}
}
