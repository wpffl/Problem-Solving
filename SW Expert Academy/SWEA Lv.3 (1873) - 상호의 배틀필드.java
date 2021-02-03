import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt(); // 테스트케이스 수

		for (int t = 1; t <= T; t++) {
			int H = sc.nextInt(); // 높이
			int W = sc.nextInt(); // 너비
			char[][] arr = new char[H][W]; // 게임 맵
			int x = 0, y = 0; // 전차가 있는 좌표
			int div = -1; // 방향을 나타내는 변수(상하좌우)

			for (int i = 0; i < arr.length; i++) {
				String s = sc.next();
				for (int j = 0; j < arr[i].length; j++) {
					arr[i][j] = s.charAt(j);

					// 전차 찾기
					if (arr[i][j] == '^') {
						x = i;
						y = j;
						div = 0; // 상
					} else if (arr[i][j] == 'v') {
						x = i;
						y = j;
						div = 1; // 하
					} else if (arr[i][j] == '<') {
						x = i;
						y = j;
						div = 2; // 좌
					} else if (arr[i][j] == '>') {
						x = i;
						y = j;
						div = 3; // 우
					}
				}
			}

			int n = sc.nextInt(); // 명령어 갯수
			String s = sc.next(); // 명령어

			for (int i = 0; i < n; i++) {
				switch (s.charAt(i)) {
				case 'U':
					// 한칸 위가 평지일 경우, 현재 위치 평지로 바꿔주고, 한칸 위로 가서 방향과 전차 모양 변경
					if (x-1 >= 0 && arr[x - 1][y] == '.') {
						arr[x][y] = '.';
						x = x - 1;
						div = 0;
						arr[x][y] = '^';
						
					} else { // 한칸 위가 평지가 아닐 경우, 전차 모양과 방향 변경
						div = 0;
						arr[x][y] = '^';
					}
					break;

				case 'D':
					if (x+1 < H && arr[x + 1][y] == '.') {
						arr[x][y] = '.';
						x = x + 1;
						div = 1;
						arr[x][y] = 'v';
					} else {
						div = 1;
						arr[x][y] = 'v';
					}
					break;

				case 'L':
					if (y-1 >= 0 && arr[x][y - 1] == '.') {
						arr[x][y] = '.';
						y = y - 1;
						div = 2;
						arr[x][y] = '<';
					} else {
						div = 2;
						arr[x][y] = '<';
					}
					break;

				case 'R':
					if (y+1 < W && arr[x][y + 1] == '.') {
						arr[x][y] = '.';
						y = y + 1;
						div = 3;
						arr[x][y] = '>';
					} else {
						div = 3;
						arr[x][y] = '>';
					}
					break;

				case 'S':
					if(div == 0) {
						int tmp = x-1;
						while(tmp >= 0) {
							if(arr[tmp][y] == '#') {
								break;
							} else if (arr[tmp][y] == '*') {
								arr[tmp][y] = '.';
								break;
							} else {
								tmp--;
							}
							
						}
						
					} else if(div == 1) {
						int tmp = x+1;
						while(tmp < H) {
							if(arr[tmp][y] == '#') {
								break;
							} else if (arr[tmp][y] == '*') {
								arr[tmp][y] = '.';
								break;
							} else {
								tmp++;
							}	
						}
					} else if(div == 2) {
						int tmp = y-1;
						while(tmp >= 0) {
							if(arr[x][tmp] == '#') {
								break;
							} else if (arr[x][tmp] == '*') {
								arr[x][tmp] = '.';
								break;
							} else {
								tmp--;
							}
						}
						
					} else if(div == 3) {
						int tmp = y+1;
						while(tmp < W) {
							if(arr[x][tmp] == '#') {
								break;
							} else if (arr[x][tmp] == '*') {
								arr[x][tmp] = '.';
								break;
							} else {
								tmp++;
							}
						}
					}
					break;
				}
			}

			System.out.print("#" + t + " ");
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					System.out.print(arr[i][j]);
				}
				System.out.println();
			}
		}
	}
}
