import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int[] parents; // 각 원소가 부모의 인덱스를 가지고 있는 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		for (int t = 1; t <= T; t++) {
			bw.write("#" + t + " ");

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 원소의 개수
			int M = Integer.parseInt(st.nextToken()); // 연산의 개수

			parents = new int[N + 1];
			make(N); // 크기가 1인 단위 집합 만들기

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int op = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				switch (op) {
				case 0: // 합집합
					union(a, b);
					break;
				case 1: // 두 원소가 같은 집합에 포함되어 있는지 확인
					bw.write(String.valueOf(check(a, b)));
					break;
				}
			}
			bw.newLine();
		}
		br.close();
		bw.close();
	}

	private static void make(int N) {
		for (int i = 0; i < N + 1; i++) {
			parents[i] = i;
		}
	}

	private static int findSet(int a) {
		if(parents[a] == a)
			return a;
		else
			// Path Compression 적용
			return parents[a] = findSet(parents[a]);
	}

	private static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		parents[bRoot] = aRoot; // bRoot가 aRoot를 가르키도록 설정
	}
	
	private static int check(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return 1;
		else
			return 0;
	}
}
