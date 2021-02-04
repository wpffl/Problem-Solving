import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < 10; t++) {
			int cnt = 1;
			
			int num = Integer.parseInt(br.readLine());
			Queue<Integer> queue = new LinkedList<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			// 입력 받은 8자리 순서대로 입력
			while(st.hasMoreTokens()) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}
			
			while(true) {
				if(cnt == 6) 
					cnt = 1;
				
				int tmp = queue.poll() - cnt;
				
				if(tmp <= 0) {
					tmp = 0;
					queue.offer(tmp);
					break;
				} else {
					queue.offer(tmp);
					++cnt;
				}
			}		
			
			// 결과 출력
			sb.append("#"+ num + " ");
			for (int i = 0; i < 8; i++) {
				if(i == 7) {
					sb.append(queue.poll() + "\n");
				} else {
					sb.append(queue.poll() + " ");
				}
				
			}
			
		}
		System.out.println(sb.toString());
	}
}
