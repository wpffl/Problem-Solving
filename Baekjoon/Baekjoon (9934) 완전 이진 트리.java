import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Integer>> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		list = new ArrayList<>(); // 정답 리스트
		int K = Integer.parseInt(br.readLine()); // 완전 이진 트리의 깊이
		// 완전 이진 트리 깊이만큼 new
		for (int i = 0; i < K; i++) {
			list.add(new ArrayList<>());
		}

		int N = (int) (Math.pow(2, K)) - 1; // 노드의 개수
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		sol(arr, 0, N, 0, K);
		
		for(ArrayList<Integer> i : list) {
			for(int j : i) {
				bw.write(String.valueOf(j) + " ");
			}
			bw.newLine();
		}
		br.close();
		bw.close();
	}

	private static void sol(int[] arr, int start, int end, int depth, int K) {
		if (depth > K - 1) {
			return;
		}

		if (start == end) {
			list.get(depth).add(arr[start]);
			return;
		}

		int mid = (start + end) / 2; // 루트 노드 index
		list.get(depth).add(arr[mid]); 
		sol(arr, start, mid - 1, depth + 1, K); // 왼쪽 서브트리 탐색
		sol(arr, mid + 1, end, depth + 1, K); // 오른쪽 서브트리 탐색
		return;
	}
}
