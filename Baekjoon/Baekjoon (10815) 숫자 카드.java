import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Set<Integer> set = new HashSet<>();

		int N = Integer.parseInt(br.readLine()); // 상근이가 가지고 있는 숫자 카드의 개수

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}

		int M = Integer.parseInt(br.readLine()); // 검사핧 카드의 개수
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			if (set.contains(Integer.parseInt(st.nextToken()))) {
				bw.write(String.valueOf(1) + " "); // 상근이가 가지고 있는 카드의 경우
			} else {
				bw.write(String.valueOf(0) + " "); // 상근이가 가지고 있지 않는 카드의 경우
			}
		}

		br.close();
		bw.close();
	}
}
