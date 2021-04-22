import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.FormatFlagsConversionMismatchException;
import java.util.LinkedList; 
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 학생 수
		int M = Integer.parseInt(st.nextToken()); // 두 학생 키를 비교한 횟수
		int res = 0; // 결과 변수(자신이 키가 몇번째인지 알 수 있는 학생의 수)

		boolean[][] arr = new boolean[N][N]; // 주어진 대로 입력받은 경우
		boolean[][] reverseArr = new boolean[N][N]; // 주어진 반대로 입력받은 경우

		// 입력 받은 학생들의 키를 비교한 결과 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1; // 키가 작은 학생
			int b = Integer.parseInt(st.nextToken()) - 1; // 키가 큰 학생

			arr[a][b] = true; // 키 작은 -> 큰
			reverseArr[b][a] = true; // 키 큰 -> 작은
		}

		// 플로이드 와샬 알고리즘 수행(나보다 큰 애들 찾기)
		for (int k = 0; k < N; ++k) { // 경유지
			for (int i = 0; i < N; ++i) { // 출발지
				for (int j = 0; j < N; ++j) { // 도착지
					if (arr[i][k] && arr[k][j]) // 출발지 -> 경유지 갈 수 있고, 경유지 -> 도착지 갈수 있냐
						arr[i][j] = true; // 출발지 -> 도착지 갈수 있다
				}
			}
		}

		// 플로이드 와샬 알고리즘 수행(나보다 작은 애들 찾기)
		for (int k = 0; k < N; ++k) { // 경유지
			for (int i = 0; i < N; ++i) { // 출발지
				for (int j = 0; j < N; ++j) { // 도착지
					if (reverseArr[i][k] && reverseArr[k][j]) // 출발지 -> 경유지 갈 수 있고, 경유지 -> 도착지 갈수 있냐
						reverseArr[i][j] = true; // 출발지 -> 도착지 갈수 있다
				}
			}
		}

		// 특정 학생에 대해 키가 크거나 작은 학생을 모두 파악
		// OR 연산을 수행한 arr[i][j]가 false이면 해당 학생과 키 비교를 할 수 없다는 뜻
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = arr[i][j] || reverseArr[i][j];
			}
		}
		
		// 나를 제외한 모든 다른 학생들과 키를 비교할 수 있는 학생 수 카운트
		here: for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == j) // 자기자신 - 자기자신은 건너뛰기
					continue;
				
				if(!arr[i][j]) // 키를 비교할 수 없는 학생이 존재하는 경우(다음 학생으로 넘어감)
					continue here;
			}
			++res; // i = j인 경우를 제외하고, arr[i][j]가 모두 true인 경우(즉, 모든 학생과 키를 비교할 수 있는 경우)
		}
		
		System.out.println(res);
	}
}
