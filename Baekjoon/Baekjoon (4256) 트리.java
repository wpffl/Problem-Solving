import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] preorder;
	static int[] inorder;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine()); // 노드의 개수
			preorder = new int[N]; // 전위 순회 배열
			inorder = new int[N]; // 중위 순회 배열

			/* preorder 파싱 */
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				preorder[i] = Integer.parseInt(st.nextToken());
			}

			/* inorder 파싱 */
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				inorder[i] = Integer.parseInt(st.nextToken());
			}

			postorder(preorder, inorder);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

    /* 후위 순회로 변환해주는 함수 */
	static void postorder(int[] pre, int[] in) {
		int length = pre.length;
		int left = 0, right = 0; // 왼쪽 서브 트리 길이, 오른쪽 서브 트리 길이
		if (length == 0)
			return;

		int root = pre[0]; // 전위 순회 맨 앞이 루트 노드

		/* left child 개수 카운트 */
		for (int i = 0; i < length; i++) {
			if (root == in[i]) {
				left = i;
				break;
			}
		}
		/* 왼쪽 서브 트리가 0개가 아닐때만 수행(0이면 방문할 필요 X) */
		if (left != 0) {
			postorder(Arrays.copyOfRange(pre, 1, left + 1), Arrays.copyOfRange(in, 0, left));
		}

		/* 오른쪽 서브 트리가 0개가 아닐때만 수행(0이면 방문할 필요 X) */
		right = length - left - 1;
		if (right != 0) {
			postorder(Arrays.copyOfRange(pre, left + 1, length), Arrays.copyOfRange(in, left + 1, length));
		}
		sb.append(root + " ");
	}
}
