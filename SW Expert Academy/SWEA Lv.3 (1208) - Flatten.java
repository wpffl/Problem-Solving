import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	private final static int MIN = 0;
	private final static int MAX = 100;
	private static int[] boxH = new int[MAX];
	
	// dump하는 함수
	private static void dump() {
		Arrays.sort(boxH);
		boxH[MIN] += 1;
		boxH[MAX-1] -= 1;	
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/D3_1208.txt"));
		Scanner sc = new Scanner(System.in);
		
		for (int t = 1; t <= 10; t++) {
			int cnt = sc.nextInt();
			
			// 입력 받은 박스 높이 배열에 저장
			for (int i = 0; i < MAX; i++) {
				boxH[i] = sc.nextInt();
			}
			
			// 덤프 횟수만큼 덤프 수행
			for (int i = 0; i < cnt; i++) {
    				// 높이가 최소 차이가 됐을 때 break
				if(boxH[MAX-1] - boxH[MIN] == 1) {
					break;
				}
				dump();
			}
			
			// 마지막 sort
			Arrays.sort(boxH);
			
			System.out.println("#" + t + " " + (boxH[MAX-1]-boxH[MIN]));
		}
		sc.close();
	}
}
