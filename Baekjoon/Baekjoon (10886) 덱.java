import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine()); // 명령의 수
		Deque<Integer> deque = new LinkedList<>();
		
		// 입력받은 명령 처리
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();

			switch (s) {
			case "push_front":
				int x = Integer.parseInt(st.nextToken()); // 정수 x를 덱의 앞에 넣어라
				deque.offerFirst(x);
				break;
			case "push_back":
				int y = Integer.parseInt(st.nextToken()); // 정수 x를 덱의 뒤에 넣어라
				deque.offerLast(y);
				break;
			case "pop_front":
				if (!deque.isEmpty())
					bw.write(deque.pollFirst() + "\n");
				else
					bw.write(-1 + "\n");
				break;
			case "pop_back":
				if (!deque.isEmpty())
					bw.write(deque.pollLast() + "\n");
				else
					bw.write(-1 + "\n");
				break;
			case "size":
				bw.write(deque.size() + "\n");
				break;
			case "empty":
				if (!deque.isEmpty())
					bw.write(0 + "\n");
				else
					bw.write(1 + "\n");
				break;
			case "front":
				if (!deque.isEmpty())
					bw.write(deque.getFirst() + "\n");
				else
					bw.write(-1 + "\n");
				break;
			case "back":
				if (!deque.isEmpty())
					bw.write(deque.getLast()+ "\n");
				else
					bw.write(-1 + "\n");
				break;
			}
		}
		bw.flush();
	}
}
