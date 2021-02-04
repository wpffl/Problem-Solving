import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Top {
	int num; // 탑 번호
	int height; // 탑 높이

	Top(int num, int height) {
		this.num = num;
		this.height = height;
	}
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		Stack<Top> input = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// 맨 처음 탑 --> 비교할거 없이 그냥 대입
		input.push(new Top(1, Integer.parseInt(st.nextToken())));
		sb.append((int) 0 + " ");

		// 두 번째 탑 부터
		for (int i = 2; i <= N; i++) {
			int height = Integer.parseInt(st.nextToken());

			while (true) {
				if (!input.isEmpty()) { // stack이 비어있지 않으면
					Top top = input.peek(); 

					if (top.height > height) { // 나보다 앞의 위치에 있는 탑이 나보다 높을 경우 -> 레이저 맞을 탑
						sb.append(top.num + " ");
						input.push(new Top(i, height));
						break;

					} else { // 나보다 앞의 위치에 있는 탑이 나보다 낮을 경우 -> 레이저 안 맞음
						input.pop();
					}
				} else { // stack이 비었을 경우 -> 레이저 맞을 탑 없음
					sb.append("0 ");
					input.push(new Top(i, height));
					break;
				}
			}
		}

		System.out.println(sb.toString());
	}
}
