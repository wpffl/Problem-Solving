import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[N];
		int[] res = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 절댓값 기준으로 오름차순 정렬 */
		Arrays.sort(arr, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return Math.abs(o1) - Math.abs(o2);
			}
			
		});
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N-1; i++) {
			if(Math.abs(arr[i] + arr[i+1]) < min) {
				min = Math.abs(arr[i] + arr[i+1]);
				res[0] = Math.min(arr[i], arr[i+1]);
				res[1] = Math.max(arr[i], arr[i+1]);
			}
		}
		
		System.out.println(res[0] + " " + res[1]);
	}
}
