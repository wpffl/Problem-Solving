import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int L, C;
	static List<Character> list;
	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken()); // 암호의 길이
		C = Integer.parseInt(st.nextToken()); // 알파벳 개수
		list = new ArrayList<>();
		//HashSet<Character> set = new HashSet<>();

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < C; i++) {
			list.add(st.nextToken().charAt(0));
		}

		// 오름차순 정렬
		
		Collections.sort(list);

		// C개중에 L개만큼 뽑는 조합
		makeCombination(0, new char[L], 0);

		br.close();
		bw.close();
	}

	static void makeCombination(int select, char[] selected, int startIdx) throws IOException {
		if (select == L) {
			String s = "";
			int vCnt = 0; // 모음 개수
			int cCnt = 0; // 자음 개수

			for (int i = 0; i < L; i++) {
				char c = selected[i];
				s += c;

				// 모음, 자음 개수 카운트
				if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
					vCnt++;
				} else {
					cCnt++;
				}
			}

			// 모음과 자음이 조건에 맞는 경우만 write
			if (vCnt >= 1 && cCnt >= 2) {
				bw.write(s);
				bw.newLine();
			}
			return;
		}

		for (int i = startIdx; i < C; i++) {
			selected[select] = list.get(i);
			makeCombination(select + 1, selected, i + 1);
		}
	}
}
