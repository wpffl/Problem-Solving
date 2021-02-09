import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		boolean[][] arr = new boolean[100][100]; // 흰색 도화지(false로 초기화)
		int res = 0; // 결과 변수
		
		for (int i = 0; i < N; i++) { // 색종이 수만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken()); // 도화지의 왼쪽 변과 색종이의 왼쪽 변 사이의 거리
			int b = Integer.parseInt(st.nextToken()); // 도화지의 아래쪽 변과 색종이의 아래쪽 변 사이의 거리
			
			for (int x = a-1; x < a+9; x++) { // 색종이 크기인 10만큼 색종이 영역 true로 설정(배열 index때문에 -1씩 빼줌)
				for (int y = b-1; y < b+9; y++) {
					arr[x][y] = true;
				}
			}
		}
		
		// 흰색 도화지에서 true인 영역 카운트 => 총 면적
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(arr[i][j])
					++res;
			}
		}
		System.out.println(res);
	}
}
