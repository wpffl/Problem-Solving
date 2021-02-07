import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/D4_1210.txt"));
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {
			int tnum = sc.nextInt(); // 테스트케이스 번호

			int[][] ladder = new int[100][100];
			int x = 0, y = 99;
			
			// 테스트케이스 배열에 저장
			for (int i = 0; i < ladder.length; i++) {
				for (int j = 0; j < ladder[i].length; j++) {
					ladder[i][j] = sc.nextInt();
					
					// 탐색할 위치
					if(ladder[i][j] == 2) {
						x = j;
					}
				}
			}

			// 경로 탐색
			while(y != 0) {
				if(x > 0 && ladder[y][x-1] == 1) { // 왼쪽
					x--;
				} else if(x < 99 && ladder[y][x+1] == 1) { // 오른쪽
					x++;
				} else { // 위쪽
					y--;
				}
				ladder[y][x] = 0;
			}
			System.out.println("#" + t + " " + x);
		}
	}
}
