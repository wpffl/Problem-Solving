import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, S, res;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		res = 0;
		powerSet(new boolean[N], 0);
		
		// S가 0인 경우 공집합 경우 제거
		if(S == 0)
			--res;
		
		System.out.println(res);
	}
	private static void powerSet(boolean[] checked, int cnt) {
		if(cnt == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if(checked[i]) {
					sum += arr[i];
				}
			}
			if(sum == S)
				res++;
			
			return;
		}
		
		// 선택
		checked[cnt] = true;
		powerSet(checked, cnt + 1);
		// 비선택
		checked[cnt] = false;
		powerSet(checked, cnt + 1);
	}
}
