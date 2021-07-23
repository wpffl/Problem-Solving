import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 수의 개수
			
			if(N == 0) // 입력이 끝난 경우
				break;
			
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) { // 입력받은 수 저장
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			makeCombination(arr, new int[6], 0, 0);
			bw.newLine();
		}
		bw.flush();
	}

	private static void makeCombination(int[] arr, int[] selected, int select, int startIdx) throws IOException {
		if(select == 6) { // 6개 숫자 다 뽑은 경우
			for (int i = 0; i < 6; i++) {
				bw.write(selected[i] + " ");
			}
			bw.newLine();
			return;
		}
		
		for (int i = startIdx; i < arr.length; i++) {
			selected[select] = arr[i];
			makeCombination(arr, selected, select + 1, i + 1);
		}
	}
}
