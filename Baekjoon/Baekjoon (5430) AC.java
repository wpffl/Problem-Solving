import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		here: for (int t = 0; t < T; t++) {
			String s = br.readLine(); // 수행할 함수
			int n = Integer.parseInt(br.readLine()); // 배열에 들어있는 수의 개수

			// 입력받은 정수 배열 파싱해서 저장
			Deque<Integer> deque = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), ",[]");
			for (int i = 0; i < n; i++) {
				deque.offer(Integer.parseInt(st.nextToken()));
			}

			boolean isReverse = false; // 배열이 반대인지 아닌지 나타내는 flag
			// 함수 수행
			for (int i = 0; i < s.length(); i++) {
				switch (s.charAt(i)) {
				case 'R': // 배열 뒤집기
					isReverse = !isReverse;
					break;

				case 'D': // 맨 앞에 숫자 버리기
					if (deque.isEmpty()) { // 배열이 비어있는데 D를 사용한경우 에러 발생
						bw.write("error");
						bw.newLine();
						continue here;
					}
					if (isReverse)
						deque.pollLast();
					else
						deque.pollFirst();
					break;
				}
			}

			// 정상적으로 수행된 경우 정수 배열의 상태 출력
			if (deque.isEmpty()) {
				bw.write("[]");
				bw.newLine();
			}
			
			else {
				StringBuilder sb = new StringBuilder();
				sb.append("[");
				while (!deque.isEmpty()) {
					if (isReverse) // 배열을 뒤집어야하는 경우
						sb.append(deque.pollLast() + ",");
					else
						sb.append(deque.pollFirst() + ",");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append("]");
				bw.write(sb.toString());
				bw.newLine();
			}
		}
		bw.flush();
	}
}
