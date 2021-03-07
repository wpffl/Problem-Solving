import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 세로
		int M = Integer.parseInt(st.nextToken()); // 가로
		int res;
		
		if(N == 1) // 세로가 1인 경우: 움직이지 못함
			res = 1;
		else if(N == 2) { // 세로가 2인 경우: 가로로 2칸씩 이동
			/*
			 * M:1 -> 1
			 * M:2 -> 1
			 * M:3 -> 2
			 * M:4 -> 2
			 * M:5 -> 3
			 * M:6 -> 3
			 */
			res = Math.min((M+1)/2, 4);
		} else if(M < 7) { // 가로가 7보다 작은 경우: 최대로 4번 밖에 움직이지 못함. 
			/* N은 2보다 큰 경우 이므로 오른쪽으로 한칸 씩 갈 수 있음.
			 * 따라서 가로의 길이인 M번 만큼 갈 수 있음.
			 * 하지만 방문 횟수가 4번 이상인 경우 4가지 방향 모두 사용해야 하는데 가로의 길이가 7칸이 안되기 때문에 불가능.ㄴ
			 * 따라서 4번이 최대
			 */
			res = Math.min(M, 4);
		} else { // 그 외의 경우: 4가지 방향으로 모두 이동 후(이 시점에서 5칸 방문), 오른쪽으로 한칸씩만 이동(이 시점에서 가로 7칸 씀)
			/* res = 5 + (M - 7) */
			res = M -2;
		}
		
		System.out.println(res);
	}
}
