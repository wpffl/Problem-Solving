import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine()); // 명령의 수
		Queue<Integer> queue = new LinkedList<>();
		int back = 0; // 큐의 마지막 원소
		
		// 입력받은 명령 처리
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();

			switch (s) {
			case "push":
				back = Integer.parseInt(st.nextToken()); // 정수 x를 스택에 넣어라
				queue.offer(back);
				break;
			case "pop":
				if (!queue.isEmpty())
					bw.write(queue.poll() + "\n");
				else
					bw.write(-1 + "\n");
				break;
			case "size":
				bw.write(queue.size() + "\n");
				break;
			case "empty":
				if (!queue.isEmpty())
					bw.write(0 + "\n");
				else
					bw.write(1 + "\n");
				break;
			case "front":
				if (!queue.isEmpty())
					bw.write(queue.peek() + "\n");
				else
					bw.write(-1 + "\n");
				break;
			case "back":
				if (!queue.isEmpty())
					bw.write(back+ "\n");
				else
					bw.write(-1 + "\n");
				break;
			}
		}
		bw.flush();
	}
}
