import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static String standard = "quack";
	static boolean[] visited;
	static int res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine(); // 검사할 문자열
		int len = s.length(); // 문자열 길이
		visited = new boolean[len]; // 문자열을 방문 체크
		
		res = sol(s, len);

		bw.write(String.valueOf(res));
		bw.flush();
	}
	
	private static int sol(String s, int len) {
		if(len % 5 != 0) // 검사할 문자열의 길이가 5로 나누어 떨어지지 않는 경우
			return -1;
		
		for (int i = 0; i < len; i++) { // 'q'를 만났고, 방문하지 않은 경우 
			if(s.charAt(i) == 'q' && !visited[i]) {
				visited[i] = true;
				check(i + 1, s, len); // 최대한 많은 'quack'를 체크
			}
		}
		
		// 모든 문자열이 지워지지 않은 경우
		for (int i = 0; i < len; i++) {
			if(!visited[i])
				return -1;
		}
		
		if(res == 0) // 'quack'이 하나도 없는 경우
			res = -1;
		
		return res;
	}

	private static void check(int idx, String s, int len) {
		int standardIdx = 1; // 'quack' 문자열의 index
		boolean isFirst = true; // 첫번째 'quack' 탐색인지 나타내는 변수
		
		for (int i = idx; i < len; i++) {
			if(!visited[i] && standard.charAt(standardIdx) == s.charAt(i)) {
				++standardIdx;
				visited[i] = true;
				
				if(s.charAt(i) == 'k')  { // 'quack'이 끝난 경우, 새로운 quack을 찾기 위해 idx 초기화
					if(isFirst) { // 첫 탐색인경우, 결과변수 + 1
						++res;
						isFirst = false;
					}
					standardIdx = 0;
				}
			}
		}
	}
}
