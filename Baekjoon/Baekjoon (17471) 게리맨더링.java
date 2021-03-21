import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static int N, min = Integer.MAX_VALUE;
	static int cntA, cntB; // 두 구역의 인구 수를 나타내는 변수
	static int totalCnt; // 두 구역의 총 인구 수
	static int[] population; // 각 정점의 인구 수를 담는 배열
	static LinkedList<Integer>[] list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		population = new int[N + 1];

		// 리스트 선언
		list = new LinkedList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			list[i] = new LinkedList<>();
		}

		// 각 정점별 인구 수 저장
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			totalCnt += population[i];
		}

		// 인접 리스트 생성
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());

			for (int j = 0; j < n; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		// 두 구역을 나누기 위해 조합 사용
		// 6C2와 6C4가 같으므로 N/2 까지만 돌리기
		for (int i = 1; i <= N / 2; i++) {
			makeCombination(i, 0, new LinkedList<>(), 1);
		}

		// 두 선거구로 나눌 수 없는 경우
		if (min == Integer.MAX_VALUE)
			min = -1;

		System.out.println(min);
	}

	/* 조합 함수 */
	private static void makeCombination(int n, int select, LinkedList<Integer> liA, int startIdx) {
		if (select == n) {
			diff(liA); // 두 구역의 인구 수 비교
			return;
		}

		// A구역 list 생성
		for (int i = startIdx; i <= N; i++) {
			liA.add(i);
			makeCombination(n, select + 1, liA, i + 1);
			liA.remove(liA.size() - 1);
		}
	}

	/* 두 구역 인구 수 비교 함수 */
	private static void diff(LinkedList<Integer> liA) {
		cntA = 0;
		cntB = 0;
		boolean[] visitedA = new boolean[N + 1];
		boolean[] visitedB = new boolean[N + 1];

		// A구역이 아닌 정점들로 B구역 리스트 생성
		LinkedList<Integer> liB = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (!liA.contains(i))
				liB.add(i);
		}

		dfsA(liA, liA.get(0), visitedA);
		dfsB(liB, liB.get(0), visitedB);

		// 두 구역을 각각 dfs 돌린 인구 수가 전체 인구 수와 같으면 모두 연결 완료됐다고 판단
		if (totalCnt == (cntA + cntB)) {
			min = Math.min(min, Math.abs(cntA - cntB));
		}
	}

	private static void dfsA(LinkedList<Integer> liA, int cur, boolean[] visitedA) {
		visitedA[cur] = true;
		cntA += population[cur];
		int size = liA.size();

		for (int i = 1; i < size; i++) {
			int next = liA.get(i);

			if (!visitedA[next] && list[cur].contains(next)) {
				dfsA(liA, next, visitedA);
			}
		}
	}

	private static void dfsB(LinkedList<Integer> liB, int cur, boolean[] visitedB) {
		visitedB[cur] = true;
		cntB += population[cur];
		int size = liB.size();

		for (int i = 1; i < size; i++) {
			int next = liB.get(i);

			if (!visitedB[next] && list[cur].contains(next)) {
				dfsB(liB, next, visitedB);
			}
		}
	}
}
