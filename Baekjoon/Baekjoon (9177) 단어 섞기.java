import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw;
	static char[] s1, s2, fin;
	static int s1Len, s2Len, finLen;
	static boolean find; // 결과를 찾았는지 나타내주는 변수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine()); // 집합의 개수

		// 첫 번째 단어와 두 번째 단어를 조합해서 세 번째 단어를 만들 수 있나(원래의 단어 순서는 섞이면 안됨)
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			s1 = st.nextToken().toCharArray(); // 첫 번째 단어
			s2 = st.nextToken().toCharArray(); // 두 번째 단어
			fin = st.nextToken().toCharArray(); // 최종 단어

			/* 단어 길이 저장 */
			s1Len = s1.length;
			s2Len = s2.length;
			finLen = s1Len + s2Len;

			/* fin 단어에 s1 단어 또는 s2 단어에 없는 단어가 나온 경우 dfs() 함수 호출 안하고 바로 출력 */
			Set<Character> set = new HashSet<>();

			// 단어 1과 단어 2 set에 넣기(자동 중복 제거)
			for (int j = 0; j < Math.max(s1Len, s2Len); j++) {
				if (j < s1.length)
					set.add(s1[j]);
				if (j < s2.length)
					set.add(s2[j]);
			}

			// 단어 3이 set에 들어 있는 단어로 구성되어있는지 검사
			boolean flag = true;
			for (int j = 0; j < finLen; j++) {
				if (!set.contains(fin[j])) {
					flag = false;
					break;
				}
			}

			bw.write("Data set " + i + ": ");

			find = false; // 변수 초기화
			if (flag) // 단어 3이 단어 1과 단어 2로 조합된 경우
				sol(0, 0, 0);

			if(!find) { // 단어 3이 단어 1과 단어 2로 만들지 못하는 경우
				bw.write("no");
				bw.newLine();
			}
		}
		bw.flush();
	}

	private static void sol(int cnt, int s1Idx, int s2Idx) throws IOException {
		if (find) // 이미 결과 찾은 경우
			return;

		if (cnt == finLen) { // 단어 1과 단어 2로 단어 3을 조합할 수 있는 경우
			find = true;
			bw.write("yes");
			bw.newLine();
			return;
		}

		if (s1Idx < s1Len && s1[s1Idx] == fin[cnt])
			sol(cnt + 1, s1Idx + 1, s2Idx);
		if (s2Idx < s2Len && s2[s2Idx] == fin[cnt])
			sol(cnt + 1, s1Idx, s2Idx + 1);
	}
}
