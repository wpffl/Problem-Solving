import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int H, W;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken()); // 세로(높이)
		W = Integer.parseInt(st.nextToken()); // 가로
		int rainCnt = 0; // 빗물의 양
		
		int[] height = new int[W];
		
		// 입력받은 map의 높이 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < W - 1; i++) {
			int cur = height[i]; // 현재 벽 높이
			int leftMax = cur; // 왼쪽 벽 최대 높이
			int rightMax = cur; // 오른쪽 벽 최대 높이
			
			for (int j = i - 1; j >= 0; j--) { // 왼쪽 벽 최대 높이 구하기
				leftMax = Math.max(leftMax, height[j]);
			}
			
			for (int j = i + 1; j < W; j++) { // 오른쪽 벽 최대 높이 구하기
				rightMax = Math.max(rightMax, height[j]);
			}
			
			if(cur < Math.min(leftMax, rightMax)) { // 현재 벽보다 높은 벽이 양쪽에 있는 경우 -> 빗물이 고일 수 있다!
				rainCnt += (Math.min(leftMax, rightMax) - height[i]);
			}
		}
		
		bw.write(String.valueOf(rainCnt));
		bw.flush();
	}
}
