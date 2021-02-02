import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int n = sc.nextInt();
			int cnt = 1;
			int x = 0, y = -1;
			int div = 1;
			int[][] arr = new int[n][n];
			

			while(n > 0) {
				for (int i = 0; i < n; i++) {
					y = y + div;
					arr[x][y] = cnt++;
				}
				
				n--;
				
				for (int i = 0; i < n; i++) {
					x = x + div;
					arr[x][y] = cnt++;
				}
				
				div = div * (-1); // 방향 바꾸기
			}

			System.out.println("#" + t);
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
