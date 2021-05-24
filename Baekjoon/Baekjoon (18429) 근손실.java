import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, K, res;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 운동키트의 개수
		K = Integer.parseInt(st.nextToken()); // 1일마다 감소하는 중량의 무게
		
		// 입력받은 운동키트별 중량 증가량 저장
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		makePermutation(0, new int[N], new boolean[N]);
		
		bw.write(String.valueOf(res));
		bw.flush();
	}

	// nPn 순열 만들어주는 함수
	private static void makePermutation(int select, int[] selected, boolean[] visited) {
		if(select == N) { // 다 뽑은 경우
			if(cal(selected)) 
				++res;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				selected[select] = arr[i];
				makePermutation(select + 1, selected, visited);
				visited[i] = false;
			}
		}
	}

	// N일 동안 중량이 500보다 작아지지 않는지 검사하는 함수
	private static boolean cal(int[] selected) {
		int weight = 500;
		
		for (int i = 0; i < N; i++) {
			weight -= K;
			weight += selected[i];
			
			if(weight < 500)
				return false;
		}
		
		return true;
	}
}
