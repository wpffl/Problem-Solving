import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int res = 0; // 소가 길을 건너간 횟수
		int[] dir = new int[10]; // 각 소별 현재 방향(-1:방문 X, 0:왼쪽, 1:오른쪽)
		Arrays.fill(dir, -1);
		
		int N = Integer.parseInt(br.readLine()); // 관찰 횟수
		
		// 입력받은 관찰 결과 저장
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()); // 0: 왼쪽, 1: 오른쪽
			
			if(dir[num] == -1) { // 한번도 관찰 결과가 없는 경우
				dir[num] = d;
			} else if(dir[num] != d) { // 현재 방향과 다른 방향인 경우 -> 길을 건넌 경우
				++res;
				dir[num] = d;
			}
		}
		
		bw.write(String.valueOf(res));
		bw.flush();
	}
}
